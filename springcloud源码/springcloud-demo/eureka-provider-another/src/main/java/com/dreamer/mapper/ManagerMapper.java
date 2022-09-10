package com.dreamer.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dreamer.pojo.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ManagerMapper extends BaseMapper<Manager> {

    List<Manager> getAllManager();

    int addManager(Manager manager);

    int deleteManager(@Param("id") int id);

    int updateManager(Manager manager);

    Manager queryByAccountAndPwd(@Param("account") String account, @Param("password") String password);
    List<String> isExitManager(@Param("name") String name, @Param("account") String account);
}
