package cn.GnaixEuy.eduservice.controller;

import cn.GnaixEuy.commonutils.ResultVo;
import cn.GnaixEuy.eduservice.entity.subject.OneSubject;
import cn.GnaixEuy.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： gxschool_parent </p>
 * 创建日期： 2023/1/9
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@CrossOrigin
@Api(tags = "课程分类管理")
@RestController
@RequestMapping(value = {"/eduservice/subject"})
public class EduSubjectController {
    private EduSubjectService subjectService;

    /**
     * 添加课程分类
     * 获取上传过来的文件，把内容读取出来，就不用上传到服务器
     */
    @ApiOperation(value = "Excel批量导入")
    @PostMapping(value = {"addSubject"})
    public ResultVo addSubject(MultipartFile file) {
        // 获取上传的excel文件 MultipartFile
        subjectService.saveSubject(file, subjectService);
        return ResultVo.ok();
    }

    /**
     * 课程分类列表(树形)
     */
    @ApiOperation(value = "嵌套数据列表")
    @GetMapping(value = {"/getAllSubject"})
    public ResultVo getAllSubject() {
        //list集合泛型是一级分类，因为一级分类有他本身和二级分类的集合
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return ResultVo.ok().data("list", list);
    }


    @Autowired
    public void setSubjectService(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }
}

