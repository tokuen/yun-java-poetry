package com.yun.springboot.service.poetry.impl;

import com.yun.springboot.log.FlowLogUtil;
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
//        long startTimeMillis = System.currentTimeMillis();
        PoetryDo poetryDo = poetryMapper.selectMaxPoetryId(requestId);
//        long endTimeMillis = System.currentTimeMillis();
//        FlowLogUtil.printLog(requestId,startTimeMillis,endTimeMillis,poetryDo);
        return poetryDo;
    }

    public List<Id> getRandomPoetry(String requestId){
//        long startTimeMillis = System.currentTimeMillis();
        List<Id> ids = poetryMapper.selectRandomPoetry(requestId);
//        long endTimeMillis = System.currentTimeMillis();
//        FlowLogUtil.printLog(requestId,startTimeMillis,endTimeMillis,ids);
        return ids;
    }

    @Override
    public PoetryDo getById(String requestId,Long id) {
//        long startTimeMillis = System.currentTimeMillis();
        PoetryDo poetryDo = poetryMapper.selectById(requestId, id);
//        long endTimeMillis = System.currentTimeMillis();
//        FlowLogUtil.printLog(requestId,startTimeMillis,endTimeMillis,poetryDo);
        return poetryDo;
    }
}
