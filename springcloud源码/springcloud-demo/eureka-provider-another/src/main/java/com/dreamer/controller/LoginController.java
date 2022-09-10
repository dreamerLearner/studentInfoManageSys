package com.dreamer.controller;

import cn.hutool.core.util.RandomUtil;
import com.dreamer.dto.LoginFormDTO;
import com.dreamer.pojo.Manager;
import com.dreamer.pojo.data.Info;
import com.dreamer.service.ManagerService.ManagerService;
import com.dreamer.util.GetToken;
import com.dreamer.util.MD5;
import com.dreamer.util.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.dreamer.util.RedisConstants.LOGIN_CODE_KEY;
import static com.dreamer.util.RedisConstants.LOGIN_CODE_TTL;

@RestController
@Slf4j
public class LoginController {
    @Autowired
    private ManagerService managerService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/loginWithPwd")
    public Info loginWithPwd(@RequestBody Map<String, String> managerInfo) throws SQLException {
        String account = managerInfo.get("account");
        String password = managerInfo.get("password");
        Manager loginManager = managerService.queryByAccountAndPwd(account, new MD5().getMD5Str(password));

        Info info = null;
        if (loginManager != null) {
            String token = new GetToken(stringRedisTemplate).getToken(loginManager);
            if(token==null){
                info = new Info(406,"页面出错，请联系工作人员",null);
            }else {
                ArrayList<Object> data = new ArrayList<>();
                data.add(loginManager.getName());
                data.add(token);
                info = Info.ok(200, "登录成功", data);
            }
        } else {
            info = new Info(406, "账号或密码错误", null);
        }
        return info;
    }
    @GetMapping("/sendCode")
    public Info sendCode(@RequestParam("phone") String phone){
        if(RegexUtils.isPhoneInvalid(phone)){
            return new Info(406,"手机号格式有误",null);
        }
        String code = RandomUtil.randomNumbers(6);
//        session.setAttribute("code",code);
        // code存储到redis中
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY+phone,code,LOGIN_CODE_TTL, TimeUnit.MINUTES);

        log.info("发送验证码成功，验证码:{}",code);

        return new Info(200,"获取成功",code);
    }

    @PostMapping("/loginWithPhone")
    public Info loginWithPhone(@RequestBody LoginFormDTO loginForm) {
        //验证手机号
        String phone = loginForm.getPhone();
        log.info("登录信息为{}", loginForm);
        if (RegexUtils.isPhoneInvalid(phone)) {
            return new Info(406, "手机号格式有误", null);
        }
        //验证验证码
//        Object realCode = session.getAttribute("code");
        //从redis中获取code
        String code = loginForm.getCode();
        String realCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone);

//        log.debug("验证码为{}",code);
//        log.debug("缓存验证码为{}",realCode);
        if (null == realCode || !code.equals(realCode)) {
            return new Info(406, "验证码错误", null);
        }
        //判断用户是否存在
        Manager manager = managerService.query().eq("phone", phone).one();
        if (manager == null) {
            return new Info(404, "用户不存在", null);
        }
        String token = new GetToken(stringRedisTemplate).getToken(manager);
        System.out.println("token为"+token);
        if(token==null){
            return new Info(406,"页面出错，请联系工作人员",null);
        }
        ArrayList<Object> data = new ArrayList<>();
        data.add(manager.getName());
        data.add(token);
//        session.setAttribute("user", userDTO);
        return new Info(200, "登录成功", data);
    }

}
