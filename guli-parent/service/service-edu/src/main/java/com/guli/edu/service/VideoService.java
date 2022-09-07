package com.guli.edu.service;

import com.guli.edu.pojo.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author gali
 * @since 2022-09-04
 */
public interface VideoService extends IService<Video> {

    void removeVideoByCourseId(String courseId);
}
