package cn.GnaixEuy.edu.client;

import cn.GnaixEuy.commonutils.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
@Component
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
public interface VodClient {
    @DeleteMapping(value = {"/eduvod/video/removeAlyVideo/{id}"})
    ResultVo removeAlyVideo(@PathVariable(value = "id") String id);


    /**
     * 定义调用删除多个视频的方法
     * 删除多个阿里云视频的方法
     * 参数多个视频id  List videoIdList
     */
    @DeleteMapping(value = {"/eduvod/video/delete-batch"})
    ResultVo deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}

