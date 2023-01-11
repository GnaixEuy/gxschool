package cn.GnaixEuy.cms.controller;

import cn.GnaixEuy.cms.entity.CrmBanner;
import cn.GnaixEuy.cms.service.CrmBannerService;
import cn.GnaixEuy.commonutils.ResultVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(value = {"/educms/banneradmin"})
public class BannerAdminController {

    private CrmBannerService crmBannerService;

    @GetMapping(value = {"pageBanner/{page}/{limit}"})
    public ResultVo pageBanner(@PathVariable long page, @PathVariable long limit) {
        Page<CrmBanner> pageBanner = new Page<>(page, limit);
        this.crmBannerService.page(pageBanner, null);
        return ResultVo.ok().data("items", pageBanner.getRecords()).data("total", pageBanner.getTotal());
    }

    /**
     * 添加banner
     */
    @PostMapping(value = {"addBanner"})
    public ResultVo addBanner(@RequestBody CrmBanner crmBanner) {
        this.crmBannerService.save(crmBanner);
        return ResultVo.ok();
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping(value = {"get/{id}"})
    public ResultVo get(@PathVariable String id) {
        CrmBanner banner = this.crmBannerService.getById(id);
        return ResultVo.ok().data("item", banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping(value = {"update"})
    public ResultVo updateById(@RequestBody CrmBanner banner) {
        this.crmBannerService.updateById(banner);
        return ResultVo.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping(value = {"remove/{id}"})
    public ResultVo remove(@PathVariable String id) {
        this.crmBannerService.removeById(id);
        return ResultVo.ok();
    }

    @Autowired
    public void setCrmBannerService(CrmBannerService crmBannerService) {
        this.crmBannerService = crmBannerService;
    }
}

