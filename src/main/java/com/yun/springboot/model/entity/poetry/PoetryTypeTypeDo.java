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
public class PoetryTypeTypeDo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 诗歌id
     */
    private Long poetryId;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 扩展字段
     */
    private String ext;

    /**
     * 删除标记，0代表有效，1代表无效
     */
    private Boolean delFlag;

    /**
     * 保存时间
     */
    private LocalDateTime saveTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
