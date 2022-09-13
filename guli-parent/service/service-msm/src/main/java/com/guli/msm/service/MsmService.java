package com.guli.msm.service;

import java.util.Map;

/**
 * @Auther: gali
 * @Date: 2022-09-13 17:47
 * @Description:
 */
public interface MsmService {
    boolean sendMsm(Map<String, Object> param, String phone);
}
