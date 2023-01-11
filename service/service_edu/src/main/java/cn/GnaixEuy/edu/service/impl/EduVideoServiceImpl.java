package cn.GnaixEuy.edu.service.impl;

import cn.GnaixEuy.edu.entity.EduVideo;
import cn.GnaixEuy.edu.mapper.EduVideoMapper;
import cn.GnaixEuy.edu.service.EduVideoService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    /**
     * 根据课程id删除小节
     *
     * @param courseId
     */
    @Override
    public void removeVideoByCourseId(String courseId) {
        baseMapper.delete(Wrappers.<EduVideo>lambdaQuery().eq(EduVideo::getCourseId, courseId));
    }


}
