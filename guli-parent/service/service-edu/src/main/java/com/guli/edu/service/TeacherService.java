package com.guli.edu.service;

import com.guli.edu.pojo.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
}
