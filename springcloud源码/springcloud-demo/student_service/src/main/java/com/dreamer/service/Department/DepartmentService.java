package com.dreamer.service.Department;

import com.dreamer.pojo.Department;
import com.dreamer.pojo.data.Info;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@FeignClient(name = "department-service", fallback = DepartmentServiceClientCallBack.class)
public interface DepartmentService {

    @GetMapping("/department/getAllDepartment")
    Info getAllDepartment();
}
