package cn.GnaixEuy.cms.controller;

import cn.GnaixEuy.cms.entity.CrmBanner;
import cn.GnaixEuy.cms.service.CrmBannerService;
import cn.GnaixEuy.commonutils.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： gxschool_parent </p>
 * 创建日期： 2023/1/11
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@CrossOrigin
@RestController
@RequestMapping(value = {"/educms/bannerfront"})
public class BannerFrontController {
    private CrmBannerService crmBannerService;

    //查询所有banner
    @GetMapping("getAllBanner")
    public ResultVo getAllBanner() {
        List<CrmBanner> list = this.crmBannerService.selectAllBanner();
        return ResultVo.ok().data("list", list);
    }

    @Autowired
    public void setCrmBannerService(CrmBannerService crmBannerService) {
        this.crmBannerService = crmBannerService;
    }
}

