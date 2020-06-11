package com.online.edu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.Query.QueruCourse;
import com.online.edu.eduservice.entity.forn.CourseInfo;
import com.online.edu.eduservice.entity.forn.CourseInfoFrom;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-06-07
 */
public interface EduCourseService extends IService<EduCourse> {

    String insertCourseInfo(CourseInfoFrom courseInfoFrom);

    CourseInfoFrom getIdCourse(String id);

    Boolean updateCourse(CourseInfoFrom courseInfoFrom);

    void pageListCourse(Page<EduCourse> coursePage, QueruCourse queruCourse);

    boolean removeCourseId(String id);

    //查询课程所有s信息
    CourseInfo getCourseInfoAll(String courseId);
}
