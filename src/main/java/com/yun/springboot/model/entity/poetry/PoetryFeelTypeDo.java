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
public class PoetryFeelTypeDo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 句子感觉id
     */
    private Integer feelId;

    /**
     * 诗歌id
     */
    private Long poetryId;

    /**
     * 感觉的名称
     */
    private String feelName;

    /**
     * 扩展字段
     */
    private String ext;

    /**
     * 删除标记:0正常,1删除
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
