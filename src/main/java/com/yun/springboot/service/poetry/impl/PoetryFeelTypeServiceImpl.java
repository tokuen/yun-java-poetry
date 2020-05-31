package com.yun.springboot.service.poetry.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.yun.springboot.model.entity.poetry.PoetryFeelTypeDo;
import com.yun.springboot.mapper.poetry.PoetryFeelTypeMapper;
import com.yun.springboot.service.poetry.IPoetryFeelTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yun
 * @since 2020-05-29
 */
@Service
public class PoetryFeelTypeServiceImpl
        extends ServiceImpl<PoetryFeelTypeMapper, PoetryFeelTypeDo>
        implements IPoetryFeelTypeService {

}
