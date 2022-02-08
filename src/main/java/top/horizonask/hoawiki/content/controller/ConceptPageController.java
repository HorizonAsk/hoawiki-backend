package top.horizonask.hoawiki.content.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import top.horizonask.hoawiki.common.ApiStatus;
import top.horizonask.hoawiki.common.ResponseUtils;
import top.horizonask.hoawiki.common.ValidateUtils;
import top.horizonask.hoawiki.content.entity.ConceptPage;
import top.horizonask.hoawiki.content.entity.Content;
import top.horizonask.hoawiki.content.request.ConceptPageTitleParam;
import top.horizonask.hoawiki.content.request.PaginationParam;
import top.horizonask.hoawiki.content.request.SearchPageParam;
import top.horizonask.hoawiki.content.service.Impl.ConceptPageServiceImpl;
import top.horizonask.hoawiki.content.service.Impl.ContentServiceImpl;

import javax.validation.Valid;

/**
 * @description:
 * @time: 2022/1/25 1:56
 */
@Slf4j
@RestController
@RequestMapping("/pages")
public class ConceptPageController {
    final ConceptPageServiceImpl conceptPageServiceImpl;

    final ContentServiceImpl contentServiceImpl;

    public ConceptPageController(ConceptPageServiceImpl conceptPageServiceImpl, ContentServiceImpl contentServiceImpl) {
        this.conceptPageServiceImpl = conceptPageServiceImpl;
        this.contentServiceImpl = contentServiceImpl;
    }

    @GetMapping("")
    public ResponseEntity<JSONObject> latestPage(@Valid PaginationParam paginationParam,
                                                 BindingResult validResult) {
        if (validResult.hasErrors()) {
            ResponseUtils responseUtils = ResponseUtils.fail(ApiStatus.API_RESPONSE_PARAM_BAD);
            for (FieldError error : validResult.getFieldErrors()) {
                responseUtils.accumulate("error", JSONUtil.createObj().set(error.getField(), error.getDefaultMessage()));
            }
            return responseUtils.toResponseEntity();
        }
        IPage<ConceptPage> conceptPages = conceptPageServiceImpl.listPageByUpdated(paginationParam.getCurrentPageLong());
        ResponseUtils responseUtils = ResponseUtils.success()
//                .data("totalPages", conceptPages.getPages()) // page number
//                .data("totalItems", conceptPages.getTotal()) // item number
                ;
        for (ConceptPage conceptPage : conceptPages.getRecords()) {
            JSONObject pageItem = conceptPage.getJson();
            Content content = contentServiceImpl.getPageLatestContentBriefById(conceptPage.getPageId());
            if (content != null) {
                pageItem.set("content",content.getContentText());
            } else {
                pageItem.set("content",null);
            }
            responseUtils.accumulate("pageItems", pageItem); // page items
        }
        return responseUtils.toResponseEntity();
    }

    @GetMapping("/search")
    public ResponseEntity<JSONObject> searchPage(@Valid SearchPageParam searchPageParam,
                                                 BindingResult validResult) {
        if (validResult.hasErrors()) {
            ResponseUtils responseUtils = ResponseUtils.fail(ApiStatus.API_RESPONSE_PARAM_BAD);
            for (FieldError error : validResult.getFieldErrors()) {
                responseUtils.accumulate("error", JSONUtil.createObj().set(error.getField(), error.getDefaultMessage()));
            }
            return responseUtils.toResponseEntity();
        }
        IPage<ConceptPage> conceptPages = conceptPageServiceImpl.searchPageByName(searchPageParam.getSearchKeyWord(), searchPageParam.getCurrentPageLong());
        ResponseUtils responseUtils = ResponseUtils.success()
//                .data("totalPages", conceptPages.getPages()) // page number
//                .data("totalItems", conceptPages.getTotal()) // item number
                ;
        for (ConceptPage conceptPage : conceptPages.getRecords()) {
            JSONObject pageItem = conceptPage.getJson();
            responseUtils.accumulate("pageItems", pageItem); // page items
        }
        return responseUtils.toResponseEntity();
    }

    @GetMapping("/{pageId}")
    public ResponseEntity<JSONObject> getPageById(@PathVariable String pageId) {
        if(ValidateUtils.wrongRequestPageId(pageId)){
            return ResponseUtils.fail(ApiStatus.API_RESPONSE_PARAM_BAD)
                    .message("页面ID格式错误")
                    .toResponseEntity();
        }
        ConceptPage conceptPage = conceptPageServiceImpl.getById(pageId);
        if (conceptPage != null) {
            return ResponseUtils.success()
                    .accumulate("pageItems", conceptPage.getJson())
                    .toResponseEntity();
        } else {
            return ResponseUtils.fail(ApiStatus.API_RESPONSE_REQUEST_NOT_FOUND)
                    .message("没有该页面或该页面已被删除！")
                    .toResponseEntity();
        }
    }

    @PutMapping("/{pageId}/recover")
    public ResponseEntity<JSONObject> recoverPageById(@PathVariable String pageId) {
        if(ValidateUtils.wrongRequestPageId(pageId)){
            return ResponseUtils.fail(ApiStatus.API_RESPONSE_PARAM_BAD)
                    .message("页面ID格式错误")
                    .toResponseEntity();
        }
        if (conceptPageServiceImpl.recoverById(Long.valueOf(pageId))) {
            return ResponseUtils.success().toResponseEntity();
        } else {
            return ResponseUtils.fail(ApiStatus.API_RESPONSE_REQUEST_NOT_FOUND)
                    .message("页面未能恢复，请检查后重试。")
                    .toResponseEntity();
        }
    }

    @DeleteMapping("/{pageId}")
    public ResponseEntity<JSONObject> deletePageById(@PathVariable String pageId) {
        if(ValidateUtils.wrongRequestPageId(pageId)){
            return ResponseUtils.fail(ApiStatus.API_RESPONSE_PARAM_BAD)
                    .message("页面ID格式错误")
                    .toResponseEntity();
        }
        if (!conceptPageServiceImpl.deleteById(Long.valueOf(pageId))) {
            return ResponseUtils.fail(ApiStatus.API_RESPONSE_REQUEST_NOT_FOUND)
                    .message("没有该页面或该页面已被删除！")
                    .toResponseEntity();
        }
        return ResponseUtils.success().toResponseEntity();
    }

    @PostMapping("/")
    @PreAuthorize(value = "hasRole('user')")
    public ResponseEntity<JSONObject> createPage(@Valid @RequestBody ConceptPageTitleParam conceptPageTitleParam, boolean force, BindingResult validResult) {
        if (validResult.hasErrors()) {
            ResponseUtils responseUtils = ResponseUtils.fail(ApiStatus.API_RESPONSE_PARAM_BAD);
            for (FieldError error : validResult.getFieldErrors()) {
                responseUtils.accumulate("error", JSONUtil.createObj().set(error.getField(), error.getDefaultMessage()));
            }
            return responseUtils.toResponseEntity();
        }
        ConceptPage conceptPage = new ConceptPage();
        conceptPage.setPageTitle(conceptPageTitleParam.getPageTitle());
        ApiStatus createPageStatus = conceptPageServiceImpl.createPage(conceptPage, force);
        if (createPageStatus == ApiStatus.API_RESPONSE_CONTENT_CREATED) {
            return ResponseUtils.success()
                    .data("pageId", conceptPage.getPageId())
                    .toResponseEntity();
        } else {
            return ResponseUtils.fail(createPageStatus)
                    .toResponseEntity();
        }
    }
}
