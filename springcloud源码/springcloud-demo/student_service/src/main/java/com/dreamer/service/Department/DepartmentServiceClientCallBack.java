package com.dreamer.service.Department;

import com.dreamer.pojo.data.Info;
import org.springframework.stereotype.Component;

/**
 * @author dreamer
 * @creater 2022/6/21 0021 19:27
 */
@Component
public class DepartmentServiceClientCallBack implements DepartmentService{

    /**
     * 熔断降级方法
     * @return
     */
    public Info getAllDepartment() {
        Info info = Info.fail(503,"获取所有系名触发熔断降级方法");
        System.out.println("66666666");
        return info;
    }
}
