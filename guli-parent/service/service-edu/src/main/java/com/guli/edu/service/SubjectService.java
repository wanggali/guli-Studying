package com.guli.edu.service;

import com.guli.edu.pojo.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.pojo.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author gali
 * @since 2022-09-04
 */
public interface SubjectService extends IService<Subject> {

    void savaSubject(MultipartFile file,SubjectService subjectService);

    List<OneSubject> getAllSubject();

}
