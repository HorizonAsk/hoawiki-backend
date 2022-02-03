package top.horizonask.hoawiki.content.controller;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.horizonask.hoawiki.common.ApiStatus;
import top.horizonask.hoawiki.common.ResponseUtils;
import top.horizonask.hoawiki.common.ValidateUtils;
import top.horizonask.hoawiki.content.service.Impl.ConceptPageServiceImpl;
import top.horizonask.hoawiki.content.service.Impl.ContentServiceImpl;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @time: 2022/1/25 1:56
 */
@Slf4j
@RestController
@RequestMapping("/page/")
public class ContentController {
    final ConceptPageServiceImpl conceptPageServiceImpl;

    final ContentServiceImpl contentServiceImpl;

    public ContentController(ConceptPageServiceImpl conceptPageServiceImpl, ContentServiceImpl contentServiceImpl) {
        this.conceptPageServiceImpl = conceptPageServiceImpl;
        this.contentServiceImpl = contentServiceImpl;
    }

    @GetMapping("/id/{pageId}/content")
    public ResponseEntity<JSONObject> getPageLatestContent(@PathVariable String pageId) {
        if(ValidateUtils.wrongRequestPageId(pageId)){
            return ResponseUtils.fail(ApiStatus.API_RESPONSE_PARAM_BAD)
                    .message("页面ID格式错误")
                    .toResponseEntity();
        }
        //TODO: content service
        return ResponseUtils.success()
                .data(contentServiceImpl.getPageLatestContentById(Long.valueOf(pageId)).getJson())
                .toResponseEntity();
    }

    @PutMapping("/id/{pageId}/content")
    public ResponseEntity<JSONObject> putPageContent(@PathVariable String pageId) {
        if(ValidateUtils.wrongRequestPageId(pageId)){
            return ResponseUtils.fail(ApiStatus.API_RESPONSE_PARAM_BAD)
                    .message("页面ID格式错误")
                    .toResponseEntity();
        }
        //TODO: content service
        return ResponseUtils.success()
                .data(contentServiceImpl.getPageLatestContentById(Long.valueOf(pageId)).getJson())
                .toResponseEntity();
    }

}
