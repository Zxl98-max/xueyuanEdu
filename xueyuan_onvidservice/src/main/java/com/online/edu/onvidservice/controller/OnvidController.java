package com.online.edu.onvidservice.controller;

import com.online.edu.commonLx.R;
import com.online.edu.onvidservice.service.VidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/onvidservice/vod")
@CrossOrigin
public class OnvidController {

    @Autowired
    private VidService vidService;

    //实现上传阿里云服务器的方法
    @PostMapping("upload")
    public R uploadAliyunVideo(MultipartFile file){
        String videoId = vidService.uploadVideoAlyun(file);
        return R.ok().data("videoId",videoId);
    }
    //实现删除视频的方法
    @DeleteMapping("{videoId}")
    public R deleteVideoIdAliyun(@PathVariable String videoId){
         vidService.deleteAliyunVideoId(videoId);
        return R.ok();
    }

}
