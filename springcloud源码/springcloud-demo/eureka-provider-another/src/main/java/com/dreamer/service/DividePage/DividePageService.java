package com.dreamer.service.DividePage;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dreamer.pojo.Student;

public interface DividePageService {

    IPage<Student> StudentSelectPageText(Page<Student> page);
}
