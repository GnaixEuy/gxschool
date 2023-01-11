package cn.GnaixEuy.edu.service;

import cn.GnaixEuy.edu.entity.EduCourse;
import cn.GnaixEuy.edu.entity.vo.CourseInfoVo;
import cn.GnaixEuy.edu.entity.vo.CoursePublishVo;
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
public interface EduCourseService extends IService<EduCourse> {
    /**
     * 保存课程信息
     *
     * @param courseInfoVo 课程信息vo
     * @return string
     */
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * 通过id查询课程信息
     */
    CourseInfoVo getCourseInfo(String courseId);

    /**
     * 更新课程信息业务
     *
     * @param courseInfoVo
     */
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * 根据课程id查询课程确认信息
     */
    CoursePublishVo publishCourseInfo(String id);


    /**
     * 删除课程
     */
    void removeCourse(String courseId);
}