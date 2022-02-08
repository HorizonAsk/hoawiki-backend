package top.horizonask.hoawiki.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.horizonask.hoawiki.content.entity.ContentAuthor;

import java.util.List;

@Mapper
public interface ContentAuthorMapper extends BaseMapper<ContentAuthor> {
    /**
     * Get authors of content by id.
     *
     * @param contentId id of content
     * @return java.util.List<top.horizonask.hoawiki.content.entity.ContentAuthor>
     **/
    @Select("SELECT user_id FROM content_authors where content_id=#{contentId}")
    List<Long> getAuthorsIdsOfContent(@Param("contentId") Long contentId);
}
