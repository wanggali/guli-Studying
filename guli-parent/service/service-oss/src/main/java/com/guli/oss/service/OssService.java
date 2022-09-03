package com.guli.oss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: gali
 * @Date: 2022-09-02 17:05
 * @Description:
 */

public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
