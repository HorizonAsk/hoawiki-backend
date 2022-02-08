package top.horizonask.hoawiki.content.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import top.horizonask.hoawiki.authorization.entity.User;
import top.horizonask.hoawiki.authorization.service.UserServiceImpl;
import top.horizonask.hoawiki.common.ApiStatus;
import top.horizonask.hoawiki.common.ResponseUtils;
import top.horizonask.hoawiki.common.ValidateUtils;
import top.horizonask.hoawiki.content.entity.Content;
import top.horizonask.hoawiki.content.mapper.ContentAuthorMapper;
import top.horizonask.hoawiki.content.request.NewContentRequest;
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
public class ContentController {
    final ConceptPageServiceImpl conceptPageServiceImpl;

    final ContentServiceImpl contentServiceImpl;

    final UserServiceImpl userServiceImpl;

    final ContentAuthorMapper contentAuthorMapper;

    public ContentController(ConceptPageServiceImpl conceptPageServiceImpl, ContentServiceImpl contentServiceImpl, UserServiceImpl userServiceImpl, ContentAuthorMapper contentAuthorMapper) {
        this.conceptPageServiceImpl = conceptPageServiceImpl;
        this.contentServiceImpl = contentServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.contentAuthorMapper = contentAuthorMapper;
    }

    @GetMapping("/{pageId}/contents/latest")
    public ResponseEntity<JSONObject> getPageLatestContent(@PathVariable String pageId) {
        if (ValidateUtils.wrongRequestPageId(pageId)) {
            return ResponseUtils.fail(ApiStatus.API_RESPONSE_PARAM_BAD).message("页面ID格式错误").toResponseEntity();
        }
        Content content = contentServiceImpl.getPageLatestContentById(Long.valueOf(pageId));
        if (content != null) {
            ResponseUtils res = ResponseUtils.success();
            res.data(content.getJson());
            for(Long userId:contentAuthorMapper.getAuthorsIdsOfContent(content.getContentId())){
                res.accumulate("authors",userServiceImpl.getUserBriefInfo(userId));
            }
            return res.toResponseEntity();
        } else {
            return ResponseUtils.fail(ApiStatus.API_RESPONSE_REQUEST_NOT_FOUND, "尚无可用内容").toResponseEntity();
        }
    }

    @PostMapping("/{pageId}/contents")
    public ResponseEntity<JSONObject> newPageContent(@PathVariable String pageId, @Valid @RequestBody NewContentRequest newContentRequest, BindingResult validResult) {
        if (validResult.hasErrors()) {
            ResponseUtils responseUtils = ResponseUtils.fail(ApiStatus.API_RESPONSE_PARAM_BAD);
            for (FieldError error : validResult.getFieldErrors()) {
                responseUtils.accumulate("error", JSONUtil.createObj().set(error.getField(), error.getDefaultMessage()));
            }
            return responseUtils.toResponseEntity();
        }
        if (ValidateUtils.wrongRequestPageId(pageId)) {
            return ResponseUtils.fail(ApiStatus.API_RESPONSE_PARAM_BAD).message("页面ID格式错误").toResponseEntity();
        }
        Content newContent = new Content();
        newContent.setContentText(newContentRequest.getContentText());
        return ResponseUtils.success(contentServiceImpl.createContentOfPage(newContent, Long.valueOf(pageId))).toResponseEntity();
    }

}
