package cn.GnaixEuy.edu.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： gxschool_parent </p>
 * 创建日期： 2023/1/11
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "EduCourse对象", description = "课程")
public class EduCourse implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "课程ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;
    @ApiModelProperty(value = "二级分类ID")
    private String subjectId;
    @ApiModelProperty(value = "一级分类级ID")
    private String subjectParentId;
    @ApiModelProperty(value = "课程标题")
    private String title;
    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;
    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;
    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;
    @ApiModelProperty(value = "销售数量")
    private Long buyCount;
    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;
    @ApiModelProperty(value = "乐观锁")
    private Long version;
    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;
    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    @TableField(select = false)
    private Integer isDeleted;
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
