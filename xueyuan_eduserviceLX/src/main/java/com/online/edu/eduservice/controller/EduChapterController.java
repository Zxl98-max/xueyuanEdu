package com.online.edu.eduservice.controller;


import com.online.edu.commonLx.R;
import com.online.edu.eduservice.entity.EduChapter;
import com.online.edu.eduservice.entity.dto.EduchapterDto;
import com.online.edu.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    //根据id查询章节小节
    @GetMapping("getChapterVideoList/{courseId}")
    public R getChapterVideoList(@PathVariable String courseId){
        List<EduchapterDto> list= eduChapterService.getChapterVideoListByCourseId(courseId);
        return R.ok().data("items",list);
    }
    //添加章节的方法
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        boolean save = eduChapterService.save(eduChapter);
        if(save){
            return R.ok();
        }else{
            return R.error();
        }
    }
    //根据章节的id查询
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return R.ok().data("eduChapter",eduChapter);
    }
    //修改章节的方法
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        boolean flag = eduChapterService.updateById(eduChapter);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }
    //删除章节方法
    @DeleteMapping("deleteChapter/{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){
        boolean result = eduChapterService.removeChapterById(chapterId);
        if (result){
            return R.ok();
        }else {
            return R.error();
        }
    }


}

