package com.dreamer.service.StudentService;

import com.dreamer.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudent();

    int addStudent(Student student);

    int deleteStudent(@Param("id") int id, @Param("name") String name);

    int updateStudent(Student student);

    List<String> isExitStudent(@Param("id") int id, @Param("name") String name);
}
