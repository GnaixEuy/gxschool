package cn.GnaixEuy.vod.controller;

import cn.GnaixEuy.commonutils.ResultVo;
import cn.GnaixEuy.servicebase.config.exception.BizException;
import cn.GnaixEuy.servicebase.config.exception.ExceptionType;
import cn.GnaixEuy.vod.service.VodService;
import cn.GnaixEuy.vod.utils.ConstantVodUtils;
import cn.GnaixEuy.vod.utils.InitVodCilent;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping(value = {"/eduvod/video"})
public class VodController {
    private VodService vodService;

    /**
     * 上传视频到阿里云
     */
    @PostMapping(value = {"uploadAlyiVideo"})
    public ResultVo uploadAliyunVideo(MultipartFile file) {
        //返回上传视频id
        String videoId = vodService.uploadVideoAly(file);
        return ResultVo.ok().data("videoId", videoId);
    }

    /**
     * 根据视频id删除阿里云视频
     */
    @DeleteMapping(value = {"removeAlyVideo/{id}"})
    public ResultVo removeAliyunVideo(@PathVariable String id) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return ResultVo.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(ExceptionType.VIDEO_DELETE_EXCEPTION);
        }
    }

    /**
     * 删除多个阿里云视频的方法
     * 参数多个视频id  List videoIdList
     */
    @DeleteMapping(value = {"delete-batch"})
    public ResultVo deleteBatch(@RequestParam(value = "videoIdList") List<String> videoIdList) {
        this.vodService.removeMoreAlyVideo(videoIdList);
        return ResultVo.ok();
    }

    @Autowired
    public void setVodService(VodService vodService) {
        this.vodService = vodService;
    }
}

