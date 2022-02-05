package com.zhang.redis.dao;

import com.zhang.redis.common.dao.BaseDao;
import com.zhang.redis.entity.UserDO;
import com.zhang.redis.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDao<UserMapper, UserDO> {
}
