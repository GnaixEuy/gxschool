package cn.GnaixEuy.vod.service;

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
public interface VodService {
    /**
     * 上传视频到阿里云
     */
    String uploadVideoAly(MultipartFile file);


    /**
     * 删除多个阿里云视频的方法
     */
    void removeMoreAlyVideo(List videoIdList);
}

