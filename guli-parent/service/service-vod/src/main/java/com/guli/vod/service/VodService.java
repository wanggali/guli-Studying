package com.guli.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Auther: gali
 * @Date: 2022-09-07 21:13
 * @Description:
 */
public interface VodService {

    String uploadAlyVideo(MultipartFile file);

    void removeVideo(String id);

    void removeBatch(List videoIdList);
}
