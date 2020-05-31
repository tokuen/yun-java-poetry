package com.yun.springboot.controller.poetry;


import com.yun.springboot.model.cache.DataCache;
import com.yun.springboot.model.entity.poetry.PoetryDo;
import com.yun.springboot.model.result.RetResponseUtil;
import com.yun.springboot.model.result.RetResult;
import com.yun.springboot.model.vo.PoetryVo;
import com.yun.springboot.service.poetry.IPoetryService;
import com.yun.springboot.util.CopyObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yun
 * @since 2020-05-29
 */
@RestController
@RequestMapping("/poetry/poetry")
public class PoetryController {
    @Autowired
    private IPoetryService poetryService;

    @RequestMapping("/getRandomPoetry")
    public RetResult getRandomPoetry(){

//        PoetryDo poetryDo = poetryService.getById(1L);

        PoetryDo poetryDo = poetryService.selectMaxPoetryId();
        PoetryVo poetryVo=null;
        CopyObjectUtil.copyObj(poetryDo.getClass(),poetryVo.getClass());
        poetryVo.setTypeName(DataCache.TYPENAMECACHE.get(poetryVo.getType()));
        return RetResponseUtil.makeOKRsp(poetryVo);
    }
}
