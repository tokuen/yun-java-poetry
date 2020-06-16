package com.yun.springboot.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PoetryExt {
    private boolean show_content_flag;
}
