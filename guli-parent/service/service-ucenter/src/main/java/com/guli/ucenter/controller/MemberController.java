package com.guli.ucenter.controller;


import com.guli.ucenter.pojo.Member;
import com.guli.ucenter.pojo.vo.RegisterVo;
import com.guli.ucenter.service.MemberService;
import com.guli.utils.JwtUtils;
import com.guli.utils.Result;
import com.guli.utils.user.UcenterMemberOrder;
import org.apache.http.HttpRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author gali
 * @since 2022-09-13
 */
@RestController
@RequestMapping("/center/member")
@CrossOrigin
public class MemberController {
    @Resource
    private MemberService memberService;
    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Member member){
        String token=memberService.login(member);
        return Result.ok().data("token",token).message("用户登录成功");
    }
    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return Result.ok().message("用户注册成功");
    }
    /**
     * token获取信息
     */
    @GetMapping("/getMemberInfo")
    public Result getMemberInfo(HttpServletRequest request){
        //根据头信息获取id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        Member member = memberService.getById(memberId);
        return Result.ok().data("userInfo",member);
    }
    /**
     * 根据用户id获取用户信息
     */
    @GetMapping("/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id){
        Member member = memberService.getById(id);
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,ucenterMemberOrder);
        return ucenterMemberOrder;
    }
}

