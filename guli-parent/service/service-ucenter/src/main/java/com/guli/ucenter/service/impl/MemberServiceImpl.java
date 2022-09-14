package com.guli.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.ucenter.pojo.Member;
import com.guli.ucenter.mapper.MemberMapper;
import com.guli.ucenter.pojo.vo.RegisterVo;
import com.guli.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.utils.JwtUtils;
import com.guli.utils.MD5;
import com.guli.utils.exceptionhandler.GuliException;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author gali
 * @since 2022-09-13
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Resource
    private MemberMapper memberMapper;
    @Resource
    private RedisTemplate<String,String> redisTemplate;
    //默认头像地址
    public static final String avatar="https://galidata-1309989133.cos.ap-chengdu.myqcloud.com/gali-music/img/songListPic/109951163271025942.jpg";
    @Override
    public String login(Member member) {
        String mobile = member.getMobile();
        String password = member.getPassword();
        if (StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            throw new GuliException(20001,"手机号或密码为空");
        }
        //手机号是否正确
        QueryWrapper<Member> wrapper=new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Member mobileMember = memberMapper.selectOne(wrapper);
        if (mobileMember==null){
            throw new GuliException(20001,"手机号不存在");
        }
        //判断密码,密码加密
        String digestPassword = MD5.encrypt(password);
        if (!digestPassword.equals(mobileMember.getPassword())){
            throw new GuliException(20001,"密码错误");
        }
        //判断用户是否被禁用 1:禁用 0:未禁用
        if (mobileMember.getIsDisabled()){
            throw new GuliException(20001,"用户被禁用");
        }
        String token = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String nickname = registerVo.getNickname();
        if (StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)||
            StringUtils.isEmpty(code)||StringUtils.isEmpty(nickname)){
            throw new GuliException(20001,"注册失败");
        }
        //判断手机号是否重复
        QueryWrapper<Member> wrapper=new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = memberMapper.selectCount(wrapper);
        if (count>0){
            throw new GuliException(20001,"手机号已经被注册");
        }
        //判断验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (!redisCode.equals(code)){
            throw new GuliException(20001,"验证码错误");
        }
        Member member = new Member();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);//不禁用
        member.setAvatar(avatar);
        memberMapper.insert(member);
    }

    @Override
    public Member getOpenIdMember(String openid) {
        QueryWrapper<Member> wrapper=new QueryWrapper<>();
        wrapper.eq("openid",openid);
        Member member = memberMapper.selectOne(wrapper);
        return member;
    }
}
