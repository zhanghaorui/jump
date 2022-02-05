package com.zhang.redis.service;

import com.zhang.redis.common.core.JsonResult;
import com.zhang.redis.entity.request.SaveAndUpdateUserRequest;
import com.zhang.redis.entity.response.SaveOrUpdateUserDto;

public interface CookUserService {


    SaveOrUpdateUserDto saveOrUpdateUser(SaveAndUpdateUserRequest saveAndUpdateUserRequest);
}
