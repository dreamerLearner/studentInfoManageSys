package com.dreamer.controller;

import com.dreamer.pojo.Profession;
import com.dreamer.pojo.data.Info;
import com.dreamer.service.Profession.ProfessionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dreamer
 * @creater 2022/6/21 0021 13:21
 */
@RestController
@RequestMapping("profession")
public class ProfessionController {
    @Resource
    private ProfessionService professionService;

    @GetMapping("/getAllProfession")
    public Info getAllProfession(){
        List<Profession> allProfession = professionService.getAllProfession();
        if(allProfession!=null){
            return Info.ok(200,"专业获取成功",allProfession);
        }else {
            return Info.fail(406,"专业获取失败");
    }

    }
}
