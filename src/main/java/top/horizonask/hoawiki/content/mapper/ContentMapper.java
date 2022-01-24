package top.horizonask.hoawiki.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.horizonask.hoawiki.content.entity.Content;

import java.util.List;

@Mapper
public interface ContentMapper extends BaseMapper<Content> {
    /**
     * Get page content by content_id.
     *
     * @param contentId id of content to get
     * @return top.horizonask.hoawiki.content.entity.Content
     */
    @Select("SELECT * FROM contents where content_id=#{contentId}")
    Content getContent(@Param("contentId") Long contentId);

    /**
     * Get page content by content_id.
     *
     * @param pageId id of content to get
     * @return top.horizonask.hoawiki.content.entity.Content
     */
    @Select("SELECT * " +
            "FROM contents " +
            "where content_id " +
            "in " +
            "(" +
            "SELECT content_id " +
            "FROM page_contents " +
            "where page_id=#{pageId}" +
            ") " +
            "ORDER BY created_time desc,content_id desc " +
            "LIMIT 1")
    Content getLatestContent(@Param("pageId") Long pageId);

    /**
     * Get all content of a page
     *
     * @param pageId id of page
     * @return java.util.List<top.horizonask.hoawiki.content.entity.Content>
     */
    @Select("SELECT * " +
            "FROM contents " +
            "where content_id " +
            "in " +
            "(" +
            "SELECT content_id " +
            "FROM page_contents " +
            "where page_id=#{pageId}" +
            ") " +
            "ORDER BY created_time desc,content_id desc")
    List<Content> getAllContentsOfPage(@Param("pageId") Long pageId); // TODO: split select result to pages.
}
