package top.horizonask.hoawiki.content.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.horizonask.hoawiki.common.ApiStatus;
import top.horizonask.hoawiki.content.entity.ConceptPage;
import top.horizonask.hoawiki.content.entity.Content;
import top.horizonask.hoawiki.content.mapper.ConceptPageMapper;
import top.horizonask.hoawiki.content.mapper.ContentMapper;
import top.horizonask.hoawiki.content.service.ConceptPageService;

/**
 * @description:
 * @time: 2022/1/24 22:40
 */
@Service
@Slf4j
public class ConceptPageServiceImpl extends ServiceImpl<ConceptPageMapper, ConceptPage> implements ConceptPageService {

    private final ConceptPageMapper conceptPageMapper;

    private final int pageSize = 10;

    public ConceptPageServiceImpl(ConceptPageMapper conceptPageMapper) {
        this.conceptPageMapper = conceptPageMapper;
    }

    /**
     * <b>List ConceptPage by update time</b>
     * <p>For fulltext match. Only use for more than one word.</p>
     * <p>For single word searching like "中"， ”上“， ”下“,
     * will automatically use {@link ConceptPageService#searchPageBySingleWord}.</p>
     *
     * @param currentPage page-number
     * @return com.baomidou.mybatisplus.core.metadata.IPage<top.horizonask.hoawiki.content.entity.ConceptPage>
     */
    @Override
    public IPage<ConceptPage> listPageByUpdated(Long currentPage) {
        Page<ConceptPage> page = new Page<>(currentPage, pageSize);
        return conceptPageMapper.getLatestPages(page);
    }

    /**
     * <b>Search ConceptPage</b>
     * <p>For fulltext match. Only use for more than one word.</p>
     * <p>For single word searching like "中"， ”上“， ”下“,
     * will automatically use {@link ConceptPageService#searchPageBySingleWord}.</p>
     *
     * @param pageTitle pageTitle to match
     * @return java.util.List<top.horizonask.hoawiki.content.entity.ConceptPage>
     */
    @Override
    public IPage<ConceptPage> searchPageByName(String pageTitle, Long currentPage) {
        Page<ConceptPage> page = new Page<>(currentPage, pageSize);
        int titleLength = pageTitle.length();
        if (titleLength > 2) {
            return conceptPageMapper.searchPageByName(page, pageTitle);
        } else {
            if (titleLength == 2) {
                String noEmojiTitle = EmojiParser.removeAllEmojis(pageTitle);
                if (titleLength != noEmojiTitle.length()) { // to search an emoji word.
                    return conceptPageMapper.searchPageBySingleWord(page, pageTitle);
                } else { // search two word.
                    return conceptPageMapper.searchPageByName(page, pageTitle);
                }
            } else {
                return conceptPageMapper.searchPageBySingleWord(page, pageTitle);
            }
        }
    }

    /**
     * <b>Search ConceptPage</b>
     * <p>For single word searching. Use `{@link ConceptPageService#searchPageByName}` for other usages.</p>
     *
     * @param pageWord single word, "中"， ”上“， ”下“
     * @return java.util.List<top.horizonask.hoawiki.content.entity.ConceptPage>
     */
    @Override
    public IPage<ConceptPage> searchPageBySingleWord(String pageWord, Long currentPage) {
        Page<ConceptPage> page = new Page<>(currentPage, pageSize);
        return conceptPageMapper.searchPageBySingleWord(page, pageWord);
    }

