package com.guli.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.cms.pojo.CrmBanner;
import com.guli.cms.service.CrmBannerService;
import com.guli.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author gali
 * @since 2022-09-08
 */
@RestController
@RequestMapping("/cms/bannerAdmin")
@CrossOrigin
public class BannerAdminController {
    @Resource
    private CrmBannerService crmBannerService;
    /**
     * 分页查询轮播图
     */
    @GetMapping("/pageBanner/{page}/{limit}")
    public Result pageBanner(@PathVariable long page,@PathVariable long limit){
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        crmBannerService.page(pageBanner, null);
        return Result.ok().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }
    /**
     * 添加轮播图
     */
    @PostMapping("/addBanner")
    public Result addBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.save(crmBanner);
        return Result.ok();
    }

    //根据id获取Banner
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id) {
        CrmBanner banner = crmBannerService.getById(id);
        return Result.ok().data("item", banner);
    }

    //修改Banner
    @PutMapping("/update")
    public Result updateById(@RequestBody CrmBanner banner) {
        crmBannerService.updateById(banner);
        return Result.ok();
    }

    //根据id删除Banner
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        crmBannerService.removeById(id);
        return Result.ok();
    }

}

