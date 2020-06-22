package com.yun.springboot.service.poetry.impl;

import com.yun.springboot.model.dto.Id;
import com.yun.springboot.model.entity.poetry.PoetryDo;
import com.yun.springboot.mapper.poetry.PoetryMapper;
import com.yun.springboot.service.poetry.IPoetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yun
 * @since 2020-05-29
 */
@Service
public class PoetryServiceImpl implements IPoetryService {

    @Autowired
    private PoetryMapper poetryMapper;

    @Override
    public PoetryDo getMaxPoetryId(String requestId) {
//        poetryMapper
        return poetryMapper.selectMaxPoetryId(requestId);
    }

    public List<Id> getRandomPoetry(String requestId){
        return poetryMapper.selectRandomPoetry(requestId);
    }

    @Override
    public PoetryDo getById(String requestId,Long id) {
        return poetryMapper.selectById(requestId,id);
    }
}
