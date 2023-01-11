package cn.GnaixEuy.edu.controller;

import cn.GnaixEuy.commonutils.ResultVo;
import cn.GnaixEuy.edu.client.VodClient;
import cn.GnaixEuy.edu.entity.EduVideo;
import cn.GnaixEuy.edu.service.EduVideoService;
import cn.GnaixEuy.servicebase.config.exception.BizException;
import cn.GnaixEuy.servicebase.config.exception.ExceptionType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
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
    private VodClient vodClient;


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
     */
    @ApiOperation(value = "根据ID删除课时")
    @ApiParam(name = "id", value = "课时ID", required = true)
    @DeleteMapping(value = {"{id}"})
    public ResultVo deleteVideo(@PathVariable String id) {
        EduVideo eduVideo = this.eduVideoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)) {
            ResultVo result = this.vodClient.removeAlyVideo(videoSourceId);
            if (result.getCode() == 20005) {
                throw new BizException(ExceptionType.VIDEO_DELETE_EXCEPTION);
            }
        }
        if (this.eduVideoService.removeById(id)) {
            return ResultVo.ok();
        } else {
            return ResultVo.error();
        }
    }

    @Autowired
    public void setEduVideoService(EduVideoService eduVideoService) {
        this.eduVideoService = eduVideoService;
    }
}

