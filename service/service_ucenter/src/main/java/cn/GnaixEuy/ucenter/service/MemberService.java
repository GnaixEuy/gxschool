package cn.GnaixEuy.ucenter.service;

import cn.GnaixEuy.ucenter.entity.Member;
import cn.GnaixEuy.ucenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author GnaixEuy
 * @since 2023-01-12
 */
public interface MemberService extends IService<Member> {
    /**
     * 登录的方法
     */
    String login(Member member);

    /**
     * 注册的方法
     */
    void register(RegisterVo registerVo);
}
