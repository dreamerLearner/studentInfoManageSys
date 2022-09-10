package com.dreamer.service.ManagerService;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamer.pojo.Manager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerService extends IService<Manager> {
    List<Manager> getAllManager();

    int addManager(Manager manager);

    int deleteManager(@Param("id") int id);

    int updateManager(Manager manager);

    Manager queryByAccountAndPwd(@Param("account") String account, @Param("password") String password);

    List<String> isExitManager(@Param("name") String name, @Param("account") String account);
}
