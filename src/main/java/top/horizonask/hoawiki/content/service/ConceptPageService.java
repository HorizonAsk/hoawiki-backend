package top.horizonask.hoawiki.content.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.horizonask.hoawiki.common.ApiStatus;
import top.horizonask.hoawiki.content.entity.ConceptPage;
import top.horizonask.hoawiki.content.entity.Content;

/**
 * @description:
 * @time: 2022/1/24 21:27
 */

public interface ConceptPageService extends IService<ConceptPage> {

    /**
     * <b>List ConceptPage by update time</b>
     * <p>For fulltext match. Only use for more than one word.</p>
     * <p>For single word searching like "中"， ”上“， ”下“,
     * will automatically use {@link ConceptPageService#searchPageBySingleWord}.</p>
     *
     * @param currentPage page-number
     * @return com.baomidou.mybatisplus.core.metadata.IPage<top.horizonask.hoawiki.content.entity.ConceptPage>
     */
    IPage<ConceptPage> listPageByUpdated(Long currentPage);

    /**
     * <b>Search ConceptPage</b>
     * <p>For fulltext match. Only use for more than one word.</p>
     * <p>For single word searching like "中"， ”上“， ”下“,
     * will automatically use {@link ConceptPageService#searchPageBySingleWord}.</p>
     *
     * @param pageTitle   pageTitle to match
     * @param currentPage page-number
     * @return com.baomidou.mybatisplus.core.metadata.IPage<top.horizonask.hoawiki.content.entity.ConceptPage>
     */
    IPage<ConceptPage> searchPageByName(String pageTitle, Long currentPage);

    /**
     * <b>Search ConceptPage</b>
     * <p>For single word searching. Use `{@link ConceptPageService#searchPageByName}` for other usages.</p>
     *
     * @param pageWord    single word, "中"， ”上“， ”下“
     * @param currentPage page-number
     * @return com.baomidou.mybatisplus.core.metadata.IPage<top.horizonask.hoawiki.content.entity.ConceptPage>
     */
    IPage<ConceptPage> searchPageBySingleWord(String pageWord, Long currentPage);

    /**
     * <b>Search ConceptPage</b>
     * <p>For fulltext searching. Use `{@link ConceptPageService#searchPageByName}` for normal usages.</p>
     *
     * @param pageWord    pageTitle to match
     * @param currentPage page-number
     * @return com.baomidou.mybatisplus.core.metadata.IPage<top.horizonask.hoawiki.content.entity.ConceptPage>
     */
    IPage<ConceptPage> searchPageIncludingSoftDeleted(String pageWord, Long currentPage);

    /**
     * <b>Count concept page by page title</b>
     * <p>Searching page title name and count.</p>
     * @param pageWord pageTitle to match
     * @return java.lang.Long
     */
    Long countPageIncludingSoftDeleted(String pageWord);

    /**
     * <b>Get page</b>
     * <p>Get page using {@code pageId}.</p>
     * <p><b>If soft-deleted or not-exist, return null.</b></p>
     *
     * @param pageId page id to fetch
     * @return top.horizonask.hoawiki.content.entity.ConceptPage
     */
    ConceptPage getById(Long pageId);

    /**
     * <b>Get page</b>
     * <p>Get page including soft deleted ones.</p>
     *
     * @param pageId page id to fetch
     * @return top.horizonask.hoawiki.content.entity.ConceptPage
     */
    ConceptPage getByIdIncludingSoftDeleted(Long pageId);


    /**
     * <b>Soft-Delete ConceptPage</b>
     * <p>Delete page using soft-delete. Depend on settings of mybatis.</p>
     *
     * @param pageId page id to fetch
     * @return boolean
     */
    boolean deleteById(Long pageId);

    /**
     * <b>Recover ConceptPage</b>
     * <p>Recover page from soft-delete.</p>
     *
     * @param pageId page id to fetch
     * @return boolean
     */
    boolean recoverById(Long pageId);

    /**
     * <b>Create ConceptPage</b>
     * <p>New a conceptPage and insert it in database.</p>
     *
     * @param conceptPage conceptPage entity to insert
     * @param force       force inserting for passing name existence check
     * @return top.horizonask.hoawiki.common.ApiStatus
     */
    ApiStatus createPage(ConceptPage conceptPage, boolean force);
}
