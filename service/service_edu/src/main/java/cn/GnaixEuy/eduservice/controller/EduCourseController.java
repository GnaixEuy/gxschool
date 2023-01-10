package cn.GnaixEuy.eduservice.controller;

import cn.GnaixEuy.commonutils.ResultVo;
import cn.GnaixEuy.eduservice.entity.vo.CourseInfoVo;
import cn.GnaixEuy.eduservice.entity.vo.CoursePublishVo;
import cn.GnaixEuy.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "课程管理")
@CrossOrigin
@RestController
@RequestMapping(value = {"/eduservice/course"})
public class EduCourseController {
    private EduCourseService eduCourseService;

    /**
     * 添加课程基本信息
     */
    @ApiOperation(value = "新增课程")
    @PostMapping(value = {"/addCourseInfo"})
    public ResultVo addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = this.eduCourseService.saveCourseInfo(courseInfoVo);
        return ResultVo.ok().data("courseId", id);
    }

    /**
     * 根据课程id查询课程基本信息
     */
    @GetMapping(value = {"getCourseInfo/{courseId}"})
    public ResultVo getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = this.eduCourseService.getCourseInfo(courseId);
        return ResultVo.ok().data("courseInfoVo", courseInfoVo);
    }

    /**
     * 修改课程信息
     */
    @PostMapping(value = {"updateCourseInfo"})
    public ResultVo updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        this.eduCourseService.updateCourseInfo(courseInfoVo);
        return ResultVo.ok();
    }

    /**
     * 根据课程id查询课程确认信息
     */
    @GetMapping(value = {"getPublishCourseInfo/{id}"})
    public ResultVo getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = this.eduCourseService.publishCourseInfo(id);
        return ResultVo.ok().data("publishCourse", coursePublishVo);
    }

    @Autowired
    public void setEduCourseService(EduCourseService eduCourseService) {
        this.eduCourseService = eduCourseService;
    }
}

