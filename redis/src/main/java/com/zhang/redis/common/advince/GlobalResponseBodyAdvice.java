package com.zhang.redis.common.advince;

import com.zhang.redis.common.core.JsonMap;
import com.zhang.redis.common.core.JsonResult;
import com.zhang.redis.common.util.JsonUtil;
import com.zhang.redis.common.util.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice
@Order
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 是否支持advice功能
     *
     * @param returnType
     * @param converterType
     * @return true:支持  false:不支持
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        Class<?> declaringClass = returnType.getDeclaringClass();
        if (declaringClass.equals(BasicErrorController.class)) {
            return false;
        }

        return true;
    }

    /**
     * 处理response的具体业务方法
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if (!selectedContentType.equalsTypeAndSubtype(MediaType.APPLICATION_JSON)) {
            return body;
        }

        if (body instanceof JsonResult || body instanceof JsonMap) {
            return body;
        } else if (body instanceof String) {
            try {
                HttpServletResponse httpServletResponse = ServletUtil.getResponse();
                if (httpServletResponse != null) {
                    ServletUtil.writeJsonMessage(httpServletResponse, JsonResult.buildSuccess(body));
                    return null;
                }
            } catch (Exception e) {
                log.warn("响应字符串对象给前端异常", e);
            }

            return JsonUtil.object2Json(JsonResult.buildSuccess(body));
        }

        return JsonResult.buildSuccess(body);
    }

}
