package com.guli.ucenter.service;

import com.guli.ucenter.pojo.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.ucenter.pojo.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author gali
 * @since 2022-09-13
 */
public interface MemberService extends IService<Member> {

    String login(Member member);

    void register(RegisterVo registerVo);
}
