package com.guli.ucenter.mapper;

import com.guli.ucenter.pojo.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author gali
 * @since 2022-09-13
 */
public interface MemberMapper extends BaseMapper<Member> {

    Integer countRegister(String day);
}
