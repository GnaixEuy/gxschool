package cn.GnaixEuy.edu.mapper;

import cn.GnaixEuy.edu.entity.EduCourse;
import cn.GnaixEuy.edu.entity.vo.CoursePublishVo;
import cn.GnaixEuy.edu.entity.vo.CourseWebVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    /**
     * 获取发布公开的课程信息
     *
     * @param courseId
     * @return
     */
    CoursePublishVo getPublishCourseInfo(String courseId);

    /**
     * 根据课程id，编写sql语句查询课程信息
     */
    CourseWebVo getBaseCourseInfo(String courseId);
}
