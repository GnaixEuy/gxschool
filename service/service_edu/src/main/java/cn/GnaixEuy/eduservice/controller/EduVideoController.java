package cn.GnaixEuy.eduservice.controller;

import cn.GnaixEuy.commonutils.ResultVo;
import cn.GnaixEuy.eduservice.entity.EduVideo;
import cn.GnaixEuy.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
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
@Api
@CrossOrigin
@RestController
@RequestMapping(value = {"/eduservice/video"})
public class EduVideoController {
    private EduVideoService eduVideoService;

    /**
     * 添加小节
     */
    @PostMapping(value = {"addVideo"})
    public ResultVo addVideo(@RequestBody EduVideo eduVideo) {
        this.eduVideoService.save(eduVideo);
        return ResultVo.ok();
    }

    /**
     * 删除小节
     * TODO 删除时把里面视频删除
     */
    @DeleteMapping(value = {"{id}"})
    public ResultVo deleteVideo(@PathVariable String id) {
        this.eduVideoService.removeById(id);
        return ResultVo.ok();
    }

    @Autowired
    public void setEduVideoService(EduVideoService eduVideoService) {
        this.eduVideoService = eduVideoService;
    }
}

