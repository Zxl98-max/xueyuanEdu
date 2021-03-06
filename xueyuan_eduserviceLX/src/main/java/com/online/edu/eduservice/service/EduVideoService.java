package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-06-09
 */
public interface EduVideoService extends IService<EduVideo> {

    //根据课程id删除小节
    void deleteVideoByCourseId(String id);

    boolean removeVideoById(String videoId);
}
