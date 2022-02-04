package top.horizonask.hoawiki.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.horizonask.hoawiki.content.entity.ConceptPage;

@Mapper
public interface ConceptPageMapper extends BaseMapper<ConceptPage> {

    /**
     * Get latest updated pages.
     *
     * @param page pagination
     * @return com.baomidou.mybatisplus.core.metadata.IPage<top.horizonask.hoawiki.content.entity.ConceptPage>
     */
    @Select("SELECT page_id,page_title,update_time " +
            "FROM concept_pages " +
            "ORDER BY update_time desc")
    IPage<ConceptPage> getLatestPages(Page<ConceptPage> page);

    /**
     * Using match against for fulltext match. Only use for more than one word.
     * <p>For single word searching like "中"， ”上“， ”下“, use {@link ConceptPageMapper#searchPageBySingleWord}.
     *
     * @param pageTitle pageTitle to match
     * @return com.baomidou.mybatisplus.core.metadata.IPage<top.horizonask.hoawiki.content.entity.ConceptPage>
     */
    @Select("SELECT page_id,page_title,update_time " +
            "FROM concept_pages " +
            "WHERE match(page_title) against(#{pageTitle}) AND delete_time is null " +
            "ORDER BY update_time desc")
    IPage<ConceptPage> searchPageByName(Page<ConceptPage> page, @Param("pageTitle") String pageTitle);

    /**
     * Using `like` for single word searching. Use {@link ConceptPageMapper#searchPageByName} for other usages.
     *
     * @param pageWord single word, "中"， ”上“， ”下“
     * @return java.util.List<top.horizonask.hoawiki.content.entity.ConceptPage>
     */
    @Select("SELECT page_id,page_title,update_time " +
            "FROM concept_pages " +
            "WHERE page_title LIKE CONCAT('%',#{pageWord},'%') AND delete_time is null " +
            "ORDER BY update_time desc")
    IPage<ConceptPage> searchPageBySingleWord(Page<ConceptPage> page, @Param("pageWord") String pageWord);

    /**
     * <b>Administration method</b>
     * <p>Using match against for fulltext match. Only use for more than one word.</p>
     * <p>For single word searching like "中"， ”上“， ”下“, use {@link ConceptPageMapper#searchPageBySingleWord}.</p>
     *
     * @param pageTitle pageTitle to match
     * @return com.baomidou.mybatisplus.core.metadata.IPage<top.horizonask.hoawiki.content.entity.ConceptPage>
     */
    @Select("SELECT page_id,page_title,update_time " +
            "FROM concept_pages " +
            "WHERE match(page_title) against(#{pageTitle}) " +
            "ORDER BY update_time desc")
    IPage<ConceptPage> searchPageByNameIdIncludeSoftDeleted(Page<ConceptPage> page, @Param("pageTitle") String pageTitle);

    /**
     * <b>Administration method</b>
     * <p>Using `like` for single word searching. Use `searchPageByName` for other usages.</p>
     *
     * @param pageWord single word, "中"， ”上“， ”下“
     * @return java.util.List<top.horizonask.hoawiki.content.entity.ConceptPage>
     */
    @Select("SELECT page_id,page_title,update_time " +
            "FROM concept_pages " +
            "WHERE page_title LIKE CONCAT('%',#{pageWord},'%') " +
            "ORDER BY update_time desc")
    IPage<ConceptPage> searchPageBySingleWordIdIncludeSoftDeleted(Page<ConceptPage> page, @Param("pageWord") String pageWord);

    /**
     * <b>Search(Admin)</b>
     * <p>Search including soft deleted.</p>
     *
     * @param pageId pageId
     * @return java.util.List<top.horizonask.hoawiki.content.entity.ConceptPage>
     */
    @Select("SELECT page_id,page_title,delete_time " +
            "FROM concept_pages " +
            "WHERE page_id = #{pageId} " +
            "ORDER BY update_time desc")
    ConceptPage searchPageByIdIncludeSoftDeleted(@Param("pageId") Long pageId);

    /**
     * <b>Search(Admin)</b>
     * <p>Search including soft deleted.</p>
     *
     * @param pageId pageId to fetch
     * @return top.horizonask.hoawiki.content.entity.ConceptPage
     */
    @Select("SELECT page_id,page_title,delete_time " +
            "FROM concept_pages " +
            "WHERE page_id = #{pageId}")
    ConceptPage selectByIdIncludingSoftDeleted(@Param("pageId") Long pageId);

    /**
     * <b>Recover(Admin)</b>
     * <p>Recover a soft-deleted page by id</p>
     *
     * @param pageId pageId to fetch
     * @return boolean
     */
    @Update("UPDATE concept_pages " +
            "SET delete_time=null,update_time=now() " +
            "WHERE page_id=#{pageId}")
    boolean recoverById(@Param("pageId") Long pageId);

    /**
     * <b>Update time</b>
     * <p>update a page's time by id</p>
     *
     * @param pageId pageId to update
     * @return boolean
     */
    @Update("UPDATE concept_pages " +
            "SET update_time=now() " +
            "WHERE page_id=#{pageId}")
    int updateTimeById(@Param("pageId") Long pageId);
}
