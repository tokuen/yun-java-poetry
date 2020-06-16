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
    private String content_abstract;

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
    private String type_name;

    /**
     * 创建人
     */
    private String open_id;

    /**
     * 出自
     */
    private String content_from;

    /**
     * 原图
     */
    private String original_photo;

    /**
     * 标准图
     */
    private String standard_photo;

    /**
     * 长图
     */
    private String long_photo;

    /**
     * 宽图
     */
    private String wide_photo;

    /**
     * 省略图
     */
    private String ellipsis_photo;



}
