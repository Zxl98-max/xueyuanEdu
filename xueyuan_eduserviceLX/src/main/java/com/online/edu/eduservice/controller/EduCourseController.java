package com.online.edu.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.commonLx.R;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.Query.QueruCourse;
import com.online.edu.eduservice.entity.forn.CourseInfo;
import com.online.edu.eduservice.entity.forn.CourseInfoFrom;
import com.online.edu.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-06-07
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    //添加课程信息
    @PostMapping
    public R addcourseInfo(@RequestBody CourseInfoFrom courseInfoFrom) {
        String courseId = eduCourseService.insertCourseInfo(courseInfoFrom);

        return R.ok().data("courseId",courseId);
    }

    //根据课程id查询课程信息
    @GetMapping("getCourseInfo/{id}")
   public R getCourseInfo(@PathVariable String id){
         CourseInfoFrom courseInfoFrom =eduCourseService.getIdCourse(id);

        return R.ok().data("courseInfoFrom",courseInfoFrom);
    }
    //修改课程方法
    @PostMapping("updateCourseInfo/{id}")
    public R updateCourseInfo(@PathVariable String id,@RequestBody CourseInfoFrom courseInfoFrom){
        Boolean flag= eduCourseService.updateCourse(courseInfoFrom);
        if (flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }
    //课程列表  完善  带条件查询  课程名称  价格
    @GetMapping("listCourse")
    public R getCourseList(){
        List<EduCourse> list = eduCourseService.list(null);
        return R.ok().data("items",list);

    }
    //删除课程  涉及到多个表
    @DeleteMapping("deleteCourse/{id}")
    public R deleteCourse(@PathVariable String id){
        boolean flag = eduCourseService.removeCourseId(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }

    }
    //根据课程id查询课程详细信息
    @GetMapping("getAllCourseInfo/{courseId}")
    public R getAllCourseInfo(@PathVariable String courseId){
        CourseInfo courseInfo = eduCourseService.getCourseInfoAll(courseId);
        return R.ok().data("courseInfo",courseInfo);
    }





}

