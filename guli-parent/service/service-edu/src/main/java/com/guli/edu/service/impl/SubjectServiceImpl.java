package com.guli.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.listener.SubjectExcelListener;
import com.guli.edu.pojo.Subject;
import com.guli.edu.mapper.SubjectMapper;
import com.guli.edu.pojo.excel.SubjectData;
import com.guli.edu.pojo.subject.OneSubject;
import com.guli.edu.pojo.subject.TwoSubject;
import com.guli.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author gali
 * @since 2022-09-04
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Resource
    private SubjectMapper subjectMapper;
    @Override
    public void savaSubject(MultipartFile file,SubjectService subjectService) {
        //文件输入流
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllSubject() {
        //查询所有的一级分类
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id","0");
        List<Subject> oneSubject = subjectMapper.selectList(wrapper);
        //查询所有的二级分类
        QueryWrapper<Subject> wrapper2 = new QueryWrapper<>();
        wrapper.ne("parent_id","0");
        List<Subject> twoSubject = subjectMapper.selectList(wrapper2);
        List<OneSubject> list = new ArrayList<>();
        //封装一级分类
        int size = oneSubject.size();
        for (int i = 0; i < size; i++) {
            Subject subject = oneSubject.get(i);
            OneSubject oneSubject1 = new OneSubject();
            BeanUtils.copyProperties(subject,oneSubject1);
            list.add(oneSubject1);
            //封装二级分类
            List<TwoSubject> twoSubjects=new ArrayList<>();
            int size1 = twoSubject.size();
            for (int j = 0; j <size1 ; j++) {
                Subject subject1 = twoSubject.get(j);
                if (subject1.getParentId().equals(oneSubject1.getId())){
                    TwoSubject twoSubject1 = new TwoSubject();
                    BeanUtils.copyProperties(subject1,twoSubject1);
                    twoSubjects.add(twoSubject1);
                }
            }
            oneSubject1.setChildren(twoSubjects);
        }
        return list;
    }
}
