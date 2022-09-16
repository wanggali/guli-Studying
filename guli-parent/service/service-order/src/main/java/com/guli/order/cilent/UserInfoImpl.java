package com.guli.order.cilent;

import com.guli.utils.user.UcenterMemberOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Auther: gali
 * @Date: 2022-09-16 15:53
 * @Description:
 */
@Component
@Slf4j
public class UserInfoImpl implements UserInfo{
    @Override
    public UcenterMemberOrder getUserInfoOrder(String id) {
        log.error("订单获取用户信息失败！！！！");
        return null;
    }
}
