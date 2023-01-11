package cn.GnaixEuy.cms.service.impl;

import cn.GnaixEuy.cms.entity.CrmBanner;
import cn.GnaixEuy.cms.mapper.CrmBannerMapper;
import cn.GnaixEuy.cms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author GnaixEuy
 * @since 2023-01-11
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    /**
     * 查询所有banner
     */
    @Override
    @Cacheable(value = "banner", key = "'selectIndexList'")
    public List<CrmBanner> selectAllBanner() {
        return baseMapper.selectList(null);
    }
}
