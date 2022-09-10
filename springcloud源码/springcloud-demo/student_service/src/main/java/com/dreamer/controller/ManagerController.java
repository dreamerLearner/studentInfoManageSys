package com.dreamer.controller;

import com.dreamer.pojo.Manager;
import com.dreamer.pojo.data.Info;
import com.dreamer.service.ManagerService.ManagerService;
import com.dreamer.util.MD5;
import com.dreamer.util.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @PostMapping("/addManager")
    public Info addManager(@RequestBody Manager newManager){
        newManager.setPassword(new MD5().getMD5Str(newManager.getPassword()));
        log.info(String.valueOf(newManager.getPhone()));
        if(RegexUtils.isPhoneInvalid(String.valueOf(newManager.getPhone()))){
            return new Info(406,"手机号格式有误",null);
        }
        Manager phone = managerService.query().eq("phone", newManager.getPhone()).one();
        log.info("phone为{}",phone);
        if(phone==null){
            int addResult = managerService.addManager(newManager);
            if(addResult == 1){
                return new Info(200,"添加成功",null);
            }else if(addResult == -1){
                return new Info(406,"该管理员已存在",null);
            }else {
                return new Info(406,"出现错误，请联系工作人员",null);
            }
        }else {
            return new Info(406,"手机号已被注册",null);
        }

    }
}
