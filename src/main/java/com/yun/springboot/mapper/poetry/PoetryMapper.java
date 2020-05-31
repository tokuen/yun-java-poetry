package com.yun.springboot.mapper.poetry;

import com.yun.springboot.model.entity.poetry.PoetryDo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yun.springboot.model.vo.PoetryVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yun
 * @since 2020-05-29
 */
public interface PoetryMapper extends BaseMapper<PoetryDo> {

    PoetryDo selectMaxPoetryId();

}
