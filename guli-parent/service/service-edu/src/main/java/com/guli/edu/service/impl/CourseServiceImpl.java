package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.mapper.CourseDescriptionMapper;
import com.guli.edu.pojo.Course;
import com.guli.edu.mapper.CourseMapper;
import com.guli.edu.pojo.CourseDescription;
import com.guli.edu.pojo.vo.CourseInfoVo;
import com.guli.edu.pojo.vo.CoursePublishVo;
import com.guli.edu.service.ChapterService;
import com.guli.edu.service.CourseDescriptionService;
import com.guli.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.service.VideoService;
import com.guli.utils.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseMapper courseMapper;
    @Resource
    private CourseDescriptionService courseDescriptionService;
    @Resource
    private VideoService videoService;
    @Resource
    private ChapterService chapterService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //向课程表添加信息
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo,course);
        int insert = courseMapper.insert(course);
        if (insert<=0){
            throw new GuliException(20001,"添加课程信息失败");
        }
        //获取添加之后的id
        String cid = course.getId();
        //向课程简介表添加信息
        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseInfoVo,courseDescription);
        //手动设置简介表id
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);
        return cid;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CourseInfoVo getCourseInfo(String courseId) {
        Course course = courseMapper.selectById(courseId);
        CourseDescription courseDescription = courseDescriptionService.getById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(course,courseInfoVo);
        BeanUtils.copyProperties(courseDescription,courseInfoVo);
        return courseInfoVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        Course course = new Course();
        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseInfoVo,course);
        BeanUtils.copyProperties(courseInfoVo,courseDescription);
        int i = courseMapper.updateById(course);
        if (i<=0){
            throw new GuliException(20001,"修改课程失败");
        }
        boolean b = courseDescriptionService.updateById(courseDescription);
        if (!b){
            throw new GuliException(20001,"修改课程简介失败");
        }
    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String id) {
        return courseMapper.getPublishCourseInfo(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeCourse(String courseId) {
        //删除小节
        videoService.removeVideoByCourseId(courseId);
        //删除章节
        chapterService.removeChapterByCourseId(courseId);
        //删除描述
        courseDescriptionService.removeById(courseId);
        //删除课程本身
        courseMapper.deleteById(courseId);
    }

    @Override
    @Cacheable(value = "indexHotCourse",key = "'findIndexHotCourse'")
    public List<Course> findIndexHotCourse() {
        QueryWrapper<Course> wrapper= new QueryWrapper<>();
        wrapper.orderByDesc("id").last("limit 8");
        List<Course> listCourse = courseMapper.selectList(wrapper);
        return listCourse;
    }
}
