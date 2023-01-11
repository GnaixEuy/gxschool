package cn.GnaixEuy.edu.service.impl;

import cn.GnaixEuy.edu.entity.EduTeacher;
import cn.GnaixEuy.edu.mapper.EduTeacherMapper;
import cn.GnaixEuy.edu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author GnaixEuy
 * @since 2023-01-06
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    /**
     * 分页查询讲师的方法
     */
    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageParam) {
        baseMapper.selectPage(pageParam, Wrappers.<EduTeacher>lambdaQuery().orderByDesc(EduTeacher::getId));
        List<EduTeacher> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }

}
