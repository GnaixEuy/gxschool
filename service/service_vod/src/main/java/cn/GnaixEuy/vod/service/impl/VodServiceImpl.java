package cn.GnaixEuy.vod.service.impl;

import cn.GnaixEuy.vod.service.VodService;
import cn.GnaixEuy.vod.utils.ConstantVodUtils;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

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
@Service
public class VodServiceImpl implements VodService {
    /**
     * 上传视频到阿里云
     *
     * @param file
     */
    @Override
    public String uploadVideoAly(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            //title：上传之后显示名称
            assert fileName != null;
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(
                    ConstantVodUtils.ACCESS_KEY_ID,
                    ConstantVodUtils.ACCESS_KEY_SECRET,
                    title,
                    fileName,
                    inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            return response.getVideoId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
