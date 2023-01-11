package cn.GnaixEuy.edu.client;

import cn.GnaixEuy.commonutils.ResultVo;
import org.springframework.stereotype.Component;

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
public class VodFileDegradeFeignClient implements VodClient {
    /**
     * 出错之后会执行
     */
    @Override
    public ResultVo removeAlyVideo(String id) {
        return ResultVo.error().message("删除视频出错了");
    }


    /**
     * 定义调用删除多个视频的方法
     * 删除多个阿里云视频的方法
     * 参数多个视频id  List videoIdList
     */
    @Override
    public ResultVo deleteBatch(List<String> videoIdList) {
        return ResultVo.error().message("删除多个视频出错了");
    }

}

