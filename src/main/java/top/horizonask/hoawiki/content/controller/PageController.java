package top.horizonask.hoawiki.content.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.horizonask.hoawiki.common.ApiStatus;
import top.horizonask.hoawiki.common.ResponseUtils;
import top.horizonask.hoawiki.content.entity.Page;
import top.horizonask.hoawiki.content.request.NewPageRequest;
import top.horizonask.hoawiki.content.service.Impl.PageServiceImpl;

import javax.validation.Valid;
import java.util.List;

/**
 * @description:
 * @time: 2022/1/25 1:56
 */
@Slf4j
@RestController
@RequestMapping("/page/")
public class PageController {
    public PageController(PageServiceImpl pageServiceImpl) {
        this.pageServiceImpl = pageServiceImpl;
    }

    PageServiceImpl pageServiceImpl;

    @GetMapping("/search/{pageTitle}")
    public ResponseEntity<JSONObject> searchPage(@PathVariable String pageTitle){
        ResponseUtils responseUtils = ResponseUtils.success();
        log.info(String.valueOf(pageTitle.length()));
        List<Page> pages = pageServiceImpl.searchPageByName(pageTitle);
        for(Page page:pages){
            JSONObject pageItem = page.getJson();
            responseUtils.accumulate("pageItems", pageItem);
        }
        return responseUtils.toResponseEntity();
    }

    @GetMapping("/{pageId}")
    public ResponseEntity<JSONObject> getPageById(@PathVariable Long pageId){
        ResponseUtils responseUtils = ResponseUtils.success();
        Page page = pageServiceImpl.getById(pageId);
        responseUtils.accumulate("pageItems", page.getJson());
        return responseUtils.toResponseEntity();
    }

    @PostMapping("/new")
    public ResponseEntity<JSONObject> createPage(@Valid @RequestBody NewPageRequest newPageRequest){
        ResponseUtils responseUtils = ResponseUtils.success();
        Page page = new Page();
        page.setPageTitle(newPageRequest.getPageTitle());
        pageServiceImpl.save(page);
        return responseUtils.toResponseEntity();
    }
}
