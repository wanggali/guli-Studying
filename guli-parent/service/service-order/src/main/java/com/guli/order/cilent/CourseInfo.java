package com.guli.order.cilent;

import com.guli.utils.user.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Auther: gali
 * @Date: 2022-09-16 15:46
 * @Description:
 */
@Component
@FeignClient(name = "service-edu",fallback = CourseInfoImpl.class)
public interface CourseInfo {
    @GetMapping("/edu/courseFront/getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable("id") String id);
}
