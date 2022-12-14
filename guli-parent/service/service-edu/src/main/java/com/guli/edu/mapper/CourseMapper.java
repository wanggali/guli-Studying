package com.guli.edu.mapper;

import com.guli.edu.pojo.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guli.edu.pojo.vo.CoursePublishVo;
import com.guli.edu.pojo.vo.front.CourseWebVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author gali
 * @since 2022-09-04
 */
public interface CourseMapper extends BaseMapper<Course> {
    CoursePublishVo getPublishCourseInfo(String courseId);

    CourseWebVo getBaseCourseInfo(String courseId);
}
