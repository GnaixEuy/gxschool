package cn.GnaixEuy.ucenter.service.impl;

import cn.GnaixEuy.commonutils.utils.JwtUtils;
import cn.GnaixEuy.commonutils.utils.MD5;
import cn.GnaixEuy.servicebase.config.exception.BizException;
import cn.GnaixEuy.servicebase.config.exception.ExceptionType;
import cn.GnaixEuy.ucenter.entity.Member;
import cn.GnaixEuy.ucenter.entity.vo.RegisterVo;
import cn.GnaixEuy.ucenter.mapper.MemberMapper;
import cn.GnaixEuy.ucenter.service.MemberService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author GnaixEuy
 * @since 2023-01-12
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    private RedisTemplate<String, String> redisTemplate;

    /**
     * 登录的方法
     *
     * @param member
     */
    public String login(Member member) {

        String mobile = member.getMobile();
        String password = member.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new BizException(ExceptionType.LOGIN_EXCEPTION);
        }
        Member mobileMember = baseMapper.selectOne(Wrappers.<Member>lambdaQuery()
                .eq(Member::getMobile, mobile));
        if (mobileMember == null) {
            throw new BizException(ExceptionType.LOGIN_EXCEPTION);
        }
        //加密方式 MD5
        if (!MD5.encrypt(password).equals(mobileMember.getPassword())) {
            throw new BizException(ExceptionType.LOGIN_EXCEPTION);
        }

        //判断用户是否禁用
        if (mobileMember.getIsDisabled()) {
            throw new BizException(ExceptionType.LOGIN_EXCEPTION);
        }
        return JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
    }

    /**
     * 注册的方法
     *
     * @param registerVo
     */
    @Override
    public void register(RegisterVo registerVo) {
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname)) {
            throw new BizException(ExceptionType.REGISTER_EXCEPTION);
        }
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(redisCode)) {
            throw new BizException(ExceptionType.REGISTER_EXCEPTION);
        }
        Long count = baseMapper.selectCount(Wrappers.<Member>lambdaQuery()
                .eq(Member::getMobile, mobile));
        if (count > 0) {
            throw new BizException(ExceptionType.REGISTER_EXCEPTION);
        }
        Member member = new Member();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("http://blog.gnaixeuy.cn/wp-content/uploads/2021/04/cropped-srchttp-img.zcool_.cn-community-013cac57adc7dc0000012e7e85cfe0.jpg@900w_1l_2o_100sh.jpgreferhttp-img.zcool_.cnapp2002sizef999910000qa80n0g0nfmtjpeg.jpeg");
        baseMapper.insert(member);
    }
}
