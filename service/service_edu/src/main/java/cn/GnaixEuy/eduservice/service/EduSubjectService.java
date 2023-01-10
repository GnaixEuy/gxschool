package cn.GnaixEuy.eduservice.service;

import cn.GnaixEuy.eduservice.entity.EduSubject;
import cn.GnaixEuy.eduservice.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author gnaixeuy
 * @description 针对表【edu_subject(课程科目)】的数据库操作Service
 * @createDate 2023-01-09 00:51:29
 */
public interface EduSubjectService extends IService<EduSubject> {
    /**
     * 添加课程分类
     */
    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    /**
     * 获取所有一二级课程类别分类
     *
     * @return List<OneSubject>
     */
    List<OneSubject> getAllOneTwoSubject();

}
