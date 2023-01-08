package cn.GnaixEuy.eduservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 课程科目
 *
 * @TableName edu_subject
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "edu_subject")
@ApiModel(value = "EduSubject对象", description = "课程")
public class EduSubject implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 课程类别ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "课程类别ID")
    private String id;
    /**
     * 类别名称
     */
    @TableField(value = "title")
    @ApiModelProperty(value = "类别名称")
    private String title;
    /**
     * 父ID
     */
    @TableField(value = "parent_id")
    @ApiModelProperty(value = "父ID")
    private String parentId;
    /**
     * 排序字段
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "排序字段")
    private Object sort;
    /**
     * 创建时间
     */
    @TableField(value = "gmt_create")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;
    /**
     * 更新时间
     */
    @TableField(value = "gmt_modified")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime gmtModified;

}