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
    private String teacherName;//讲师名称
    private String levelOne;//一级分类名称
    private String levelTwo;//二级分类名称


}
