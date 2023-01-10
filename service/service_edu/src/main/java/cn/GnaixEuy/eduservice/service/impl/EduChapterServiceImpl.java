package cn.GnaixEuy.eduservice.service.impl;

import cn.GnaixEuy.eduservice.entity.EduChapter;
import cn.GnaixEuy.eduservice.entity.EduVideo;
import cn.GnaixEuy.eduservice.entity.chapter.ChapterVo;
import cn.GnaixEuy.eduservice.entity.chapter.VideoVo;
import cn.GnaixEuy.eduservice.mapper.EduChapterMapper;
import cn.GnaixEuy.eduservice.service.EduChapterService;
import cn.GnaixEuy.eduservice.service.EduVideoService;
import cn.GnaixEuy.servicebase.config.exception.BizException;
import cn.GnaixEuy.servicebase.config.exception.ExceptionType;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //根据课程id查询课程里面所有章节
        List<EduChapter> eduChapterList = baseMapper.selectList(
                Wrappers.<EduChapter>lambdaQuery().
                        eq(EduChapter::getCourseId, courseId));
        //根据课程id查询课程里面所有的小节
        List<EduVideo> eduVideoList = videoService.list(
                Wrappers.<EduVideo>lambdaQuery()
                        .eq(EduVideo::getCourseId, courseId));
        List<ChapterVo> finalList = new ArrayList<>();
        for (EduChapter chapter : eduChapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            List<VideoVo> videoList = new ArrayList<>();
            for (EduVideo eduVideo : eduVideoList) {
                if (eduVideo.getChapterId().equals(chapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    videoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoList);
            finalList.add(chapterVo);
        }
        return finalList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        long count = videoService.count(
                Wrappers.<EduVideo>lambdaQuery()
                        .eq(EduVideo::getChapterId, chapterId));
        if (count > 0) {
            throw new BizException(ExceptionType.CHAPTER_INFO_DELETE_EXCEPTION);
        } else {
            int result = baseMapper.deleteById(chapterId);
            return result > 0;
        }
    }

    @Autowired
    public void setVideoService(EduVideoService videoService) {
        this.videoService = videoService;
    }
}
