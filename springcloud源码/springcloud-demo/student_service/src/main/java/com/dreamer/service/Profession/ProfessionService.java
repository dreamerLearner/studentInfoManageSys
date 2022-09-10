package com.dreamer.service.Profession;


import com.dreamer.pojo.Profession;
import com.dreamer.pojo.data.Info;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@FeignClient(name = "profession-service", fallback = ProfessionServiceClientCallBack.class)
public interface ProfessionService {

    @GetMapping("/profession/getAllProfession")
    Info getAllProfession();
}
