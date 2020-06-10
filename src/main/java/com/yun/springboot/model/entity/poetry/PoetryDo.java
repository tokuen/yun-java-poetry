package com.yun.springboot.model.entity.poetry;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * @author yun
 * @since 2020-05-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PoetryDo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 内容
     */
    private String content;

    /**
     * 摘要
     */
    private String contentAbstract;

    /**
     * 状态:0初始化待审核,1审核中,2审核失败,3审核成功
     */
    private Integer state;

    /**
     * 作者
     */
    private String author;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 创建人
     */
    private String openId;

    /**
     * 出自
     */
    private String contentFrom;

    /**
     * 原图
     */
    private String originalPhoto;

    /**
     * 标准图
     */
    private String standardPhoto;

    /**
     * 长图
     */
    private String longPhoto;

    /**
     * 宽图
     */
    private String widePhoto;

    /**
     * 省略图
     */
    private String ellipsisPhoto;

    /**
     * 扩展字段
     */
    private String ext;

    /**
     * 删除标记，0代表有效，1代表无效
     */
    private Boolean delFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 保存时间
     */
    private LocalDateTime saveTime;


}
