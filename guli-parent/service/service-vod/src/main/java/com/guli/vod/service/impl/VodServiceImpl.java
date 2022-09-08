package com.guli.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;

import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.guli.utils.exceptionhandler.GuliException;
import com.guli.vod.service.VodService;

import com.guli.vod.client.AliyunClient;
import com.guli.vod.utils.ConstantVodUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @Auther: gali
 * @Date: 2022-09-07 21:13
 * @Description:
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadAlyVideo(MultipartFile file) {
        try {
            //accessKeyId, accessKeySecret
            //fileName：上传文件原始名称
            // 01.03.09.mp4
            String fileName = file.getOriginalFilename();
            //title：上传之后显示名称
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            //inputStream：上传文件输入流
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtil.ACCESS_KEY_ID, ConstantVodUtil.ACCESS_KEY_SECRET, title, fileName, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return videoId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void removeVideo(String id) {
        try {
            //初始化对象
            DefaultAcsClient client = AliyunClient.initVodClient(
                    ConstantVodUtil.ACCESS_KEY_ID,
                    ConstantVodUtil.ACCESS_KEY_SECRET);
            //创建删除视频的request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request中设置videoId
            request.setVideoIds(id);
            //调用删除方法
            client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new GuliException(20001, "视频删除失败");
        }
    }

    @Override
    public void removeBatch(List videoIdList) {
        try {
            //初始化对象
            DefaultAcsClient client = AliyunClient.initVodClient(
                    ConstantVodUtil.ACCESS_KEY_ID,
                    ConstantVodUtil.ACCESS_KEY_SECRET);
            //创建删除视频的request对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            //遍历数组为 1,2,3,4
            String join = StringUtils.join(videoIdList.toArray(), ",");

            //向request中设置videoId
            request.setVideoIds(join);
            //调用删除方法
            client.getAcsResponse(request);
        } catch (ClientException e) {
            throw new GuliException(20001, "视频删除失败");
        }
    }
}
