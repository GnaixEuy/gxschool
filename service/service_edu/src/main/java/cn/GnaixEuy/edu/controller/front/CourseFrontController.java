package cn.GnaixEuy.edu.controller.front;

import cn.GnaixEuy.commonutils.ResultVo;
import cn.GnaixEuy.edu.entity.EduCourse;
import cn.GnaixEuy.edu.entity.chapter.ChapterVo;
import cn.GnaixEuy.edu.entity.vo.CourseFrontVo;
import cn.GnaixEuy.edu.entity.vo.CourseWebVo;
import cn.GnaixEuy.edu.service.EduChapterService;
import cn.GnaixEuy.edu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： gxschool_parent </p>
 * 创建日期： 2023/1/12
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@CrossOrigin
@RestController
@RequestMapping(value = {"/eduservice/coursefront"})
public class CourseFrontController {
    private EduCourseService eduCourseService;
    private EduChapterService eduChapterService;

    /**
     * 条件查询带分页查询课程
     */
    @PostMapping(value = {"getFrontCourseList/{page}/{limit}"})
    public ResultVo getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                       @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(page, limit);
        Map<String, Object> map = this.eduCourseService.getCourseFrontList(pageCourse, courseFrontVo);
        return ResultVo.ok().data(map);
    }

    /**
     * 课程详情的方法
     */
    @GetMapping(value = {"getFrontCourseInfo/{courseId}"})
    public ResultVo getFrontCourseInfo(@PathVariable String courseId) {
        CourseWebVo courseWebVo = this.eduCourseService.getBaseCourseInfo(courseId);
        List<ChapterVo> chapterVideoList = this.eduChapterService.getChapterVideoByCourseId(courseId);
        return ResultVo.ok().data("courseWebVo", courseWebVo).data("chapterVideoList", chapterVideoList);
    }

    @Autowired
    public void setEduCourseService(EduCourseService eduCourseService) {
        this.eduCourseService = eduCourseService;
    }

    @Autowired
    public void setEduChapterService(EduChapterService eduChapterService) {
        this.eduChapterService = eduChapterService;
    }
}

