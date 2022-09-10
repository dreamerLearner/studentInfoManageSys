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
@RequestMapping("/FormData")
public class FormDataController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ProfessionService professionService;

    @PostMapping("/StudentForm")
    public List<Info> StudentForm() {
        List<Info> studentFormInfo = new ArrayList<>();
        Info allDepartment = departmentService.getAllDepartment();
        Info allProfession = professionService.getAllProfession();
        studentFormInfo.add(allDepartment);
        studentFormInfo.add(allProfession);
        return studentFormInfo;
    }
}
