package cn.GnaixEuy.eduservice.service;

import cn.GnaixEuy.eduservice.entity.vo.CourseInfoVo;

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
public interface EduCourseService {
    /**
     * 保存课程信息
     *
     * @param courseInfoVo 课程信息vo
     * @return string
     */
    String saveCourseInfo(CourseInfoVo courseInfoVo);
}
