package cn.GnaixEuy.edu.controller.front;

import cn.GnaixEuy.commonutils.ResultVo;
import cn.GnaixEuy.edu.entity.EduCourse;
import cn.GnaixEuy.edu.entity.EduTeacher;
import cn.GnaixEuy.edu.service.EduCourseService;
import cn.GnaixEuy.edu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
@RequestMapping(value = {"/eduservice/teacherfront"})
public class TeacherFrontController {
    private EduTeacherService eduTeacherService;
    private EduCourseService eduCourseService;

    /**
     * 分页查询讲师的方法
     */
    @PostMapping(value = {"getTeacherFrontList/{page}/{limit}"})
    public ResultVo getTeacherFrontList(@PathVariable long page, @PathVariable long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(page, limit);
        Map<String, Object> map = this.eduTeacherService.getTeacherFrontList(pageTeacher);
        //返回分页所有数据
        return ResultVo.ok().data(map);
    }

    /**
     * 讲师详情的功能
     *
     * @param teacherId
     * @return
     */
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public ResultVo getTeacherFrontInfo(@PathVariable String teacherId) {
        EduTeacher eduTeacher = this.eduTeacherService.getById(teacherId);
        List<EduCourse> courseList = this.eduCourseService.list(Wrappers.<EduCourse>lambdaQuery().eq(
                EduCourse::getTeacherId, teacherId
        ));
        return ResultVo.ok().data("teacher", eduTeacher).data("courseList", courseList);
    }

    @Autowired
    public void setEduTeacherService(EduTeacherService eduTeacherService) {
        this.eduTeacherService = eduTeacherService;
    }

    @Autowired
    public void setEduCourseService(EduCourseService eduCourseService) {
        this.eduCourseService = eduCourseService;
    }
}

