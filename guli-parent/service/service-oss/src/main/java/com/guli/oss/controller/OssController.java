package com.guli.oss.controller;

import com.guli.oss.service.OssService;
import com.guli.utils.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Auther: gali
 * @Date: 2022-09-02 17:05
 * @Description:
 */
@RestController
@RequestMapping("oss")
@CrossOrigin
public class OssController {
     @Resource
     private OssService ossService;
     @PostMapping("/upload")
     public Result uploadOssFile(MultipartFile file){
       String url=ossService.uploadFileAvatar(file);
       return Result.ok().data("url",url);
     }
}
