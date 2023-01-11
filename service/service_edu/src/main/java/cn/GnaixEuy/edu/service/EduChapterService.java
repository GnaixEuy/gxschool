package cn.GnaixEuy.edu.service;

import cn.GnaixEuy.edu.entity.EduChapter;
import cn.GnaixEuy.edu.entity.chapter.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

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
public interface EduChapterService extends IService<EduChapter> {
    /**
     * 课程大纲列表,根据课程id进行查询
     */
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    /**
     * 删除章节的方法
     */
    boolean deleteChapter(String chapterId);


    /**
     * 根据课程id删除章节
     */
    void removeChapterByCourseId(String courseId);
}

