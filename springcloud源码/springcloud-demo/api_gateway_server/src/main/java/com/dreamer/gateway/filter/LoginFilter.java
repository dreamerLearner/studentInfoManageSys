package com.dreamer.gateway.filter;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;

import com.dreamer.gateway.dto.UserDTO;
import com.dreamer.gateway.util.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.dreamer.gateway.util.RedisConstants.LOGIN_CODE_TTL;
import static com.dreamer.gateway.util.RedisConstants.LOGIN_USER_KEY;

/**
 * 自定义一个全局过滤器
 *      实现 globalfilter, ordered
 */
@Component
@Slf4j
public class LoginFilter implements GlobalFilter, Ordered {
    private static AntPathMatcher matcher = new AntPathMatcher();

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 执行过滤器中的业务逻辑
     * @param exchange
     * @param chain
     * @return
     */
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        System.out.println(needLogin(request.getPath().toString()));
        System.out.println(request.getPath().toString());

        if (needLogin(request.getPath().toString())){
            return chain.filter(exchange);
        }


        log.info("此处为拦截器");
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())){
            System.out.println("OPTIONS请求，放行");
            return chain.filter(exchange);
        }
        String token = request.getHeaders().getFirst("Authorization");
        log.info("拦截前token为：{}",token);
        if (StrUtil.isBlank(token)){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(LOGIN_USER_KEY + token);
        log.info("userMap为{}",userMap);
        if (userMap.isEmpty()){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
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
        return chain.filter(exchange);
    }

    public static boolean needLogin(String uri){
        ArrayList<String> uriList = new ArrayList<>();
        uriList.add("/student-service/manager/loginWithPwd");
        uriList.add("/student-service/manager/loginWithPhone");
        for (String pattern : uriList) {
            if (matcher.match(pattern, uri)){
                return true;
            }
        }
        return false;
    }

    public int getOrder() {
        return 0;
    }
}
