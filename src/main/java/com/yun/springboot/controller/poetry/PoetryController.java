package com.yun.springboot.controller.poetry;


import com.yun.springboot.model.dto.Id;
import com.yun.springboot.model.entity.poetry.PoetryDo;
import com.yun.springboot.model.result.RetResponseUtil;
import com.yun.springboot.model.result.RetResult;
import com.yun.springboot.model.vo.PoetryVo;
import com.yun.springboot.service.poetry.IPoetryService;
import com.yun.springboot.util.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public RetResult getMaxPoetry(HttpServletRequest req, HttpServletResponse resp) {
        String requestId =(String) req.getAttribute("requestId");
        PoetryDo poetryDo = poetryService.getMaxPoetryId(requestId);
        PoetryVo poetryVo = new PoetryVo(poetryDo);
        return RetResponseUtil.makeOKRsp(poetryVo);
    }

    @RequestMapping("/getRandomPoetry")
    public RetResult getRandomPoetry(HttpServletRequest req) {
        String requestId =(String) req.getAttribute("requestId");
        requestId=LogUtils.getNewReqId(requestId);

        List<Id> ids = poetryService.getRandomPoetry(requestId);
        int randomInt =(int) (Math.random() * ids.size());

        requestId=LogUtils.getNewReqId(requestId);
        PoetryDo poetryDo = poetryService.getById(requestId,ids.get(randomInt).getId());

        PoetryVo poetryVo = new PoetryVo(poetryDo);
        return RetResponseUtil.makeOKRsp(poetryVo);
    }

    @RequestMapping("/getIndexPoetry")
    public RetResult getIndexPoetry(HttpServletRequest req,@RequestParam(value = "id") Long id) {
        String requestId =(String) req.getAttribute("requestId");
        PoetryDo poetryDo = poetryService.getById(LogUtils.getNewReqId(requestId),id);
        PoetryVo poetryVo = new PoetryVo(poetryDo);
        return RetResponseUtil.makeOKRsp(poetryVo);
    }
}
