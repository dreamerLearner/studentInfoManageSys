package com.dreamer.service.StudentService;

import com.dreamer.mapper.StudentMapper;
import com.dreamer.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentImpl implements StudentService{
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getAllStudent() {
        return studentMapper.getAllStudent();
    }

    @Override
    public int addStudent(Student student) {
        if(isExitStudent(student.getId(),student.getName()).isEmpty()){
            return studentMapper.addStudent(student);
        }else {
            return -1;
        }
    }

    @Override
    public int deleteStudent(int id,String name) {
        if(!isExitStudent(id,name).isEmpty()){
            return studentMapper.deleteStudent(id,name);
        }else{
            return -1;
        }

    }

    @Override
    public int updateStudent(Student student) {
        if(!isExitStudent(student.getId(),student.getName()).isEmpty()){
            return studentMapper.updateStudent(student);
        }else {
            return -1;
        }

    }

    @Override
    public List<String> isExitStudent(int id, String name) {
        return studentMapper.isExitStudent(id,name);
    }


}
