package com.dreamer.mapper.DividePage;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dreamer.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DividePageMapper extends BaseMapper<Student> {

    IPage<Student> StudentSelectPageText(Page<Student> page);
}
