package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {
    @Excel(name = "编号")
    private String id;
    @Excel(name = "手机号")
    private String phone_num;
    @ExcelIgnore
    private String password;
    @ExcelIgnore
    private String salt;
    @Excel(name = "头像",type = 2 ,width = 40 , height = 20)
    private String head_pic;
    @Excel(name = "真实姓名")
    private String name;
    @Excel(name = "法名")
    private String dharma;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "省份")
    private String province; //省
    @Excel(name = "所在市")
    private String city; //市
    @Excel(name = "个性签名")
    private String sign; //个性签名
    @Excel(name = "状态")
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建日期")
    private Date create_date;
    private String guru_id;//上师id
}

