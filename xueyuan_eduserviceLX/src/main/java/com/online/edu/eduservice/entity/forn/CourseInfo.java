package com.online.edu.eduservice.entity.forn;

import lombok.Data;

//用于封装课程详细信息的实体类
@Data
public class CourseInfo {
    private String id;
    private String title;
    private String cover;
    private String price;
    private String description;
    private String teachername;
    private String levelOne;
    private String levelTwo;


}
