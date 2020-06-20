package com.yun.springboot.controller.poetry;


import com.yun.springboot.model.dto.Id;
import com.yun.springboot.model.entity.poetry.PoetryDo;
import com.yun.springboot.model.result.RetResponseUtil;
import com.yun.springboot.model.result.RetResult;
import com.yun.springboot.model.vo.PoetryVo;
import com.yun.springboot.service.poetry.IPoetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        PoetryDo poetryDo = poetryService.getMaxPoetryId();
        PoetryVo poetryVo = new PoetryVo(poetryDo);
        return RetResponseUtil.makeOKRsp(poetryVo);
    }

    @RequestMapping("/getRandomPoetry")
    public RetResult getRandomPoetry() {
        List<Id> ids = poetryService.getRandomPoetry();
        int randomInt =(int) (Math.random() * ids.size());
        PoetryDo poetryDo = poetryService.getById(ids.get(randomInt).getId());
        PoetryVo poetryVo = new PoetryVo(poetryDo);
        return RetResponseUtil.makeOKRsp(poetryVo);
    }

    @RequestMapping("/getIndexPoetry")
    public RetResult getIndexPoetry(@RequestParam(value = "id") Long id) {
        PoetryDo poetryDo = poetryService.getById(id);
        PoetryVo poetryVo = new PoetryVo(poetryDo);
        return RetResponseUtil.makeOKRsp(poetryVo);
    }
}
