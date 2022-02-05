package com.zhang.redis.service;

import com.zhang.redis.common.core.JsonResult;
import com.zhang.redis.entity.dto.UserDTO;
import com.zhang.redis.entity.request.SaveAndUpdateUserRequest;
import com.zhang.redis.entity.response.SaveOrUpdateUserDto;

public interface CookUserService {


    /**
     *  增删用户
     * @param saveAndUpdateUserRequest
     * @return
     */
    SaveOrUpdateUserDto saveOrUpdateUser(SaveAndUpdateUserRequest saveAndUpdateUserRequest);

    /**
     *  查找用户
     * @param id
     * @return
     */
    UserDTO getUserInfoById(Long id);
}
