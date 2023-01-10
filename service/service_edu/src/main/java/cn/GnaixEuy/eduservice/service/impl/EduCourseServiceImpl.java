package cn.GnaixEuy.eduservice.service.impl;

import cn.GnaixEuy.eduservice.entity.EduCourse;
import cn.GnaixEuy.eduservice.entity.EduCourseDescription;
import cn.GnaixEuy.eduservice.entity.vo.CourseInfoVo;
import cn.GnaixEuy.eduservice.mapper.EduCourseMapper;
import cn.GnaixEuy.eduservice.service.EduCourseDescriptionService;
import cn.GnaixEuy.eduservice.service.EduCourseService;
import cn.GnaixEuy.servicebase.config.exception.BizException;
import cn.GnaixEuy.servicebase.config.exception.ExceptionType;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    private EduCourseDescriptionService courseDescriptionService;

    /**
     * 保存课程信息
     *
     * @param courseInfoVo 课程信息vo
     * @return string
     */
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        //再用mapstruct就是故意折磨自己
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0) {
            throw new BizException(ExceptionType.CLASS_INFO_ADD_EXCEPTION);
        }
        String cid = eduCourse.getId();
        //向课程简介表添加课程介绍
        EduCourseDescription CourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo, CourseDescription);
        CourseDescription.setId(cid);
        courseDescriptionService.save(CourseDescription);
        return cid;
    }


    @Autowired
    public void setCourseDescriptionService(EduCourseDescriptionService courseDescriptionService) {
        this.courseDescriptionService = courseDescriptionService;
    }
}
