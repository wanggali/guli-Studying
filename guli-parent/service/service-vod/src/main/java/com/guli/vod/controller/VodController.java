package com.guli.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.guli.utils.Result;
import com.guli.utils.exceptionhandler.GuliException;
import com.guli.vod.client.AliyunClient;
import com.guli.vod.service.VodService;
import com.guli.vod.utils.ConstantVodUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

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
    /**
     * 删除多个视频
     */
    @DeleteMapping("/removeBatch")
    @ApiOperation(value = "删除多个视频")
    public Result removeBatch(@RequestParam("videoIdList") List<String> videoIdList){
        vodService.removeBatch(videoIdList);
        return Result.ok();
    }
    /**
     * 根据视频id获取凭证
     */
    @GetMapping("/getPlayAuth/{id}")
    public Result getPlayAuth(@PathVariable String id){
        String playAuth = vodService.getPlayAuth(id);
        return Result.ok().data("playAuth",playAuth);
    }
}
