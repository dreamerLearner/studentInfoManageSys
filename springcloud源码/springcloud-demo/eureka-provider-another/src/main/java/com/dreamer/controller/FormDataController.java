package com.dreamer.controller;

import com.dreamer.pojo.Department;
import com.dreamer.pojo.Profession;
import com.dreamer.pojo.data.Info;
import com.dreamer.service.Department.DepartmentService;
import com.dreamer.service.Profession.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FormDataController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ProfessionService professionService;

    @PostMapping("/StudentForm")
    public List<Info> StudentForm(){
        List<Info> studentFormInfo = new ArrayList<>();
        List<Department> allDepartment = departmentService.getAllDepartment();
        List<Profession> allProfession = professionService.getAllProfession();
        if(allDepartment!=null && allProfession!=null){
            Info department = new Info(200, "请求部门信息成功",
                    departmentService.getAllDepartment());
            Info profession = new Info(200, "请求专业信息成功",
                    professionService.getAllProfession());
            studentFormInfo.add(department);
            studentFormInfo.add(profession);
            return studentFormInfo;
        }else {
            studentFormInfo.add(new Info(406,"请求失败",null));
            return studentFormInfo;
        }
    }

}