    /**
     * <b>Search ConceptPage (Admin)</b>
     * <p>For administration user searching. Use `{@link ConceptPageService#searchPageByName}` for normal usages.</p>
     *
     * @param pageTitle pageTitle to match
     * @return java.util.List<top.horizonask.hoawiki.content.entity.ConceptPage>
     */
    @Override
    public IPage<ConceptPage> searchPageIncludingSoftDeleted(String pageTitle, Long currentPage) {
        Page<ConceptPage> page = new Page<>(currentPage, pageSize);
        int titleLength = pageTitle.length();
        if (titleLength > 2) {
            return conceptPageMapper.searchPageByNameIdIncludeSoftDeleted(page, pageTitle);
        } else {
            if (titleLength == 2) {
                String noEmojiTitle = EmojiParser.removeAllEmojis(pageTitle);
                if (titleLength != noEmojiTitle.length()) { // to search an emoji word.
                    return conceptPageMapper.searchPageBySingleWordIdIncludeSoftDeleted(page, pageTitle);
                } else { // search two word.
                    return conceptPageMapper.searchPageByNameIdIncludeSoftDeleted(page, pageTitle);
                }
            } else {
                return conceptPageMapper.searchPageBySingleWordIdIncludeSoftDeleted(page, pageTitle);
            }
        }
    }

    /**
     * <b>Count concept page by page title</b>
     * <p><b>Exactly</b> match page title and count them.</p>
     * <p>Only named 'page word' will be count.</p>
     *
     * @param pageWord pageTitle to match
     * @return java.lang.Long
     */
    @Override
    public Long countPageIncludingSoftDeleted(String pageWord) {
        QueryWrapper<ConceptPage> query = new QueryWrapper<>();
        query.like("page_title",pageWord);
        return conceptPageMapper.selectCount(query);
    }

    /**
     * <b>Get page</b>
     * <p>Get page using {@code pageId}.</p>
     * <p><b>If soft-deleted or not-exist, return null.</b></p>
     *
     * @param pageId page id to fetch
     * @return top.horizonask.hoawiki.content.entity.ConceptPage
     */
    @Override
    public ConceptPage getById(Long pageId) {
        return conceptPageMapper.selectById(pageId);
    }

    /**
     * <b>Get page</b>
     * <p>Get page including soft deleted ones.</p>
     *
     * @param pageId page id to fetch
     * @return top.horizonask.hoawiki.content.entity.ConceptPage
     */
    @Override
    public ConceptPage getByIdIncludingSoftDeleted(Long pageId) {
        return conceptPageMapper.selectByIdIncludingSoftDeleted(pageId);
    }

    /**
     * <b>Soft-Delete ConceptPage</b>
     * <p>Delete page using soft-delete. Depend on settings of mybatis.</p>
     *
     * @param pageId page id to fetch
     * @return boolean
     */
    @Override
    public boolean deleteById(Long pageId) {
        if (getById(pageId) != null) {
            return super.removeById(pageId);
        } else {
            return false;
        }
    }

    /**
     * <b>Recover ConceptPage</b>
     * <p>Recover page from soft-delete.</p>
     *
     * @param pageId page id to fetch
     * @return boolean
     */
    @Override
    public boolean recoverById(Long pageId) {
        if (getById(pageId) != null) {
            return false;
        } else {
            if (getByIdIncludingSoftDeleted(pageId) != null) {
                return conceptPageMapper.recoverById(pageId);
            } else {
                return false;
            }
        }
    }

    /**
     * <b>Create ConceptPage</b>
     * <p>New a conceptPage and insert it in database.</p>
     *
     * @param conceptPage conceptPage entity to insert
     * @param force       force inserting for passing name existence check
     * @return top.horizonask.hoawiki.common.ApiStatus
     */
    @Override
    public ApiStatus createPage(ConceptPage conceptPage, boolean force) {
        if (force) {
            if (super.save(conceptPage)) {
                return ApiStatus.API_RESPONSE_CONTENT_CREATED;
            } else {
                return ApiStatus.API_RESPONSE_DATABASE_ERROR;
            }
        }else {
            if(countPageIncludingSoftDeleted(conceptPage.getPageTitle())!=0){
                return ApiStatus.API_RESPONSE_CONTENT_EXISTED;
            }
            else{
                super.save(conceptPage);
                return ApiStatus.API_RESPONSE_CONTENT_CREATED;
            }
        }
    }
}
