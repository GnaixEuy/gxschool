package cn.GnaixEuy.edu.service.impl;

import cn.GnaixEuy.edu.entity.EduSubject;
import cn.GnaixEuy.edu.entity.excel.SubjectData;
import cn.GnaixEuy.edu.entity.subject.OneSubject;
import cn.GnaixEuy.edu.entity.subject.TwoSubject;
import cn.GnaixEuy.edu.mapper.EduSubjectMapper;
import cn.GnaixEuy.edu.service.EduSubjectService;
import cn.GnaixEuy.edu.service.listener.SubjectExcelListener;
import cn.GnaixEuy.servicebase.config.exception.BizException;
import cn.GnaixEuy.servicebase.config.exception.ExceptionType;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gnaixeuy
 * @description 针对表【edu_subject(课程科目)】的数据库操作Service实现
 * @createDate 2023-01-09 00:51:29
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    /**
     * 添加课程分类
     */
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            throw new BizException(ExceptionType.CLASS_SUBJECT_ADD_EXCEPTION);
        }
    }

    /**
     * 获取所有一二级课程类别分类
     *
     * @return List<OneSubject>
     */
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //查询所有一级分类 parentId=0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);
        //查询所有二级分类 parentId!=0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperOne.ne("parent_id", "0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);
        List<OneSubject> finalSubjectList = new ArrayList<>();
        for (EduSubject eduSubject : oneSubjectList) {
            OneSubject oneSubject = new OneSubject();
            //把eduSubject值复制到对应的oneSubject对象里面，两个对象里面的属性相同对应的的自动赋值
            BeanUtils.copyProperties(eduSubject, oneSubject);
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            for (EduSubject tSubject : twoSubjectList) {
                if (tSubject.getParentId().equals(eduSubject.getId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject, twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoFinalSubjectList);
            finalSubjectList.add(oneSubject);
        }
        return finalSubjectList;
    }

}




