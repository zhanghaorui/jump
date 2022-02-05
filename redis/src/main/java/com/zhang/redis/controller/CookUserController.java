package com.zhang.redis.controller;

import com.zhang.redis.common.core.JsonResult;
import com.zhang.redis.entity.request.SaveAndUpdateUserRequest;
import com.zhang.redis.entity.response.SaveOrUpdateUserDto;
import com.zhang.redis.service.CookUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cookUser")
@CrossOrigin
public class CookUserController {

    @Autowired
    private CookUserService cookUserService;


    /**
     * 添加或修改用户
     *
     * @param saveAndUpdateUserRequest
     * @return
     */
    @PostMapping("/saveOrUpdateUser")
    public JsonResult<SaveOrUpdateUserDto> saveOrUpdateUser(@RequestBody SaveAndUpdateUserRequest saveAndUpdateUserRequest) {
        SaveOrUpdateUserDto dto = cookUserService.saveOrUpdateUser(saveAndUpdateUserRequest);
        return JsonResult.buildSuccess(dto);
    }
}
