package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.pojo.Chapter;
import com.guli.edu.mapper.ChapterMapper;
import com.guli.edu.pojo.Video;
import com.guli.edu.pojo.chapter.ChapterVo;
import com.guli.edu.pojo.chapter.VideoVo;
import com.guli.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.service.VideoService;
import com.guli.utils.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author gali
 * @since 2022-09-04
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Resource
    private ChapterMapper chapterMapper;
    @Resource
    private VideoService videoService;
    @Override
    public List<ChapterVo> getChapterVideo(String courseId) {
        //查询所有章节
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<Chapter> chapters = chapterMapper.selectList(wrapper);
        //查询所有小节
        QueryWrapper<Video> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id",courseId);
        List<Video> videos = videoService.list(wrapper1);
        //创建最终list集合
        List<ChapterVo> list = new ArrayList<>();
        //封装章节和小节
        int size = chapters.size();
        for (int i = 0; i < size; i++) {
            Chapter chapter = chapters.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            list.add(chapterVo);
            int videoSize = videos.size();
            List<VideoVo> videoVoList=new ArrayList<>();
            for (int j = 0; j <videoSize ; j++) {
                Video video = videos.get(j);
                if (video.getChapterId().equals(chapterVo.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
        }
        return list;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        //查询章节中是否有小节
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id",chapterId);
        int count = videoService.count(queryWrapper);
        if (count>0){
            throw new GuliException(20001,"该章节中有小节，不能删除");
        }else {
            //删除章节
            int i = chapterMapper.deleteById(chapterId);
            return i>0;
        }
    }
}
