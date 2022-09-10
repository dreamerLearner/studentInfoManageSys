package com.dreamer.service.Profession;

import com.dreamer.pojo.data.Info;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author dreamer
 * @creater 2022/6/21 0021 21:20
 */
@Service
public class ProfessionServiceClientCallBack implements ProfessionService {
    @Override
    public Info getAllProfession() {
        return Info.fail(503,"获取所有专业名触发熔断降级方法");
    }
}
