package com.guli.cms.controller;

import com.guli.cms.pojo.CrmBanner;
import com.guli.cms.service.CrmBannerService;
import com.guli.utils.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: gali
 * @Date: 2022-09-08 20:22
 * @Description:
 */
@RestController
@RequestMapping("/cms/bannerFront")
@CrossOrigin
public class BannerFrontController {
    @Resource
    private CrmBannerService crmBannerService;

    /**
     * 前台显示轮播图
     */
    @GetMapping("/getAllBanner")
    public Result getAllBanner(){
        List<CrmBanner> list=crmBannerService.selectAllBanner();
        return Result.ok().data("list",list);
    }
}
