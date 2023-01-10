package cn.GnaixEuy.eduservice.service.listener;

import cn.GnaixEuy.eduservice.entity.EduSubject;
import cn.GnaixEuy.eduservice.entity.excel.SubjectData;
import cn.GnaixEuy.eduservice.service.EduSubjectService;
import cn.GnaixEuy.servicebase.config.exception.BizException;
import cn.GnaixEuy.servicebase.config.exception.ExceptionType;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    public EduSubjectService subjectService;

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new BizException(ExceptionType.FILE_EMPTY_EXCEPTION);
        }
        //一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
        EduSubject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (existOneSubject == null) {
            //没有相同的一级分类就进行添加
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            //设置一级分类名称
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existOneSubject);
        }
        //获取一级分类id值
        String pid = existOneSubject.getId();
        //添加二级分类
        //判断二级分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if (existTwoSubject == null) {
            //没有相同的一级分类就进行添加
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            //设置二级分类名称
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }
    }

    //判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        return subjectService.getOne(wrapper);
    }

    //判断二级分类不能重复添加
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        return subjectService.getOne(wrapper);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}
