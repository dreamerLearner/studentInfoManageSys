package com.dreamer.service.DividePage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamer.mapper.DividePage.DividePageMapper;
import com.dreamer.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DividePageImpl extends ServiceImpl<DividePageMapper, Student>
        implements DividePageService{

    @Autowired
    private DividePageMapper dividePageMapper;
    @Override
    public IPage<Student> StudentSelectPageText(Page<Student> page) {
        return dividePageMapper.StudentSelectPageText(page);
    }
}
