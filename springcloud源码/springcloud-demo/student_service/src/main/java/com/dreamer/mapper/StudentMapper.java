package com.dreamer.mapper;

import com.dreamer.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    List<Student> getAllStudent();

    int addStudent(Student student);

    int deleteStudent(@Param("id") int id, @Param("name") String name);

    int updateStudent(Student student);

    List<String> isExitStudent(@Param("id") int id, @Param("name") String name);
}
