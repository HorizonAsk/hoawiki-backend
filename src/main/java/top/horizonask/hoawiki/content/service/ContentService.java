package top.horizonask.hoawiki.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.horizonask.hoawiki.authorization.entity.User;
import top.horizonask.hoawiki.content.entity.Content;

import java.util.List;

/**
 * @description:
 * @time: 2022/1/24 21:27
 */

public interface ContentService extends IService<Content> {
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
}
