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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
            redisCache.set(RedisKeyConstants.USER_INFO_PREFIX + cookbookUserDO.getId(),
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
}
