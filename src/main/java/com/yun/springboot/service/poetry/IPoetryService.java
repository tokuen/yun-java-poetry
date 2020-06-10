package com.yun.springboot.service.poetry;

import com.yun.springboot.model.dto.Id;
import com.yun.springboot.model.entity.poetry.PoetryDo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yun
 * @since 2020-05-29
 */
public interface IPoetryService {

    PoetryDo getMaxPoetryId();

    List<Id> getRandomPoetry();

    PoetryDo getById(Long id);
}
