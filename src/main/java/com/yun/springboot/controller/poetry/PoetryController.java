package com.yun.springboot.controller.poetry;


import com.yun.springboot.model.cache.DataCache;
import com.yun.springboot.model.dto.Id;
import com.yun.springboot.model.entity.poetry.PoetryDo;
import com.yun.springboot.model.result.RetResponseUtil;
import com.yun.springboot.model.result.RetResult;
import com.yun.springboot.model.vo.PoetryVo;
import com.yun.springboot.service.poetry.IPoetryService;
import com.yun.springboot.util.CopyObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * <p>
 * 前端控制器
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

    @RequestMapping("/getMaxPoetry")
    public RetResult getMaxPoetry() {

        PoetryDo poetryDo = poetryService.selectMaxPoetryId();

        PoetryVo poetryVo = new PoetryVo();
        poetryVo.setAuthor(poetryDo.getAuthor());
        poetryVo.setType(poetryDo.getType());
        poetryVo.setType_name(DataCache.TYPENAMECACHE.get(poetryVo.getType()));
        poetryVo.setOpen_id(poetryDo.getOpenId());
        poetryVo.setContent_from(poetryDo.getContentFrom());
        poetryVo.setContent(poetryDo.getContent());
        poetryVo.setContent_abstract(poetryDo.getContentAbstract());
        poetryVo.setLong_photo(poetryDo.getLongPhoto());
        poetryVo.setStandard_photo(poetryVo.getStandard_photo());
        poetryVo.setEllipsis_photo(poetryVo.getEllipsis_photo());
        poetryVo.setOriginal_photo(poetryVo.getOriginal_photo());
        poetryVo.setWide_photo(poetryVo.getWide_photo());

        return RetResponseUtil.makeOKRsp(poetryVo);
    }

    @RequestMapping("/getRandomPoetry")
    public RetResult getRandomPoetry() {

        List<Id> ids = poetryService.selectRandomPoetry();
        Random r = new Random(1);
        int randomInt = r.nextInt(ids.size());

        PoetryDo poetryDo = poetryService.getById(Long.valueOf(randomInt));

        PoetryVo poetryVo = new PoetryVo();
        poetryVo.setAuthor(poetryDo.getAuthor());
        poetryVo.setType(poetryDo.getType());
        poetryVo.setType_name(DataCache.TYPENAMECACHE.get(poetryVo.getType()));
        poetryVo.setOpen_id(poetryDo.getOpenId());
        poetryVo.setContent_from(poetryDo.getContentFrom());
        poetryVo.setContent(poetryDo.getContent());
        poetryVo.setContent_abstract(poetryDo.getContentAbstract());
        poetryVo.setLong_photo(poetryDo.getLongPhoto());
        poetryVo.setStandard_photo(poetryVo.getStandard_photo());
        poetryVo.setEllipsis_photo(poetryVo.getEllipsis_photo());
        poetryVo.setOriginal_photo(poetryVo.getOriginal_photo());
        poetryVo.setWide_photo(poetryVo.getWide_photo());
        return RetResponseUtil.makeOKRsp(poetryVo);
    }
}
