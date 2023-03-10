package cn.GnaixEuy.edu.service;

import cn.GnaixEuy.edu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author GnaixEuy
 * @since 2023-01-06
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 分页查询讲师的方法
     *
     * @param pageTeacher
     * @return
     */
    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
