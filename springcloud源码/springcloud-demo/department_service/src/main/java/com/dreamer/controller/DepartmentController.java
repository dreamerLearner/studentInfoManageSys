package com.dreamer.controller;

import com.dreamer.pojo.Department;
import com.dreamer.pojo.data.Info;
import com.dreamer.service.Department.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dreamer
 * @creater 2022/6/21 0021 13:29
 */
@RestController
@RequestMapping("department")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @GetMapping("getAllDepartment")
    public Info getAllDepartment() {
        List<Department> allProfession = departmentService.getAllDepartment();
        if (allProfession != null) {
            return Info.ok(200, "系名获取成功", allProfession);
        } else {
            return Info.fail(406, "系名获取失败");
        }
    }

}
