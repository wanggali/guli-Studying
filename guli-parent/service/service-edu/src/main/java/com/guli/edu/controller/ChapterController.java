package com.guli.edu.controller;


import com.guli.edu.pojo.Chapter;
import com.guli.edu.pojo.chapter.ChapterVo;
import com.guli.edu.service.ChapterService;
import com.guli.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author gali
 * @since 2022-09-04
 */
@RestController
@RequestMapping("/edu/chapter")
@CrossOrigin
@Api(description = "章节管理")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    /**
     * 课程大纲列表
     */
    @GetMapping("/getChapterVideo/{courseId}")
    @ApiOperation(value = "查询大纲列表")
    public Result getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list =chapterService.getChapterVideo(courseId);
        return Result.ok().data("allChapterVideo",list);
    }
    /**
     * 添加章节
     */
    @PostMapping("/addChapter")
    @ApiOperation(value = "添加章节")
    public Result addChapter(@RequestBody Chapter chapter){
        chapterService.save(chapter);
        return Result.ok();
    }
    /**
     * id查询章节
     */
    @GetMapping("/getChapterInfo/{chapterId})")
    @ApiOperation(value = "id查询章节")
    public Result getChapterInfo(@PathVariable String chapterId){
        Chapter chapter = chapterService.getById(chapterId);
        return Result.ok().data("chapter",chapter);
    }
    /**
     * 修改章节
     */
    @PostMapping("/updateChapter")
    @ApiOperation(value = "修改章节")
    public Result updateChapter(@RequestBody Chapter chapter){
        chapterService.updateById(chapter);
        return Result.ok();
    }
    /**
     * 删除章节
     */
    @DeleteMapping("/{chapterId}")
    @ApiOperation(value = "删除章节")
    public Result deleteChapter(@PathVariable String chapterId){
        boolean b = chapterService.deleteChapter(chapterId);
        if (b){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

}

