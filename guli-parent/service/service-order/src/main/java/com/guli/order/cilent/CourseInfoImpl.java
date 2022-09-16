package com.guli.order.cilent;

import com.guli.utils.user.CourseWebVoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Auther: gali
 * @Date: 2022-09-16 15:55
 * @Description:
 */
@Component
@Slf4j
public class CourseInfoImpl implements CourseInfo{
    @Override
    public CourseWebVoOrder getCourseInfoOrder(String id) {
        log.error("订单获取课程信息失败！！！！");
        return null;
    }
}
