package com.dreamer.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import com.dreamer.dto.UserDTO;
import com.dreamer.pojo.Manager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.dreamer.util.RedisConstants.LOGIN_USER_KEY;
import static com.dreamer.util.RedisConstants.LOGIN_USER_TTL;

/**
 * @author dreamer
 * @creater 2022/6/18 0018 12:50
 */
@Slf4j
public class GetToken {
    private StringRedisTemplate stringRedisTemplate;
    public GetToken(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }
    public String getToken(Manager manager){
        log.info("manager为{}",manager);
        UserDTO userDTO = BeanUtil.copyProperties(manager, UserDTO.class);
        log.info("userDTO为{}",userDTO);
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO, new HashMap<>(),
                CopyOptions.create().setFieldValueEditor((fieldName, fieldVal) -> fieldVal.toString()));
//        userMap.put("id",String.valueOf(userMap.get("id")));
        // 生成token令牌
        log.info("userMap为{}",userMap);
        String token = UUID.randomUUID().toString(true);
        log.info("令牌为{}", token);
        // 存储信息manager（hash）
        stringRedisTemplate.opsForHash().putAll(LOGIN_USER_KEY + token, userMap);
        stringRedisTemplate.expire(LOGIN_USER_KEY + token, LOGIN_USER_TTL, TimeUnit.MINUTES);

        // 返回token

        log.info("userDTO为{}", userDTO.getName());
        return token;
    }

}
