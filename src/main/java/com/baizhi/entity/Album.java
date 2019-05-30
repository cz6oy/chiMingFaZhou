package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 专辑
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album implements Serializable {
    private String id;
    private String title;
    private Integer score;  //分数
    private String author;
    private String broadcast;   //播音员
    private Integer count;  //集数
    private String brief;   //简介
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_date;
    private String cover_pic; //封面
}
