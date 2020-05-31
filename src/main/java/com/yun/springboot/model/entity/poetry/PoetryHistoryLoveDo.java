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
public class PoetryHistoryLoveDo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 外键id
     */
    private Long poetryId;

    /**
     * 用户唯一id
     */
    private String openId;

    /**
     * 摘要
     */
    private String contentAbstract;

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
