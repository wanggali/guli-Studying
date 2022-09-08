package com.guli.edu.controller;


import com.guli.edu.client.VodClient;
import com.guli.edu.pojo.Video;
import com.guli.edu.service.VideoService;
import com.guli.utils.Result;
import com.guli.utils.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
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

    @Resource
    private VodClient vodClient;

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
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除小节")
    public Result deleteVideo(@PathVariable String id){
        //根据小节id获取视频id
        Video video = videoService.getById(id);
        String videoSourceId = video.getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)){
            Result result = vodClient.removeAlyVideo(videoSourceId);
            if (result.getCode()==20001){
                throw new GuliException(20001,"删除视频失败，熔断器启动");
            }
        }
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

