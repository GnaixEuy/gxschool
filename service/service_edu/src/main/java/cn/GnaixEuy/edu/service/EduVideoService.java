package cn.GnaixEuy.edu.service;

import cn.GnaixEuy.edu.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

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
public interface EduVideoService extends IService<EduVideo> {

    /**
     * 根据课程id删除小节
     */
    void removeVideoByCourseId(String courseId);


}

