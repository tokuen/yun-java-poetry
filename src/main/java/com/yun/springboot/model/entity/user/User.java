package com.yun.springboot.model.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author yun
 * @since 2020-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 手机号，带国际区域
     */
    private String phone;

    /**
     * 头像
     */
    private String imgUri;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 唯一id
     */
    private String openId;

    /**
     * 0无，1男 ，2女
     */
    private Boolean sex;

    /**
     * 城市
     */
    private String city;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

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
