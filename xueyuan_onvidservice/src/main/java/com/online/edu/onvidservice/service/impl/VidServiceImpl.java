package com.online.edu.onvidservice.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.online.edu.onvidservice.service.VidService;
import com.online.edu.onvidservice.utils.AliyunVodSDKUtils;
import com.online.edu.onvidservice.utils.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VidServiceImpl implements VidService {

    //上传视频  返会视频id
    @Override
    public String uploadVideoAlyun(MultipartFile file) {
        try {
            //获取上传文件名称
            //视频名称.mp4
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);

            String title = fileName.substring(0,fileName.lastIndexOf("."));
            System.out.println(ConstantPropertiesUtil.ACCESS_KEY_ID);
            System.out.println(ConstantPropertiesUtil.ACCESS_KEY_SECRET);

            UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET,
                    title, fileName, file.getInputStream());
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return videoId;
        }catch(Exception e) {
            return null;
        }
    }

    //删除阿里云视频
    @Override
    public void deleteAliyunVideoId(String videoId) {
        try{
            //初始化
            DefaultAcsClient client =
                    AliyunVodSDKUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            //创建一个删除视频请求对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //设置删除视频id
            request.setVideoIds(videoId);
            //调用方法删除
            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.print("RequestId = " + response.getRequestId() + "\n");
        }catch (Exception e){

        }

    }



}
