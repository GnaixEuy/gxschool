package cn.GnaixEuy.eduservice.mapper;

import cn.GnaixEuy.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author gnaixeuy
 * @description 针对表【edu_subject(课程科目)】的数据库操作Mapper
 * @createDate 2023-01-09 00:51:29
 * @Entity cn.GnaixEuy.eduservice.entity.EduSubject
 */
@Mapper
public interface EduSubjectMapper extends BaseMapper<EduSubject> {
}




