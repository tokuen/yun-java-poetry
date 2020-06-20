package com.yun.springboot.model.vo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yun.springboot.exception.SignalingException;
import com.yun.springboot.model.cache.DataCache;
import com.yun.springboot.model.dto.PoetryExt;
import com.yun.springboot.model.entity.poetry.PoetryDo;
import com.yun.springboot.model.result.ErrorCode;
import com.yun.springboot.util.MyUtil;
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

    public PoetryVo(PoetryDo poetryDo) {
        this.id = poetryDo.getId();

        boolean showContentFlag = true;
        boolean showContentFromFlag = true;
        if (!MyUtil.isEmpty4Object(poetryDo.getExt())) {
            String ext = poetryDo.getExt();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                PoetryExt poetryExt = objectMapper.readValue(ext, PoetryExt.class);
                if (!MyUtil.isEmpty4Object(poetryExt)) {
                    showContentFlag = poetryExt.isShow_content_flag();
                    showContentFromFlag = poetryExt.isShow_content_from_flag();
                }
            } catch (Exception e) {
                throw new SignalingException(ErrorCode.ERR_CODE_PARSE_JSON_DATA_ERROR_10001002,e);
            }
        }
        if (showContentFlag) {
            this.content = poetryDo.getContent();
        } else {
            this.content = "";
        }
        if (showContentFromFlag) {
            this.content_from = poetryDo.getContentFrom();
        } else {
            this.content_from = "";
        }
        this.content_abstract = poetryDo.getContentAbstract();
        this.author = poetryDo.getAuthor();
        this.type = poetryDo.getType();
        this.type_name = DataCache.TYPE_NAME_CACHE.get(poetryDo.getType());
        this.open_id = poetryDo.getOpenId();
        this.original_photo = poetryDo.getOriginalPhoto();
        this.standard_photo = poetryDo.getStandardPhoto();
        this.long_photo = poetryDo.getLongPhoto();
        this.wide_photo = poetryDo.getWidePhoto();
        this.ellipsis_photo = poetryDo.getWidePhoto();
    }

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
