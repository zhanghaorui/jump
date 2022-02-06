package com.zhang.redis.service.impl;

import com.zhang.redis.cache.CacheSupport;
import com.zhang.redis.cache.RedisCache;
import com.zhang.redis.cache.RedisLock;
import com.zhang.redis.common.exception.BaseBizException;
import com.zhang.redis.common.util.JsonUtil;
import com.zhang.redis.constant.RedisKeyConstants;
import com.zhang.redis.convert.UserConvert;
import com.zhang.redis.dao.UserDao;
import com.zhang.redis.entity.UserDO;
import com.zhang.redis.entity.dto.UserDTO;
import com.zhang.redis.entity.request.SaveAndUpdateUserRequest;
import com.zhang.redis.entity.response.SaveOrUpdateUserDto;
import com.zhang.redis.service.CookUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.zhang.redis.constant.RedisKeyConstants.USER_INFO_PREFIX;

/**
 * @author Admin
 */
@Slf4j
@Service
public class CookUserServiceImpl implements CookUserService {


    @Autowired
    private RedisCache redisCache;


    @Autowired
    private RedisLock redisLock;

    @Autowired
    private UserConvert userConvert;

    @Autowired
    private UserDao userDao;

    private static final long USER_UPDATE_LOCK_TIMEOUT = 200;


    @Override
    public SaveOrUpdateUserDto saveOrUpdateUser(SaveAndUpdateUserRequest saveAndUpdateUserRequest) {
        String saveOrUpdateUserKey = RedisKeyConstants.USER_UPDATE_LOCK_PREFIX+saveAndUpdateUserRequest.getOperator();
        // 对修改人加锁  防止修改人并发修改
        Boolean lock = redisLock.lock(saveOrUpdateUserKey);
        if (!lock) {
            log.info("操作作者信息获取锁失败，operator:{}", saveAndUpdateUserRequest.getOperator());
            throw new BaseBizException("新增/修改失败");
        }

        try {
            // 这个时候有一个线程来更新了，先写了db，此时db已经成了新数据
            UserDO cookbookUserDO = userConvert.convertCookbookUserDO(saveAndUpdateUserRequest);
            cookbookUserDO.setUpdateUser(saveAndUpdateUserRequest.getOperator());
            if (Objects.isNull(cookbookUserDO.getId())) {
                // 若前端未传入id  则认为此用户为新用户
                cookbookUserDO.setCreateUser(saveAndUpdateUserRequest.getOperator());
            }
            // 先落db
            userDao.saveOrUpdate(cookbookUserDO);

            UserDTO cookbookUserDTO = userConvert.convertCookbookUserDTO(cookbookUserDO);
            redisCache.set(USER_INFO_PREFIX + cookbookUserDO.getId(),
                    JsonUtil.object2Json(cookbookUserDTO), CacheSupport.generateCacheExpireSecond());

            SaveOrUpdateUserDto dto = SaveOrUpdateUserDto.builder()
                    .success(true)
                    .build();
            return dto;
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            // 最终释放锁
            redisLock.unlock(saveOrUpdateUserKey);
        }
        return null;
    }


    @Override
    public UserDTO getUserInfoById(Long id) {
        // 先从redis 捞一手  并续期  可视为热点数据
        UserDTO userDTO = getUserFromCache(id);
        if (!Objects.isNull(userDTO)) {
            return userDTO;
        }
        // 若redis 无此数据  读数据表  并反写redis
        return getUserFromDb(id);
    }

    private UserDTO getUserFromDb(Long id) {
        String userLockKey = RedisKeyConstants.USER_UPDATE_LOCK_PREFIX + id;
        boolean lock = false;
        try {
            lock = redisLock.tryLock(userLockKey, USER_UPDATE_LOCK_TIMEOUT);
        } catch(InterruptedException e) {
            UserDTO user = getUserFromCache(id);
            if(user != null) {
                return user;
            }
            log.error(e.getMessage(), e);
            throw new BaseBizException("查询失败");
        }

        if (!lock) {
            UserDTO user = getUserFromCache(id);
            if(user != null) {
                return user;
            }
            log.info("缓存数据为空，从数据库查询作者信息时获取锁失败，userId:{}", id);
            throw new BaseBizException("查询失败");
        }

        try {
            UserDTO user = getUserFromCache(id);
            if(user != null) {
                return user;
            }

            log.info("缓存数据为空，从数据库中获取数据，userId:{}", id);

            String userInfoKey = RedisKeyConstants.USER_INFO_PREFIX + id;

            // 在这里先读到了db里的用户信息的旧数据
            // 这个线程刚刚读到，还没有来得及把旧数据写入缓存里去
            UserDO cookbookUserDO = userDao.getById(id);
            if (Objects.isNull(cookbookUserDO)) {
                // 给redis里扔个空值  查不到的数据无需在查
                redisCache.set(userInfoKey, CacheSupport.EMPTY_CACHE, CacheSupport.generateCachePenetrationExpireSecond());
                return null;
            }

            UserDTO dto = userConvert.convertCookbookUserDTO(cookbookUserDO);

            // 此时这个线程，在上面的那个线程都已经把新数据写入缓存里去了，缓存里已经是最新数据了
            // 把旧数据库，写入了缓存做了一个覆盖操作，典型的，数据库+缓存双写的时候，写和读，并发的时候
            // db里是新数据，缓存里是旧数据，旧数据是覆盖了新数据的
            // db和缓存，数据是不一致的
            redisCache.set(userInfoKey, JsonUtil.object2Json(dto), CacheSupport.generateCacheExpireSecond());
            return dto;
        } finally {
            redisLock.unlock(userLockKey);
        }

    }

    private UserDTO getUserFromCache(Long id) {
        String userKey = USER_INFO_PREFIX+id;
        String userJson = redisCache.get(userKey);
        log.info("从redis中取出的key:{},value:{}", userKey, userJson);
        if (!StringUtils.isEmpty(userJson)) {
            if (Objects.equals(CacheSupport.EMPTY_CACHE,userJson)) {
                // 防止缓存穿透
                return new UserDTO();
            }
            redisCache.expire(RedisKeyConstants.USER_INFO_PREFIX + id,
                    CacheSupport.generateCacheExpireSecond());
            return JsonUtil.json2Object(userJson,UserDTO.class);
        }
        return null;

    }
}
