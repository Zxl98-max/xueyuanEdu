package com.online.edu.eduservice.mapper;

import com.online.edu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.online.edu.eduservice.entity.forn.CourseInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-06-07
 */
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    //根据课程id查询课程详情信息

    CourseInfo getCourseInfoAllform(String courseId);

}
