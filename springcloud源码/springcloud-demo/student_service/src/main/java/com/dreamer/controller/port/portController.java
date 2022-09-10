package com.dreamer.controller.port;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dreamer
 * @creater 2022/6/20 0020 13:48
 */
@RestController
public class portController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/port")
    public String getPort(){
        return "Hello world, I 'm from port: " + port;
    }

}
