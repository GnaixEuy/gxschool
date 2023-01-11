package cn.GnaixEuy.edu.controller;

import cn.GnaixEuy.commonutils.ResultVo;
import cn.GnaixEuy.edu.entity.EduChapter;
import cn.GnaixEuy.edu.entity.chapter.ChapterVo;
import cn.GnaixEuy.edu.service.EduChapterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "章节管理")
@CrossOrigin
@RestController
@RequestMapping(value = {"/eduservice/chapter"})
public class EduChapterController {
    private EduChapterService eduChapterService;

    /**
     * 课程大纲列表,根据课程id进行查询
     */
    @GetMapping("getChapterVideo/{courseId}")
    public ResultVo getChapterVideo(@PathVariable String courseId) {
        List<ChapterVo> list = this.eduChapterService.getChapterVideoByCourseId(courseId);
        return ResultVo.ok().data("allChapterVideo", list);
    }

    /**
     * 添加章节
     */
    @PostMapping(value = {"addChapter"})
    public ResultVo addChapter(@RequestBody EduChapter eduChapter) {
        this.eduChapterService.save(eduChapter);
        return ResultVo.ok();
    }

    /**
     * 根据章节id查询
     */
    @GetMapping(value = {"getChapterInfo/{chapterId}"})
    public ResultVo getChapterInfo(@PathVariable String chapterId) {
        EduChapter eduChapter = this.eduChapterService.getById(chapterId);
        return ResultVo.ok().data("chapter", eduChapter);
    }

    //修改章节
    @PostMapping("updateChapter")
    public ResultVo updateChapter(@RequestBody EduChapter eduChapter) {
        this.eduChapterService.updateById(eduChapter);
        return ResultVo.ok();
    }

    /**
     * 删除的方法
     */
    @DeleteMapping(value = {"{chapterId}"})
    public ResultVo deleteChapter(@PathVariable String chapterId) {
        boolean flag = this.eduChapterService.deleteChapter(chapterId);
        if (flag) {
            return ResultVo.ok();
        } else {
            return ResultVo.error();
        }
    }

    @Autowired
    public void setEduChapterService(EduChapterService eduChapterService) {
        this.eduChapterService = eduChapterService;
    }
}
