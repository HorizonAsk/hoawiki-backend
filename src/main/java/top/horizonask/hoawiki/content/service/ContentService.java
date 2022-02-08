package top.horizonask.hoawiki.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;
import top.horizonask.hoawiki.authorization.entity.User;
import top.horizonask.hoawiki.common.ApiStatus;
import top.horizonask.hoawiki.content.entity.Content;

import java.util.List;

/**
 * @description:
 * @time: 2022/1/24 21:27
 */

public interface ContentService extends IService<Content> {

    /**
     * <b>Contents</b>
     * <p>Get latest page content by page id.</p>
     *
     * @param pageId page id to get latest content.
     * @return top.horizonask.hoawiki.content.entity.Content
     */
    Content getPageLatestContentById(Long pageId);

    /**
     * <b>Contents</b>
     * <p>Get the <b>first part</b> of latest page content by page id.</p>
     *
     * @param pageId page id to get latest content.
     * @return top.horizonask.hoawiki.content.entity.Content
     */
    Content getPageLatestContentBriefById(Long pageId);

    /**
     * Get all authors of one content
     *
     * @param contentId content id to match
     * @return java.util.List<top.horizonask.hoawiki.content.entity.User>
     */
    List<User> getContentAuthorsByContentId(Long contentId);

    /**
     * Get all contents of page id
     *
     * @param pageId page id to match
     * @return java.util.List<top.horizonask.hoawiki.content.entity.Content>
     */
    List<Content> getAllContentByPageId(Long pageId);

    /**
     * <b>Create Content for page</b>
     * <p>Get all content id list of page id</p>
     *
     * @param newContent new content of the page
     * @param pageId id of page to create content
     * @return top.horizonask.hoawiki.common.ApiStatus
     */
    @Transactional
    ApiStatus createContentOfPage(Content newContent, Long pageId);
}
