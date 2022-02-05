package com.zhang.redis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhang.redis.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
