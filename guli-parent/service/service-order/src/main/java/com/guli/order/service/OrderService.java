package com.guli.order.service;

import com.guli.order.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author gali
 * @since 2022-09-16
 */
public interface OrderService extends IService<Order> {

    String createOrder(String courseId, String memberId);
}
