package cn.GnaixEuy.edu.service.impl;

import cn.GnaixEuy.edu.entity.EduCourse;
import cn.GnaixEuy.edu.entity.EduCourseDescription;
import cn.GnaixEuy.edu.entity.vo.CourseFrontVo;
import cn.GnaixEuy.edu.entity.vo.CourseInfoVo;
import cn.GnaixEuy.edu.entity.vo.CoursePublishVo;
import cn.GnaixEuy.edu.entity.vo.CourseWebVo;
import cn.GnaixEuy.edu.mapper.EduCourseMapper;
import cn.GnaixEuy.edu.service.EduChapterService;
import cn.GnaixEuy.edu.service.EduCourseDescriptionService;
import cn.GnaixEuy.edu.service.EduCourseService;
import cn.GnaixEuy.edu.service.EduVideoService;
import cn.GnaixEuy.servicebase.config.exception.BizException;
import cn.GnaixEuy.servicebase.config.exception.ExceptionType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private EduCourseDescriptionService eduCourseDescriptionService;
    private EduVideoService eduVideoService;
    private EduChapterService eduChapterService;

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
        System.out.println(courseInfoVo);
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        eduCourse.setIsDeleted(0);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0) {
            throw new BizException(ExceptionType.CLASS_INFO_ADD_EXCEPTION);
        }
        String cid = eduCourse.getId();
        //向课程简介表添加课程介绍
        EduCourseDescription CourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo, CourseDescription);
        CourseDescription.setId(cid);
        this.eduCourseDescriptionService.save(CourseDescription);
        return cid;
    }

    /**
     * 通过id查询课程信息
     *
     * @param courseId
     */
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //查询课程表
        EduCourse eduCourse = this.baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);
        //查询描述表
        EduCourseDescription courseDescription = this.eduCourseDescriptionService.getById(courseId);
        BeanUtils.copyProperties(courseDescription, courseInfoVo);
        return courseInfoVo;
    }

    /**
     * 更新课程信息业务
     *
     * @param courseInfoVo
     */
    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int i = baseMapper.updateById(eduCourse);
        if (i == 0) {
            throw new BizException(ExceptionType.CLASS_INFO_UPDATE_EXCEPTION);
        }
        EduCourseDescription courseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo, courseDescription);
        this.eduCourseDescriptionService.updateById(courseDescription);
    }

    /**
     * 根据课程id查询课程确认信息
     *
     * @param id
     */
    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        return baseMapper.getPublishCourseInfo(id);
    }

    /**
     * 删除课程
     *
     * @param courseId
     */
    @Override
    public void removeCourse(String courseId) {
        this.eduVideoService.removeVideoByCourseId(courseId);
        this.eduChapterService.removeChapterByCourseId(courseId);
        this.eduCourseDescriptionService.removeById(courseId);
        int result = baseMapper.deleteById(courseId);
        if (result == 0) {
            throw new BizException(ExceptionType.DELETE_EXCEPTION);
        }
    }

    /**
     * 条件查询带分页查询课程前台
     *
     * @param pageCourse
     * @param courseFrontVo
     * @return
     */
    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) {
            wrapper.eq("subject_parent_id", courseFrontVo.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(courseFrontVo.getSubjectId())) {
            wrapper.eq("subject_id", courseFrontVo.getSubjectId());
        }
        if (!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())) {
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) {
            wrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())) {
            wrapper.orderByDesc("price");
        }
        baseMapper.selectPage(pageCourse, wrapper);
        List<EduCourse> records = pageCourse.getRecords();
        long current = pageCourse.getCurrent();
        long pages = pageCourse.getPages();
        long size = pageCourse.getSize();
        long total = pageCourse.getTotal();
        boolean hasNext = pageCourse.hasNext();
        boolean hasPrevious = pageCourse.hasPrevious();
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }

    /**
     * 根据课程id，编写sql语句查询课程信息
     *
     * @param courseId
     * @return
     */
    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }

    @Autowired
    public void setEduCourseDescriptionService(EduCourseDescriptionService eduCourseDescriptionService) {
        this.eduCourseDescriptionService = eduCourseDescriptionService;
    }

    @Autowired
    public void setEduVideoService(EduVideoService eduVideoService) {
        this.eduVideoService = eduVideoService;
    }

    @Autowired
    public void setEduChapterService(EduChapterService eduChapterService) {
        this.eduChapterService = eduChapterService;
    }
}
