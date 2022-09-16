package com.guli.order.cilent;

import com.guli.utils.user.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Auther: gali
 * @Date: 2022-09-16 15:45
 * @Description:
 */
@Component
@FeignClient(name = "service-ucenter",fallback = UserInfoImpl.class)
public interface UserInfo {
    @GetMapping("/center/member/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id);
}
