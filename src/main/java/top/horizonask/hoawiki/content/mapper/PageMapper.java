package top.horizonask.hoawiki.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.horizonask.hoawiki.content.entity.Page;

import java.util.List;

@Mapper
public interface PageMapper extends BaseMapper<Page> {

    /**
     * Using match against for fulltext match. Only use for more than one word.
     * <p>For single word searching like "中"， ”上“， ”下“, use searchPageBySingleWord().
     *
     * @param pageTitle pageTitle to match
     * @return java.util.List<top.horizonask.hoawiki.content.entity.Page>
     */
    @Select("SELECT * FROM pages where match(page_title) against(#{pageTitle})")
    List<Page> searchPageByName(@Param("pageTitle") String pageTitle);

    /**
     * Using `like` for single word searching. Use `searchPageByName` for other usages.
     *
     * @param pageWord single word, "中"， ”上“， ”下“
     * @return java.util.List<top.horizonask.hoawiki.content.entity.Page>
     */
    @Select("SELECT * FROM pages where page_title like %#{pageWord}%")
    List<Page> searchPageBySingleWord(@Param("pageTitle") String pageWord);
}
