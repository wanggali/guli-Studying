package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.pojo.Teacher;
import com.guli.edu.mapper.TeacherMapper;
import com.guli.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author gali
 * @since 2022-09-01
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;
    @Override
    @Cacheable(value = "indexHotTeacher",key = "'findIndexHotTeacher'")
    public List<Teacher> findIndexHotTeacher() {
        QueryWrapper<Teacher> wrapper1= new QueryWrapper<>();
        wrapper1.orderByDesc("id").last("limit 4");
        List<Teacher> listTeacher = teacherMapper.selectList(wrapper1);
        return listTeacher;
    }
}
