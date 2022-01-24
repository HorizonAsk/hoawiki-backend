package top.horizonask.hoawiki.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.horizonask.hoawiki.content.entity.Content;
import top.horizonask.hoawiki.content.entity.Page;

import java.util.List;

/**
 * @description:
 * @time: 2022/1/24 21:27
 */

public interface PageService extends IService<Page> {
    /**
     * <p> For fulltext match. Only use for more than one word.
     * <p> For single word searching like "中"， ”上“， ”下“, use searchPageBySingleWord().
     *
     * @param pageTitle pageTitle to match
     * @return java.util.List<top.horizonask.hoawiki.content.entity.Page>
     */
    List<Page> searchPageByName(String pageTitle);

    /**
     * For single word searching. Use `searchPageByName` for other usages.
     *
     * @param pageWord single word, "中"， ”上“， ”下“
     * @return java.util.List<top.horizonask.hoawiki.content.entity.Page>
     */
    List<Page> searchPageBySingleWord(String pageWord);

    /**
     * Get latest page content by page id.
     *
     * @param pageId page id to get latest content.
     * @return top.horizonask.hoawiki.content.entity.Content
     */
    Content getPageLatestContentById(Long pageId);
}
