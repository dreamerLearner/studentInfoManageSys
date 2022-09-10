package com.dreamer.handler;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.dreamer.dto.UserDTO;
import com.dreamer.util.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.dreamer.util.RedisConstants.*;

@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public TokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        log.info("此处为拦截器");
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())){
            System.out.println("OPTIONS请求，放行");
            return true;
        }
        String token = request.getHeader("Authorization");
        log.info("拦截前token为：{}",token);
        if (StrUtil.isBlank(token)){
            response.setStatus(401);
            return false;
        }
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(LOGIN_USER_KEY + token);
        log.info("userMap为{}",userMap);
        if (userMap.isEmpty()){
            response.setStatus(401);
            return false;
        }
        //从redis中获取user
//        Object user = stringRedisTemplate.opsForHash().get(LOGIN_USER_KEY + token, "userMap");
        //  将userMap转为UserDTO
        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);

        log.info("user值为{}",userMap);
        //保存信息到TheadLocal
        UserHolder.saveUser(userDTO);
        //刷新缓存时间
        stringRedisTemplate.expire(LOGIN_USER_KEY+token,LOGIN_CODE_TTL, TimeUnit.MINUTES);
        //放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
