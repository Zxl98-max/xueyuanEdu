package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.dto.EduchapterDto;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-06-09
 */
public interface EduChapterService extends IService<EduChapter> {

    //根据课程id删除章节
    void deleteChapterByCourseId(String id);
    //根据课程id查询章节和小节数据
    List<EduchapterDto> getChapterVideoListByCourseId(String courseId);

    boolean removeChapterById(String chapterId);
}
