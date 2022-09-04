package com.guli.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.pojo.Subject;
import com.guli.edu.pojo.excel.SubjectData;
import com.guli.edu.service.SubjectService;
import com.guli.utils.exceptionhandler.GuliException;

/**
 * @Auther: gali
 * @Date: 2022-09-04 14:39
 * @Description:
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    //因为SubjectExcelListener不能交给spring容器管理，需要自己new，不能注入其他对象
    public SubjectService subjectService;

    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
    public SubjectExcelListener() {
    }
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData==null){
            throw new GuliException(20001,"文件数据为空");
        }
        //判断一级分类是否重复
        Subject existOneSubject = this.existOneSubject(subjectData.getOneSubjectName(), subjectService);
        if (existOneSubject==null){
            //没有相同的一级分类
            existOneSubject=new Subject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existOneSubject);
        }
        //获取一级分类的id值
        String pid=existOneSubject.getId();
        Subject existTwoSubject = this.existTwoSubject(subjectData.getTwoSubjectName(),subjectService,pid);
        if (existTwoSubject==null){
            //没有相同的二级分类
            existTwoSubject=new Subject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }
    }

    /**
     * 判断一级分类不能重复添加
     */
    private Subject existOneSubject(String name,SubjectService subjectService){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        Subject one = subjectService.getOne(wrapper);
        return one;
    }
    /**
     * 判断二级分类不能重复添加
     */
    private Subject existTwoSubject(String name,SubjectService subjectService,String pid){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        Subject two = subjectService.getOne(wrapper);
        return two;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
