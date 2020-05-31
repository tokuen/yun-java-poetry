package com.yun.springboot.service.poetry;

import com.yun.springboot.model.entity.poetry.PoetryDo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yun.springboot.model.vo.PoetryVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yun
 * @since 2020-05-29
 */
public interface IPoetryService extends IService<PoetryDo> {

    PoetryDo selectMaxPoetryId();

}
