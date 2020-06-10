package com.online.edu.eduservice.controller;


import com.online.edu.commonLx.R;
import com.online.edu.eduservice.entity.dto.EduchapterDto;
import com.online.edu.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-06-09
 */
@RestController
@RequestMapping("/eduservice/chapter")
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    //根据id查询章节小节
    @GetMapping("getChapterVideoList/{courseId}")
    public R getChapterVideoList(@PathVariable String courseId){
        List<EduchapterDto> list= eduChapterService.getChapterVideoListByCourseId(courseId);
        return R.ok().data("items",list);
    }


}

