package com.guli.msm.controller;

import com.guli.msm.service.MsmService;
import com.guli.utils.RandomUtil;
import com.guli.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: gali
 * @Date: 2022-09-13 17:46
 * @Description:
 */
@RestController
@RequestMapping("/msm")
@CrossOrigin
public class MsmController {

    @Resource
    private MsmService msmService;

    @Resource
    private RedisTemplate<String,String> redisTemplate;
    /**
     * 发送短信,利用redis进行有效时间控制
     */
    @GetMapping("/send/{phone}")
    public Result sendMsm(@PathVariable String phone){
        if(StringUtils.isEmpty(phone)) return Result.error().message("手机号为空");
        String msg = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(msg)){
            return Result.ok().message("短信验证码未过期");
        }
        String code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        boolean flag=msmService.sendMsm(param,phone);
        if (flag){
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return Result.ok().message("短信发送成功");
        }
        return Result.error().message("短信发送失败");
    }
}
