package cn.GnaixEuy.edu.controller;

import cn.GnaixEuy.commonutils.ResultVo;
import cn.GnaixEuy.edu.entity.subject.OneSubject;
import cn.GnaixEuy.edu.service.EduSubjectService;
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
@Api(tags = "课程分类管理")
@CrossOrigin
@RestController
@RequestMapping(value = {"/eduservice/subject"})
public class EduSubjectController {
    private EduSubjectService eduSubjectService;

    /**
     * 添加课程分类
     * 获取上传过来的文件，把内容读取出来，就不用上传到服务器
     */
    @ApiOperation(value = "Excel批量导入")
    @PostMapping(value = {"addSubject"})
    public ResultVo addSubject(MultipartFile file) {
        // 获取上传的excel文件 MultipartFile
        this.eduSubjectService.saveSubject(file, this.eduSubjectService);
        return ResultVo.ok();
    }

    /**
     * 课程分类列表(树形)
     */
    @ApiOperation(value = "嵌套数据列表")
    @GetMapping(value = {"/getAllSubject"})
    public ResultVo getAllSubject() {
        //list集合泛型是一级分类，因为一级分类有他本身和二级分类的集合
        List<OneSubject> list = this.eduSubjectService.getAllOneTwoSubject();
        return ResultVo.ok().data("list", list);
    }


    @Autowired
    public void setEduSubjectService(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }
}

