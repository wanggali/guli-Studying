package com.guli.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.pojo.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author gali
 * @since 2022-09-01
 */
public interface TeacherService extends IService<Teacher> {

    List<Teacher> findIndexHotTeacher();

    Map<String, Object> getTeacherFrontList(Page<Teacher> pageInfo);
}
