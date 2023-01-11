package cn.GnaixEuy.cms.service;

import cn.GnaixEuy.cms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author GnaixEuy
 * @since 2023-01-11
 */
public interface CrmBannerService extends IService<CrmBanner> {

    /**
     * 查询所有banner
     */
    List<CrmBanner> selectAllBanner();
}
