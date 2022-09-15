package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.pojo.Teacher;
import com.guli.edu.mapper.TeacherMapper;
import com.guli.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> getTeacherFrontList(Page<Teacher> pageInfo) {
        QueryWrapper<Teacher> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("id");
        teacherMapper.selectPage(pageInfo,wrapper);

        List<Teacher> records = pageInfo.getRecords();
        long current = pageInfo.getCurrent();
        long pages = pageInfo.getPages();
        long size = pageInfo.getSize();
        long total = pageInfo.getTotal();
        boolean hasNext = pageInfo.hasNext();//上一页
        boolean hasPrevious = pageInfo.hasPrevious();//下一页

        //把分页数据获取出来放入map中
        Map<String,Object> map= new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }
}
