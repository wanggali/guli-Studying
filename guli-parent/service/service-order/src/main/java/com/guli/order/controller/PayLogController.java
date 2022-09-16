package com.guli.order.controller;


import com.guli.order.service.PayLogService;
import com.guli.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author gali
 * @since 2022-09-16
 */
@RestController
@RequestMapping("/order/payLog")
@CrossOrigin
public class PayLogController {

    @Resource
    private PayLogService payLogService;

    /**
     * 生成二维码
     */
    @GetMapping("/createNative/{orderNo}")
    public Result createNative(@PathVariable String orderNo){
        Map map=payLogService.createNative(orderNo);
        return Result.ok().data(map);
    }
    /**
     * 查询订单支付的状态
     */
    @GetMapping("queryPayStatus/{orderNo}")
    public Result queryPayStatus(@PathVariable String orderNo){
        Map<String,String> map=payLogService.queryPayStatus(orderNo);
        if (map==null){
            return Result.error().message("支付出错");
        }
        //如果返回不为空，通过map获取订单状态
        if (map.get("trade_state").equals("SUCCESS")){
            //添加记录在支付表，更新订单表订单
            payLogService.updateOrdersStatus(map);
            return Result.ok().message("支付成功");
        }
        return Result.error().message("正在支付中......");
    }
}

