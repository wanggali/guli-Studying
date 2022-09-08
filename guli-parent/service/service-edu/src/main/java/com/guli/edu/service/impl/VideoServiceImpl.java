package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.client.VodClient;
import com.guli.edu.controller.VideoController;
import com.guli.edu.pojo.Course;
import com.guli.edu.pojo.Video;
import com.guli.edu.mapper.VideoMapper;
import com.guli.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author gali
 * @since 2022-09-04
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Resource
    private VideoMapper videoMapper;
    @Resource
    private VodClient vodClient;
    @Override
    //删除对应视频及小节
    public void removeVideoByCourseId(String courseId) {
        QueryWrapper<Video> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.select("video_source_id");
        List<Video> list = videoMapper.selectList(queryWrapper);
        List<String> videoIds = new ArrayList<>();
        //将list<video> ->list<string>
        for (int i = 0; i < list.size(); i++) {
            Video video = list.get(i);
            String videoSourceId = video.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)){
                videoIds.add(videoSourceId);
            }
        }
        //判断集合是否为空
        if (videoIds.size()>0){
            vodClient.removeBatch(videoIds);
        }
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        videoMapper.delete(wrapper);
    }
}
