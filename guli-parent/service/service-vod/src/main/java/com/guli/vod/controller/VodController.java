package com.guli.vod.controller;

import com.guli.utils.Result;
import com.guli.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Auther: gali
 * @Date: 2022-09-07 21:12
 * @Description:
 */
@RestController
@RequestMapping("vod/video")
@Api(description = "阿里云视频点播管理")
@CrossOrigin
public class VodController {

    @Resource
    private VodService vodService;

    /**
     * 上传视频
     */
    @PostMapping("/uploadAlyVideo")
    @ApiOperation(value = "上传视频")
    public Result uploadAlyVideo(MultipartFile file){
        String videoId = vodService.uploadAlyVideo(file);
        return Result.ok().data("videoId",videoId);
    }
    /**
     * 删除视频
     */
    @DeleteMapping("/removeAlyVideo/{id}")
    @ApiOperation(value = "删除视频")
    public Result removeAlyVideo(@PathVariable String id){
        vodService.removeVideo(id);
        return Result.ok();
    }
}
