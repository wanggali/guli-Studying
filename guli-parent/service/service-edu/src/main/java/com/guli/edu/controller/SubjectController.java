package com.guli.edu.controller;


import com.guli.edu.pojo.subject.OneSubject;
import com.guli.edu.service.SubjectService;
import com.guli.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author gali
 * @since 2022-09-04
 */
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
@Api(description = "学科分类")
public class SubjectController {
    @Resource
    private SubjectService subjectService;

    /**
     * 添加课程分类
     */
    @PostMapping("/addSubject")
    @ApiOperation(value = "添加学科信息")
    public Result addSubject(MultipartFile file){
        //上传过来文件
        subjectService.savaSubject(file,subjectService);
        return Result.ok();
    }

    /**
     * 课程分类的列表---树形
     */
    @GetMapping("/getAllSubject")
    @ApiOperation(value = "课程分类的列表")
    public Result getAllSubject(){
        List<OneSubject> list=subjectService.getAllSubject();
        return Result.ok().data("list",list);
    }

}

