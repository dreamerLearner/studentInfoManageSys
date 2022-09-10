package com.dreamer.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {
    @TableId
    private int id;
    private String name;
    private String account;
    private String password;
    private long phone;
}
