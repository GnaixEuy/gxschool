package cn.GnaixEuy.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
public class ConstantVodUtils implements InitializingBean {
    public static String ACCESS_KEY_SECRET;
    public static String ACCESS_KEY_ID;
    @Value("${aliyun.vod.file.key_id}")
    private String keyId;
    @Value("${aliyun.vod.file.key_secret}")
    private String keySecret;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = this.keyId;
        ACCESS_KEY_SECRET = this.keySecret;
    }
}

