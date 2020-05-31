package com.yun.springboot.service.poetry.impl;

import com.yun.springboot.model.entity.poetry.PoetryTypeDo;
import com.yun.springboot.mapper.poetry.PoetryTypeMapper;
import com.yun.springboot.service.poetry.IPoetryTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yun
 * @since 2020-05-29
 */
@Service
public class PoetryTypeServiceImpl extends ServiceImpl<PoetryTypeMapper, PoetryTypeDo> implements IPoetryTypeService {

}
