package com.zhang.redis.entity.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveOrUpdateUserDto {

    /**
     * 操作成功
     */
    private Boolean success;

}
