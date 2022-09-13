package com.guli.cms.service.impl;

import com.guli.cms.pojo.CrmBanner;
import com.guli.cms.mapper.CrmBannerMapper;
import com.guli.cms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author gali
 * @since 2022-09-08
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Resource
    private CrmBannerMapper crmBannerMapper;

    @Override
    @Cacheable(value = "banner",key = "'selectAllBanner'")
    public List<CrmBanner> selectAllBanner() {

        List<CrmBanner> list = crmBannerMapper.selectList(null);
        return list;
    }
}
