package com.dreamer.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info {
    private int code;
    private String message;
    private Object data;

    public static Info ok(int code,String message,List<?> data){
        return new Info(code, message, data);
    }
    public static Info fail(int code,String message){
        return new Info(code, message, null);
    }
}
