package cn.GnaixEuy.ossservice.service.impl;

import cn.GnaixEuy.ossservice.service.OssService;
import cn.GnaixEuy.ossservice.utils.AliyunPropertiesUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

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
@Service
public class OssServiceImpl implements OssService {
    /**
     * 上传头像到oss
     */
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        //工具类获取值
        String endpoint = AliyunPropertiesUtils.END_POINT;
        String accessKeyId = AliyunPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = AliyunPropertiesUtils.ACCESS_KEY_SECRET;
        String backetName = AliyunPropertiesUtils.BUCKET_NAME;
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = file.getInputStream();
            //获取文件名
            String filename = file.getOriginalFilename();
            //在文件名称里添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replace("-", "");
            filename = uuid + filename;
            //把文件按日期分类
            String datePath = new DateTime().toString("yyyy/MM/dd");
            //第一个参数 Backet名称
            //第二个参数 上传到oss文件路径和文件名称
            //第三个参数 上传文件输入流
            //拼接路径
            filename = datePath + "/" + filename;
            ossClient.putObject(backetName, filename, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //需要把上传文件到阿里云的路径手动拼接出来
            // https://gulischool-dyk.oss-cn-beijing.aliyuncs.com/1.png
            return "https://" + backetName + "." + endpoint + "/" + filename;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }
    }
}
