package cn.GnaixEuy.edu.controller;

import cn.GnaixEuy.commonutils.ResultVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： gxschool_parent </p>
 * 创建日期： 2023/1/7
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@CrossOrigin
@RestController
@Api(tags = "登录管理")
@RequestMapping(value = {"/eduservice/user"})
public class EduLoginController {

    @PostMapping(value = {"/login"})
    public ResultVo login() {
        return ResultVo.ok().data("token", "admin");
    }

    @GetMapping(value = {"/info"})
    public ResultVo info() {
        return ResultVo.ok()
                .data("roles", "[admin]")
                .data("name", "admin")
                .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}

