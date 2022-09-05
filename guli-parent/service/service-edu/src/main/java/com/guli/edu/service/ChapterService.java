package com.guli.edu.service;

import com.guli.edu.pojo.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.pojo.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author gali
 * @since 2022-09-04
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getChapterVideo(String courseId);

    boolean deleteChapter(String chapterId);
}
