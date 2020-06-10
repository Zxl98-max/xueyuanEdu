package com.online.edu.eduservice.handle;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtil  implements InitializingBean {
    //服务器启动时候加载这个类读取配置文件

    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.keyid}")
    private String keyid;
    @Value("${aliyun.oss.file.keysecret}")
    private String keysecret;
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketname;
    //读取文件夹
    @Value("${aliyun.oss.file.host}")
    private String host;

    //定义常量  为了使用
    public static  String ENDPOINT;
    public static  String KEYID;
    public static  String KEYSECRET;
    public static  String BUCKETNAME;
    public static  String HOST;

    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT=endpoint;
        KEYID=keyid;
        KEYSECRET=keysecret;
        BUCKETNAME=bucketname;
        HOST=host;
    }
}
