package cn.GnaixEuy.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： gxschool_parent </p>
 * 创建日期： 2023/1/8
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
public interface OssService {
    /**
     * 文件上传至阿里云
     */
    String uploadFileAvatar(MultipartFile file);
}


