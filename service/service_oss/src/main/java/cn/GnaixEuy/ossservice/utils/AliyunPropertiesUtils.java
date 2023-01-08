package cn.GnaixEuy.ossservice.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 * 常量类，读取配置文件application.properties中的配置
 * <p>项目： gxschool_parent </p>
 * 创建日期： 2023/1/8
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Component
public class AliyunPropertiesUtils implements InitializingBean {
    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.key_id}")
    private String keyId;
    @Value("${aliyun.oss.file.key_secret}")
    private String keySecret;
    @Value("${aliyun.oss.file.bucket_name}")
    private String bucketName;

    @Override
    public void afterPropertiesSet() throws Exception {
        AliyunPropertiesUtils.END_POINT = this.endpoint;
        AliyunPropertiesUtils.ACCESS_KEY_ID = this.keyId;
        AliyunPropertiesUtils.ACCESS_KEY_SECRET = this.keySecret;
        AliyunPropertiesUtils.BUCKET_NAME = this.bucketName;
    }
}

