package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduChapter;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.EduCourseDescription;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.Query.QueruCourse;
import com.online.edu.eduservice.entity.forn.CourseInfo;
import com.online.edu.eduservice.entity.forn.CourseInfoFrom;
import com.online.edu.eduservice.handle.EduException;
import com.online.edu.eduservice.mapper.EduCourseMapper;
import com.online.edu.eduservice.service.EduChapterService;
import com.online.edu.eduservice.service.EduCourseDescriptionService;
import com.online.edu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-06-07
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    //描述表
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    //章节表
    @Autowired
    private EduChapterService eduChapterService;
    //小结表
    @Autowired
    private EduVideoService eduVideoService;


    //添加课程基本信息
    @Override
    public String insertCourseInfo(CourseInfoFrom courseInfoFrom) {
        //把courserInfofrom数据复制到课程实体类
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoFrom,eduCourse);

        //添加课程基本信息
       int result= baseMapper.insert(eduCourse);
       //判断添加是否成功添加描述
       if(result<=0){
           //抛出异常
           throw new EduException(20001,"添加课程失败");
       }
        //把courserInfofrom数据复制到课程实体类
        EduCourseDescription eduCourseDescription=new EduCourseDescription();

        String description = courseInfoFrom.getDescription();
        eduCourseDescription.setDescription(description);
        String courseId = eduCourse.getId();
        eduCourseDescription.setId(courseId);

        boolean save = eduCourseDescriptionService.save(eduCourseDescription);

        //添加课程描述到课程描述表
        if(save){
            return courseId;
        }else{
            return null;
        }

    }

    @Override
    public CourseInfoFrom getIdCourse(String id) {
        //根据id查询
        //1.根据
        EduCourse eduCourse = baseMapper.selectById(id);
        //把他复制到FROM的dto
        if(eduCourse==null){
            throw new EduException(20001,"没有课程信息");
        }
        CourseInfoFrom courseInfoFrom=new CourseInfoFrom();
        BeanUtils.copyProperties(eduCourse,courseInfoFrom);

        //描述表
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(id);
        String description = eduCourseDescription.getDescription();
        courseInfoFrom.setDescription(description);


        return courseInfoFrom;
    }

    //修改课程和课程描述表
    @Override
    public Boolean updateCourse(CourseInfoFrom courseInfoFrom) {

        //1.修改课程信息表
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoFrom,eduCourse);
        int i = baseMapper.updateById(eduCourse);
        if(i==0){
            throw  new EduException(20001,"修改课程失败");
        }

        //2.修改课程描述表
        EduCourseDescription eduCourseDescription=new EduCourseDescription();
        String id = courseInfoFrom.getId();
        eduCourseDescription.setId(id);
        String description = courseInfoFrom.getDescription();
        eduCourseDescription.setDescription(description);
        boolean b = eduCourseDescriptionService.updateById(eduCourseDescription);

        return b;
    }

    //条件查询

    @Override
    public void pageListCourse(Page<EduCourse> coursePage, QueruCourse queruCourse) {
        //条件为空  直接查询所有
        if(queruCourse==null){
            baseMapper.selectPage(coursePage,null);
        }
        //创建条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //条件部位空，取值查询
        String title = queruCourse.getTitle();//名字
        if(!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }
        String price = queruCourse.getPrice();//价格
        if(!StringUtils.isEmpty(price)){
            wrapper.eq("price",price);
        }
        baseMapper.selectPage(coursePage,wrapper);
    }

    //根据课程id删除课程
    @Override
    public boolean removeCourseId(String id) {
        //1 根据课程id删除章节
        eduChapterService.deleteChapterByCourseId(id);

        //2 根据课程id删除小节
        eduVideoService.deleteVideoByCourseId(id);

        //3 根据课程id删除课程描述
        eduCourseDescriptionService.deleteDescriptionByCourseId(id);

        //4 删除课程本身
        int result = baseMapper.deleteById(id);
        return result>0;
    }

    //根据课程id查询详情信息
    @Override
    public CourseInfo getCourseInfoAll(String courseId) {
        CourseInfo courseInfoAll = baseMapper.getCourseInfoAllform(courseId);
        return courseInfoAll;
    }


}
