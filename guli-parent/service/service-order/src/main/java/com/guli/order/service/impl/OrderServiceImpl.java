package com.guli.order.service.impl;

import com.guli.order.cilent.CourseInfo;
import com.guli.order.cilent.UserInfo;
import com.guli.order.pojo.Order;
import com.guli.order.mapper.OrderMapper;
import com.guli.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.order.utils.OrderNoUtil;
import com.guli.utils.user.CourseWebVoOrder;
import com.guli.utils.user.UcenterMemberOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author gali
 * @since 2022-09-16
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private CourseInfo courseInfo;
    @Resource
    private UserInfo userInfo;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createOrder(String courseId, String memberId) {
        //通过远程调用获取用户，课程信息
        CourseWebVoOrder courseInfoOrder = courseInfo.getCourseInfoOrder(courseId);
        UcenterMemberOrder userInfoOrder = userInfo.getUserInfoOrder(memberId);
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        orderMapper.insert(order);
        return order.getOrderNo();
    }
}
