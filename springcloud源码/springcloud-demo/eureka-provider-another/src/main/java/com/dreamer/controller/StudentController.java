package com.dreamer.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dreamer.mapper.DividePage.DividePageMapper;
import com.dreamer.pojo.Student;
import com.dreamer.pojo.data.Info;
import com.dreamer.service.StudentService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private DividePageMapper dividePageMapper;


    @GetMapping("/getStudentPage")
    public List<Info> getStudentPage(@RequestParam("current") int current,
                                     @RequestParam("size") int size){
        Page<Student> studentPage = new Page<>(current, size);
        IPage<Student> iPage = dividePageMapper.StudentSelectPageText(studentPage);
        List<Info> studentPageInfo = new ArrayList<>();
        if (iPage.getRecords().size() != 0) {
            studentPageInfo.add(new Info(200,"请求总页数成功",iPage.getPages()));
            studentPageInfo.add(new Info(200,"请求成功",iPage.getRecords()));
        }else{
            studentPageInfo.add(new Info(406,"请求错误",null));
        }
        return studentPageInfo;
    }

    @PostMapping("/addStudent")
    public Info getAllStudent(@RequestBody Student newStudent){
        int addResult = studentService.addStudent(newStudent);
        if(addResult == 1){
            //导入成功
            return new Info(200,"成功导入学生信息",null);
        }else if(addResult == -1){
            //存在学生，添加失败
            return new Info(406,"该学生已存在",null);
        }else {
            //添加失败
            return new Info(406,"导入错误,请联系管理员",null);
        }

    }


    @GetMapping("/deleteStudent")
    public Info deleteStudent(@RequestParam int id, @RequestParam String name){
        int deleteResult = studentService.deleteStudent(id,name);
        if(deleteResult == 1){
            //删除成功
            return new Info(200,"成功删除学生信息",null);
        }else if(deleteResult == -1){
            //不存在学生，删除失败
            return new Info(406,"该学生不存在",null);
        }else {
            //删除失败
            return new Info(406,"删除错误,请联系管理员",null);
        }

    }
    @PostMapping("/updateStudent")
    public Info updateStudent(@RequestBody Student updateStudent){
        int updateResult = studentService.updateStudent(updateStudent);
        if(updateResult == 1){
            //删除成功
            return new Info(200,"成功更新学生信息",null);
        }else if(updateResult == -1){
            //不存在学生，删除失败
            return new Info(406,"该学生不存在",null);
        }else {
            //删除失败
            return new Info(406,"更新错误,请联系管理员",null);
        }
    }



}
