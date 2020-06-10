package com.online.edu.eduservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.commonLx.R;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.Query.QueruTeacher;
import com.online.edu.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-05-31
 */
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;


    //模拟登陆
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }
    @GetMapping("info")
    public R info() {
        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://wpimg.wallstcn.com/f778738c-e47f8-b634-56703b4acafe.gif");
    }


    //1.查询所有讲师
    @GetMapping
    public R getAllListTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }
    ////2.逻辑删除讲师
    @DeleteMapping("{id}")
    public Boolean DeleteById(@PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        return b;
    }


    //3.分页查询讲师列表方法
    @GetMapping("pagelist/{page}/{limit}")
    public R getPageTeacherList(@PathVariable long page,
                                  @PathVariable long limit){
        Page<EduTeacher> teacherPage=new Page<>(page,limit);
        eduTeacherService.page(teacherPage,null);
        List<EduTeacher> records = teacherPage.getRecords();//获取分页数据
        long total = teacherPage.getTotal();//总记录数

        return R.ok().data("total",total).data("records",records);

    }

    //4.条件分页查询
    @PostMapping("moreCondtionPagtList/{page}/{limit}")
    public R getgetMoreCondtionPageList(@PathVariable Long page, @PathVariable Long limit,
                                        @RequestBody(required = false) QueruTeacher queruTeacher){
        Page<EduTeacher> teacherPage=new Page<>(page,limit);
        eduTeacherService.pagtListCondition(teacherPage,queruTeacher);
        long total = teacherPage.getTotal();//总记录数
        List<EduTeacher> records = teacherPage.getRecords();//条件查询数据
        return R.ok().data("total",total).data("records",records);
    }


    //5.添加讲师功能
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }


    }


    //6.根据id查询
    @GetMapping("getTeacherById/{id}")
    public R getTeacherById(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("eduTeacher",eduTeacher);
    }


    //7.修改
    @PostMapping("update/{id}")
    public R UpdateTeacher(@PathVariable String id,@RequestBody EduTeacher eduTeacher){
        eduTeacher.setId(id);
        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b){
            return R.ok();
        }else{
            return R.error();
        }

    }


}

