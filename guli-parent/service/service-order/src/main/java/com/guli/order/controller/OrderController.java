package com.guli.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.order.pojo.Order;
import com.guli.order.service.OrderService;
import com.guli.utils.JwtUtils;
import com.guli.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author gali
 * @since 2022-09-16
 */
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    @Resource
    private OrderService orderService;

    /**
     * 生成订单
     */
    @PostMapping("/createOrder/{courseId}")
    public Result createOrder(@PathVariable String courseId,
                              HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        String orderNo=orderService.createOrder(courseId,memberId);
        return Result.ok().data("orderId",orderNo);
    }
    /**
     * 根据订单id查询信息
     */
    @GetMapping("/getOrderInfo/{orderId}")
    public Result getOrderInfo(@PathVariable String orderId){
        QueryWrapper<Order> wr=new QueryWrapper<>();
        wr.eq("order_no",orderId);
        Order orderInfo = orderService.getOne(wr);
        return Result.ok().data("item",orderInfo);
    }
}

