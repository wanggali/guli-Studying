package com.guli.edu.service.impl;

import com.guli.edu.mapper.CourseDescriptionMapper;
import com.guli.edu.pojo.Course;
import com.guli.edu.mapper.CourseMapper;
import com.guli.edu.pojo.CourseDescription;
import com.guli.edu.pojo.vo.CourseInfoVo;
import com.guli.edu.service.CourseDescriptionService;
import com.guli.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.utils.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCourseInfo(CourseInfoVo courseInfoVo) {
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
    }
}
