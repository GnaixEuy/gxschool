package cn.GnaixEuy.msm.controller;

import cn.GnaixEuy.commonutils.ResultVo;
import cn.GnaixEuy.msm.service.MsmService;
import cn.GnaixEuy.msm.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： gxschool_parent </p>
 * 创建日期： 2023/1/12
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */

@CrossOrigin
@RestController
@RequestMapping(value = {"/edumsm/msm"})
public class MsmController {
    private MsmService msmService;
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping(value = {"send/{phone}"})
    public ResultVo sendMsm(@PathVariable String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return ResultVo.ok();
        }
        code = RandomUtil.getFourBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        boolean isSend = msmService.send(param, phone);
        if (isSend) {
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return ResultVo.ok();
        } else {
            return ResultVo.error().message("短信发送失败");
        }
    }

    @Autowired
    public void setMsmService(MsmService msmService) {
        this.msmService = msmService;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
