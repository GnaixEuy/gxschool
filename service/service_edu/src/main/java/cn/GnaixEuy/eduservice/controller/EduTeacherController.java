package cn.GnaixEuy.eduservice.controller;


import cn.GnaixEuy.commonutils.ResultVo;
import cn.GnaixEuy.eduservice.entity.EduTeacher;
import cn.GnaixEuy.eduservice.entity.vo.TeacherQueryVo;
import cn.GnaixEuy.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author GnaixEuy
 * @since 2023-01-06
 */
@Api
@CrossOrigin
@RestController
@RequestMapping(value = {"/eduservice/teacher"})
public class EduTeacherController {

    private EduTeacherService eduTeacherService;

    //查询讲师表所有数据
    @ApiOperation(value = "所有讲师列表")
    @GetMapping(value = {"findAll"})
    public ResultVo findAllTeacher() {
        //调用service方法实现查询所有
        List<EduTeacher> list = eduTeacherService.list(null);
        return ResultVo.ok().data("items", list);
    }

    //逻辑删除讲师
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping(value = {"{id}"})
    public ResultVo removeTeacher(@PathVariable("id") String id) {
        boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            return ResultVo.ok();
        } else {
            return ResultVo.error();
        }
    }

    @ApiOperation(value = "分页查询讲师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "limit", value = "每页记录")
    })
    @GetMapping(value = {"/pageTeacher/{current}/{limit}"})
    public ResultVo pageListTeacher(@PathVariable Long current, @PathVariable Long limit) {
        //创建page对象
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
        //调用方法实现分页
        //调用方法时，底层封装，把分页所有数据封装到teacherPage对象里面
        eduTeacherService.page(teacherPage, null);
        //总记录数
        long total = teacherPage.getTotal();
        //数据list集合
        List<EduTeacher> records = teacherPage.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);
        return ResultVo.ok().data(map);
    }

    @ApiOperation(value = "带条件分页查询讲师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "limit", value = "每页记录")
    })
    @PostMapping(value = {"pageTeacherCondition/{current}/{limit}"})
    public ResultVo pageTeacherCondition(@PathVariable Long current, @PathVariable Long limit, @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        //创建page对象
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String begin = teacherQueryVo.getBegin();
        String end = teacherQueryVo.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }
        //调用方法实现分页
        //调用方法时，底层封装，把分页所有数据封装到teacherPage对象里面
        eduTeacherService.page(teacherPage, wrapper);
        //总记录数
        long total = teacherPage.getTotal();
        //数据list集合
        List<EduTeacher> records = teacherPage.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);
        return ResultVo.ok().data(map);
    }


    @PostMapping(value = {"addTeacher"})
    public ResultVo addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return ResultVo.ok();
        } else {
            return ResultVo.error();
        }
    }

    @ApiOperation(value = "根据讲师id进行查询")
    @ApiImplicitParam(name = "id", value = "讲师id", dataType = "string")
    @GetMapping("/{id}")
    public ResultVo getTeacher(@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return ResultVo.ok().data("teacher", eduTeacher);
    }

    @ApiOperation(value = "讲师修改功能")
    @PutMapping("/updateTeacher")
    public ResultVo updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag) {
            return ResultVo.ok();
        } else {
            return ResultVo.error();
        }
    }

    @Autowired
    public void setEduTeacherService(EduTeacherService eduTeacherService) {
        this.eduTeacherService = eduTeacherService;
    }
}


