package com.guli.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.guli.msm.service.MsmService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Auther: gali
 * @Date: 2022-09-13 17:47
 * @Description:
 */
@Service
public class MsmServiceImpl implements MsmService {

    @Override
    public boolean sendMsm(Map<String, Object> param, String phone) {
        DefaultProfile profile =
                DefaultProfile.getProfile("default", "你的id", "你的secret");
        IAcsClient client = new DefaultAcsClient(profile);
        //设置相关参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);固定参数
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        //"PhoneNumbers"固定名字
        request.putQueryParameter("PhoneNumbers",phone);
        request.putQueryParameter("SignName", "你的签名名字");//签名名
        request.putQueryParameter("TemplateCode", "模板编号");//模板编号
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));
        try {
            //最终发送
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
