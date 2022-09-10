package com.dreamer.mapper;

import com.dreamer.pojo.Profession;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dreamer
 * @creater 2022/6/21 0021 13:23
 */
@Mapper
@Repository
public interface ProfessionMapper{

    List<Profession> getAllProfession();
}
