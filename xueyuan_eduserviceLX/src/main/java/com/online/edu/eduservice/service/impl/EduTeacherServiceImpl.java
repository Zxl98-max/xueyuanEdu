package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.Query.QueruTeacher;
import com.online.edu.eduservice.mapper.EduTeacherMapper;
import com.online.edu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-05-31
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pagtListCondition(Page<EduTeacher> teacherPage, QueruTeacher queruTeacher) {
        if(queruTeacher == null) {
            //直接查询分页，不进行条件操作
            baseMapper.selectPage(teacherPage,null);
            return;
        }
        //如果queryTeacher不为空
        //从queryTeacher对象里面获取出条件值
        String name = queruTeacher.getName();
        String level = queruTeacher.getLevel();

        String begin = queruTeacher.getBegin();
        String end = queruTeacher.getEnd();
        //判断条件值是否有，如果有拼接条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(name)) {
            //拼接条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {

            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }
        //条件查询带分页
        baseMapper.selectPage(teacherPage,wrapper);
    }
}
