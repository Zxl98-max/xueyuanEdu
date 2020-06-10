package com.online.edu.eduservice.service.impl;

import com.online.edu.eduservice.entity.EduCourseDescription;
import com.online.edu.eduservice.mapper.EduCourseDescriptionMapper;
import com.online.edu.eduservice.service.EduCourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-06-07
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {

    //根据课程id删除描述
    @Override
    public void deleteDescriptionByCourseId(String id) {
        baseMapper.deleteById(id);
    }
}
