package com.zhang.redis.convert;

import com.zhang.redis.entity.dto.UserDTO;
import org.mapstruct.*;
import com.zhang.redis.entity.UserDO;
import com.zhang.redis.entity.request.SaveAndUpdateUserRequest;

@Mapper(componentModel = "spring")
public interface UserConvert {

    /**
     * 对象转换
     * @param request 对象
     * @return 对象
     */
    @Mappings({
            @Mapping(target = "createTime", ignore = true),
            @Mapping(target = "createUser", ignore = true),
            @Mapping(target = "updateTime", ignore = true),
            @Mapping(target = "updateUser", ignore = true)
    })
    UserDO convertCookbookUserDO(SaveAndUpdateUserRequest request);

    /**
     * 对象转换
     * @param cookbookUserDO 对象
     * @return 对象
     */
    UserDTO convertCookbookUserDTO(UserDO cookbookUserDO);

}
