package cn.GnaixEuy.oss.controller;

import cn.GnaixEuy.commonutils.ResultVo;
import cn.GnaixEuy.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： gxschool_parent </p>
 * 创建日期： 2023/1/8
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@CrossOrigin
@RestController
@Api(tags = "阿里云文件管理")
@RequestMapping(value = {"/eduoss/fileoss"})
public class OssController {
    private OssService ossService;

    //上传头像的方法
    @ApiOperation(value = "文件上传")
    @PostMapping(value = {""})
    public ResultVo uploadOssFile(@ApiParam(name = "file", value = "文件", required = true) MultipartFile file) {
        //获取上传文件 MultipartFile
        //返回上传路径
        String url = ossService.uploadFileAvatar(file);
        return ResultVo.ok().message("文件上传成功").data("url", url);
    }

    @Autowired
    public void setOssService(OssService ossService) {
        this.ossService = ossService;
    }
}

