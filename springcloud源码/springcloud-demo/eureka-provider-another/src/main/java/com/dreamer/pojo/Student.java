package com.dreamer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private int id;
    private String name;
    private int sex;
    private String profession;  //根据student表的专业id去找专业名
    private String department;  //根据student表的院系id去找院系名
    private String grade;
}
