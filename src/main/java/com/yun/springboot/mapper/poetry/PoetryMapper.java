package com.yun.springboot.mapper.poetry;

import com.yun.springboot.model.dto.Id;
import com.yun.springboot.model.entity.poetry.PoetryDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yun
 * @since 2020-05-29
 */
@Mapper
public interface PoetryMapper{

    PoetryDo selectMaxPoetryId(String requestId);

    List<Id> selectRandomPoetry(String requestId);

    PoetryDo selectById(String requestId,@Param("id") Long id);

}
