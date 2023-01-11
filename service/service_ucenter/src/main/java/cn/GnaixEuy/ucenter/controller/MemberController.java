package cn.GnaixEuy.ucenter.controller;


import cn.GnaixEuy.commonutils.ResultVo;
import cn.GnaixEuy.commonutils.utils.JwtUtils;
import cn.GnaixEuy.ucenter.entity.Member;
import cn.GnaixEuy.ucenter.entity.vo.RegisterVo;
import cn.GnaixEuy.ucenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author GnaixEuy
 * @since 2023-01-12
 */
@CrossOrigin
@RestController
@RequestMapping(value = {"/educenter/member"})
public class MemberController {
    private MemberService memberService;

    /**
     * 登录
     */
    @PostMapping(value = {"login"})
    public ResultVo loginUser(@RequestBody Member member) {
        String token = this.memberService.login(member);
        return ResultVo.ok().data("token", token);
    }

    /**
     * 注册
     */
    @PostMapping(value = {"register"})
    public ResultVo registerUser(@RequestBody RegisterVo registerVo) {
        this.memberService.register(registerVo);
        return ResultVo.ok();
    }

    /**
     * 根据token获取用户信息
     */
    @GetMapping(value = {"getMemberInfo"})
    public ResultVo getMemberInfo(HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        Member member = this.memberService.getById(memberId);
        return ResultVo.ok().data("userInfo", member);
    }

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
}
