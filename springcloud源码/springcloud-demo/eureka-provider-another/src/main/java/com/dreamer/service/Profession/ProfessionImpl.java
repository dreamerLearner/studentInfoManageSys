package com.dreamer.service.Profession;

import com.dreamer.mapper.ProfessionMapper;
import com.dreamer.pojo.Profession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionImpl implements ProfessionService{
    @Autowired
    private ProfessionMapper professionMapper;


    @Override
    public List<Profession> getAllProfession() {
        return professionMapper.getAllProfession();
    }

}
