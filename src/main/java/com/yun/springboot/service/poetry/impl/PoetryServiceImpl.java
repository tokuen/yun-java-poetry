package com.yun.springboot.service.poetry.impl;

import com.yun.springboot.model.dto.Id;
import com.yun.springboot.model.entity.poetry.PoetryDo;
import com.yun.springboot.mapper.poetry.PoetryMapper;
import com.yun.springboot.service.poetry.IPoetryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class PoetryServiceImpl extends ServiceImpl<PoetryMapper, PoetryDo> implements IPoetryService {

    @Autowired
    private PoetryMapper poetryMapper;

    @Override
    public PoetryDo selectMaxPoetryId() {
//        poetryMapper
        return poetryMapper.selectMaxPoetryId();
    }

    public List<Id> selectRandomPoetry(){
        return poetryMapper.selectRandomPoetry();
    }
}
