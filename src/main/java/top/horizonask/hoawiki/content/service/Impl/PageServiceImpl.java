package top.horizonask.hoawiki.content.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.horizonask.hoawiki.content.entity.Content;
import top.horizonask.hoawiki.content.entity.Page;
import top.horizonask.hoawiki.content.mapper.ContentMapper;
import top.horizonask.hoawiki.content.mapper.PageMapper;
import top.horizonask.hoawiki.content.service.PageService;

import java.util.List;

/**
 * @description:
 * @time: 2022/1/24 22:40
 */
@Service
public class PageServiceImpl extends ServiceImpl<PageMapper, Page> implements PageService {

    private final PageMapper pageMapper;

    private final ContentMapper contentMapper;

    public PageServiceImpl(PageMapper pageMapper, ContentMapper contentMapper) {
        this.pageMapper = pageMapper;
        this.contentMapper = contentMapper;
    }

    /**
     * <p> For fulltext match. Only use for more than one word.
     * <p> For single word searching like "中"， ”上“， ”下“, use searchPageBySingleWord().
     *
     * @param pageTitle pageTitle to match
     * @return java.util.List<top.horizonask.hoawiki.content.entity.Page>
     */
    @Override
    public List<Page> searchPageByName(String pageTitle) {
        return pageMapper.searchPageByName(pageTitle);
    }

    /**
     * For single word searching. Use `searchPageByName` for other usages.
     *
     * @param pageWord single word, "中"， ”上“， ”下“
     * @return java.util.List<top.horizonask.hoawiki.content.entity.Page>
     */
    @Override
    public List<Page> searchPageBySingleWord(String pageWord) {
        return pageMapper.searchPageBySingleWord(pageWord);
    }

    /**
     * Get latest page content by page id.
     *
     * @param pageId page id to get latest content.
     * @return top.horizonask.hoawiki.content.entity.Content
     */
    @Override
    public Content getPageLatestContentById(Long pageId) {
        return contentMapper.getLatestContent(pageId);
    }
}
