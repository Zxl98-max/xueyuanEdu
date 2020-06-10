package com.online.edu.eduservice.entity.Query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class QueruTeacher {

    private String name;//名字
    private String level;//讲师头衔
    private String begin;//开始时间
    private String end;//结束时间
}