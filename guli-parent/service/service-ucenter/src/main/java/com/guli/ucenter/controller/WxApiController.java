package com.guli.ucenter.controller;

import com.google.gson.Gson;
import com.guli.ucenter.pojo.Member;
import com.guli.ucenter.service.MemberService;
import com.guli.ucenter.utils.ConstantWxUtil;
import com.guli.ucenter.utils.HttpClientUtil;
import com.guli.utils.JwtUtils;
import com.guli.utils.exceptionhandler.GuliException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @Auther: gali
 * @Date: 2022-09-14 17:18
 * @Description:
 */
@Controller //只请求地址，不返回数据
@RequestMapping("/api/ucenter/wx")
@CrossOrigin
public class WxApiController {
    @Resource
    private MemberService memberService;
    /**
     * 生成二维码
     */
    @GetMapping("/login")
    public String getWxCode(){
        //请求微信地址
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        //授权码需要传入加密过的URL,必须使用
        String redirectUrl = ConstantWxUtil.WX_OPEN_REDIRECT_URL;//获取业务服务器重定向地址
        try {
            redirectUrl = URLEncoder.encode(redirectUrl,"utf-8");//url编码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //设置%s参数值
        String url = String.format(
                baseUrl,
                ConstantWxUtil.WX_OPEN_APP_ID,
                redirectUrl,
                "gali");
        //请求微信地址,重定向的方式
        return "redirect:" + url;
    }
    /**
     * 获取扫描人信息，添加数据
     */
    @GetMapping("/callback")
    public String callback(String code, String state, HttpSession session){
        try {
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";

            String accessTokenUrl = String.format(baseAccessTokenUrl,
                    ConstantWxUtil.WX_OPEN_APP_ID,
                    ConstantWxUtil.WX_OPEN_APP_SECRET,
                    code);
            //使用httpclient发送请求
            String accessTokenInfo = HttpClientUtil.get(accessTokenUrl);
            Gson gson = new Gson();
            HashMap hashMap = gson.fromJson(accessTokenInfo, HashMap.class);
            String access_token = (String)hashMap.get("access_token");
            String openid = (String)hashMap.get("openid");
            //判断微信号是否注册
            Member member=memberService.getOpenIdMember(openid);
            if (member==null){
                //3拿着access_token和openid，再去请求微信提供的固定地址，获取扫描人信息
                //访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                //再次拼接微信地址
                String userInfoUrl = String.format(baseUserInfoUrl, access_token, openid);

                String userInfo = HttpClientUtil.get(userInfoUrl);

                //获取的微信个人信息json信息进行转换
                HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
                System.out.println(userInfoMap);
                String nickname = (String)userInfoMap.get("nickname");//昵称
                String headImgUrl = (String)userInfoMap.get("headimgurl");//头像
                Double sex = (Double) userInfoMap.get("sex");//性别
                Integer integer=0;
                if (sex==0.0){
                     integer = 2;
                }else {
                     integer=1;
                }
                //把微信信息注册到数据库中
                member = new Member();
                member.setNickname(nickname);
                member.setOpenid(openid);
                member.setSex(integer);
                member.setAvatar(headImgUrl);
                memberService.save(member);
            }
            //使用jwt生成token字符串
            String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
            //返回首页面
            return "redirect:http://localhost:3000?token="+jwtToken;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(20001,"登录失败");
        }
    }
}
