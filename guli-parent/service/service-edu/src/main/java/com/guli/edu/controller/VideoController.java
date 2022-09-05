package com.guli.edu.controller;


import com.guli.edu.pojo.Video;
import com.guli.edu.service.VideoService;
import com.guli.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author gali
 * @since 2022-09-04
 */
@RestController
@RequestMapping("/edu/video")
@CrossOrigin
@Api(description = "小节管理")
public class VideoController {

    @Resource
    private VideoService videoService;

    /**
     * 添加小节
     */
    @PostMapping("/addVideo")
    @ApiOperation(value = "添加小节")
    public Result addVideo(@RequestBody Video video){
        videoService.save(video);
        return Result.ok();
    }
    /**
     * 删除小节
     * TODO 后面需要完善
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除小节")
    public Result deleteVideo(@PathVariable String id){
        videoService.removeById(id);
        return Result.ok();
    }

    /**
     * 修改小节
     */
    @PostMapping("/updateVideo")
    @ApiOperation(value = "修改小节")
    public Result updateVideo(@RequestBody Video video){
        videoService.updateById(video);
        return Result.ok();
    }
}

