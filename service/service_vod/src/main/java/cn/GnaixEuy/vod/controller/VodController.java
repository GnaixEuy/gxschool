package cn.GnaixEuy.vod.controller;

import cn.GnaixEuy.commonutils.ResultVo;
import cn.GnaixEuy.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping(value = {"/eduvod/video"})
public class VodController {
    private VodService vodService;

    /**
     * 上传视频到阿里云
     */
    @PostMapping(value = {"uploadAlyiVideo"})
    public ResultVo uploadAlyiVideo(MultipartFile file) {
        //返回上传视频id
        String videoId = vodService.uploadVideoAly(file);
        return ResultVo.ok().data("videoId", videoId);
    }

    @Autowired
    public void setVodService(VodService vodService) {
        this.vodService = vodService;
    }
}

