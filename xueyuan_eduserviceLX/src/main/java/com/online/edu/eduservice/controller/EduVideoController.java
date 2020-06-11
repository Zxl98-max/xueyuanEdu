package com.online.edu.eduservice.controller;


import com.online.edu.commonLx.R;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-06-09
 */
@RestController
@RequestMapping("/eduservice/eduvideo")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        boolean save = eduVideoService.save(eduVideo);
        if(save){
            return R.ok();
        }else{
            return R.error();
        }
    }
    //修改
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo ){
        boolean b = eduVideoService.updateById(eduVideo);
        if(b){
            return R.ok();
        }else{
            return R.error();
        }
    }
    //根据id查询
    @GetMapping("getVideo/{videoid}")
    public R getVideo(@PathVariable String videoid){
        EduVideo eduVideo = eduVideoService.getById(videoid);
        return R.ok().data("eduVideo",eduVideo);
    }
    //根据id删除
    @DeleteMapping("deleteVideo/{videoId}")
    public R deleteVideo(@PathVariable String videoId){
        boolean b = eduVideoService.removeVideoById(videoId);
        if(b){
            return R.ok();
        }else{
            return R.error();
        }
    }


}

