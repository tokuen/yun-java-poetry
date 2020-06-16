package com.yun.springboot.controller.poetry;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yun.springboot.exception.SignalingException;
import com.yun.springboot.model.cache.DataCache;
import com.yun.springboot.model.dto.Id;
import com.yun.springboot.model.dto.PoetryExt;
import com.yun.springboot.model.entity.poetry.PoetryDo;
import com.yun.springboot.model.result.ErrorCode;
import com.yun.springboot.model.result.RetResponseUtil;
import com.yun.springboot.model.result.RetResult;
import com.yun.springboot.model.vo.PoetryVo;
import com.yun.springboot.service.poetry.IPoetryService;
import com.yun.springboot.util.MyUtil;
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
        List<Id> ids = poetryService.getRandomPoetry();
        int randomInt =(int) (Math.random() * ids.size());
        PoetryDo poetryDo = poetryService.getById(ids.get(randomInt).getId());
        boolean showContentFlag = true;
        if(!MyUtil.isEmpty4Object(poetryDo.getExt())){
            String ext = poetryDo.getExt();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                PoetryExt poetryExt = objectMapper.readValue(ext, PoetryExt.class);
                if(!MyUtil.isEmpty4Object(poetryExt)){
                    showContentFlag = poetryExt.isShow_content_flag();
                }
            }catch (Exception e){
                System.out.println(e);
                throw new SignalingException(ErrorCode.ERR_CODE_PARSE_JSON_DATA_ERROR_10001002);
            }
        }

        PoetryVo poetryVo = new PoetryVo();
        if(showContentFlag){
            poetryVo.setContent(poetryDo.getContent());
        }else {
            poetryVo.setContent("");
        }
        poetryVo.setId(poetryDo.getId());
        poetryVo.setAuthor(poetryDo.getAuthor());
        poetryVo.setType(poetryDo.getType());
        poetryVo.setType_name(DataCache.TYPENAMECACHE.get(poetryVo.getType()));
        poetryVo.setOpen_id(poetryDo.getOpenId());
        poetryVo.setContent_from(poetryDo.getContentFrom());
        poetryVo.setContent_abstract(poetryDo.getContentAbstract());
        poetryVo.setLong_photo(poetryDo.getLongPhoto());
        poetryVo.setStandard_photo(poetryVo.getStandard_photo());
        poetryVo.setEllipsis_photo(poetryVo.getEllipsis_photo());
        poetryVo.setOriginal_photo(poetryVo.getOriginal_photo());
        poetryVo.setWide_photo(poetryVo.getWide_photo());
        return RetResponseUtil.makeOKRsp(poetryVo);
    }

    @RequestMapping("/getIndexPoetry")
    public RetResult getIndexPoetry(@RequestParam("id") Long id) {

        PoetryDo poetryDo = poetryService.getById(id);
        boolean showContentFlag = true;
        if(!MyUtil.isEmpty4Object(poetryDo.getExt())){
            String ext = poetryDo.getExt();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                PoetryExt poetryExt = objectMapper.readValue(ext, PoetryExt.class);
                if(!MyUtil.isEmpty4Object(poetryExt)){
                    showContentFlag = poetryExt.isShow_content_flag();
                }
            }catch (Exception e){
                System.out.println(e);
                throw new SignalingException(ErrorCode.ERR_CODE_PARSE_JSON_DATA_ERROR_10001002);
            }
        }

        PoetryVo poetryVo = new PoetryVo();
        if(showContentFlag){
            poetryVo.setContent(poetryDo.getContent());
        }else {
            poetryVo.setContent("");
        }
        poetryVo.setId(poetryDo.getId());
        poetryVo.setAuthor(poetryDo.getAuthor());
        poetryVo.setType(poetryDo.getType());
        poetryVo.setType_name(DataCache.TYPENAMECACHE.get(poetryVo.getType()));
        poetryVo.setOpen_id(poetryDo.getOpenId());
        poetryVo.setContent_from(poetryDo.getContentFrom());
        poetryVo.setContent_abstract(poetryDo.getContentAbstract());
        poetryVo.setLong_photo(poetryDo.getLongPhoto());
        poetryVo.setStandard_photo(poetryVo.getStandard_photo());
        poetryVo.setEllipsis_photo(poetryVo.getEllipsis_photo());
        poetryVo.setOriginal_photo(poetryVo.getOriginal_photo());
        poetryVo.setWide_photo(poetryVo.getWide_photo());
        return RetResponseUtil.makeOKRsp(poetryVo);
    }
}
