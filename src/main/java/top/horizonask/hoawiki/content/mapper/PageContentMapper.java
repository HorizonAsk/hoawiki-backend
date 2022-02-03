package top.horizonask.hoawiki.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.horizonask.hoawiki.content.entity.ConceptPage;
import top.horizonask.hoawiki.content.entity.PageContent;

import java.util.List;

@Mapper
public interface PageContentMapper extends BaseMapper<PageContent> {
    /**
     * Get page content by page_id.
     *
     * @param pageId Id of page to get.
     * @return java.util.List<top.horizonask.hoawiki.content.entity.ConceptPage>
     */
    @Select("SELECT * FROM page_contents where page_id=#{pageId}")
    List<ConceptPage> getPageContentIds(@Param("pageId") Long pageId);
}
