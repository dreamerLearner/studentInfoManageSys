package com.dreamer.service.ManagerService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamer.mapper.ManagerMapper;
import com.dreamer.pojo.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerImpl extends ServiceImpl<ManagerMapper,Manager> implements ManagerService{
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public List<Manager> getAllManager() {
        return managerMapper.getAllManager();
    }

    @Override
    public int addManager(Manager manager) {
        if(isExitManager(manager.getName(),manager.getAccount()).isEmpty()){
            return managerMapper.addManager(manager);
        }else {
            return -1;
        }
    }

    @Override
    public int deleteManager(int id) {
        return managerMapper.deleteManager(id);
    }

    @Override
    public int updateManager(Manager manager) {
        return managerMapper.updateManager(manager);
    }

    @Override
    public Manager queryByAccountAndPwd(String account, String password) {
        return managerMapper.queryByAccountAndPwd(account,password);
    }
    @Override
    public List<String> isExitManager(String name,String account) {
        return managerMapper.isExitManager(name,account);
    }
}
