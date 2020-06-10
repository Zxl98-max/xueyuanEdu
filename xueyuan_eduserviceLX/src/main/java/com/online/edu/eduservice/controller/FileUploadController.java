package com.online.edu.eduservice.controller;

//上传文件到阿里云

import com.aliyun.oss.OSSClient;
import com.online.edu.commonLx.R;
import com.online.edu.eduservice.handle.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@RestController    //控制器注解
@RequestMapping("/eduservice/oss")     //请求路径
@CrossOrigin      //跨域
public class FileUploadController {

    //上传讲师头像方法
    @PostMapping("upload")
    public R uploadTeacherImg(@RequestParam("file") MultipartFile file,@RequestParam(value = "host",required = false) String host){

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtil.ENDPOINT;
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtil.KEYID;
        String accessKeySecret = ConstantPropertiesUtil.KEYSECRET;
        String yourBucketName=ConstantPropertiesUtil.BUCKETNAME;
        try{
            //1.获取上传文件  file

            //2.获取文件上传文件名称  获取上传文件输入流
            String filename = file.getOriginalFilename();
            //载文件名称前  添加uuid
            String uuid= UUID.randomUUID().toString();
            filename=uuid+filename;
            //获取当前时间  2019/03/12
            String filePath=new DateTime().toString("yyyy/MM/dd");


            String hostname=ConstantPropertiesUtil.HOST;
            //如何是头像  host为bull  封面 host优质
            if (!StringUtils.isEmpty(host)) {
                hostname=host;
            }
            //拼接文件完整名称
            filename=filePath+"/"+hostname+"/"+filename;

            InputStream inputStream = file.getInputStream();


            // 创建OSSClient实例。
            OSSClient ossClient=new OSSClient(endpoint, accessKeyId, accessKeySecret);

// 上传文件流。   bucket 名称  文件明  流

            ossClient.putObject(yourBucketName, filename, inputStream);

// 关闭OSSClient。
            ossClient.shutdown();





            //返会图片路径
            String path="http://"+yourBucketName+"."+endpoint+"/"+filename;

            return R.ok().data("imgurl",path);

        }catch (Exception e){
            e.printStackTrace();
            return R.error();

        }


    }


}
