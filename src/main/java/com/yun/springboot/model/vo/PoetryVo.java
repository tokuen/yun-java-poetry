package com.yun.springboot.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author yun
 * @since 2020-05-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PoetryVo implements Serializable {

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
     * 作者
     */
    private String author;

    /**
     * 类型
     */
    private int type;

    /**
     * 类型名称
     */
    private String typeName;

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



}
