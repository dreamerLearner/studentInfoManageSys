package com.dreamer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    private int id;
    private String CourseName;
    private int periodTime;
    private String teacherName;
    private String CourseType;
    private Date date;
    private String address;
}
