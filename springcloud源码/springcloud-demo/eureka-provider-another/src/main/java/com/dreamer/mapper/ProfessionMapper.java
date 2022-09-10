package com.dreamer.mapper;

import com.dreamer.pojo.Profession;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProfessionMapper {
    List<Profession> getAllProfession();
}
