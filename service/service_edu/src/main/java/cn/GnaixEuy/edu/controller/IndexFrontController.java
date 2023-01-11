package cn.GnaixEuy.edu.controller;

import cn.GnaixEuy.commonutils.ResultVo;
import cn.GnaixEuy.edu.entity.EduCourse;
import cn.GnaixEuy.edu.entity.EduTeacher;
import cn.GnaixEuy.edu.service.EduCourseService;
import cn.GnaixEuy.edu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
@CrossOrigin
@RestController
@RequestMapping(value = {"/eduservice/indexfront"})
public class IndexFrontController {
    private EduCourseService eduCourseService;
    private EduTeacherService eduTeacherService;

    /**
     * 查询前8条热门课程，查询前4条名师
     */
    @GetMapping(value = {"index"})
    public ResultVo index() {
        //查询前8条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> eduList = this.eduCourseService.list(wrapper);
        //查询前4条名师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = this.eduTeacherService.list(wrapperTeacher);
        return ResultVo.ok().data("eduList", eduList).data("teacherList", teacherList);
    }

    @Autowired
    public void setEduCourseService(EduCourseService eduCourseService) {
        this.eduCourseService = eduCourseService;
    }

    @Autowired
    public void setEduTeacherService(EduTeacherService eduTeacherService) {
        this.eduTeacherService = eduTeacherService;
    }
}
