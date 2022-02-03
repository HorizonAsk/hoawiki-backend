package top.horizonask.hoawiki.content.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.horizonask.hoawiki.authorization.entity.User;
import top.horizonask.hoawiki.authorization.mapper.UserMapper;
import top.horizonask.hoawiki.content.entity.Content;
import top.horizonask.hoawiki.content.mapper.ContentAuthorMapper;
import top.horizonask.hoawiki.content.mapper.ContentMapper;
import top.horizonask.hoawiki.content.service.ContentService;

import java.util.List;

@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    private final ContentAuthorMapper contentAuthorMapper;

    private final ContentMapper contentMapper;

    private final UserMapper userMapper;

    public ContentServiceImpl(ContentAuthorMapper contentAuthorMapper, ContentMapper contentMapper, UserMapper userMapper) {
        this.contentAuthorMapper = contentAuthorMapper;
        this.contentMapper = contentMapper;
        this.userMapper = userMapper;
    }

    /**
     * <b>Get Contents</b>
     * <p>Get latest page content by page id.</p>
     *
     * @param pageId page id to get latest content.
     * @return top.horizonask.hoawiki.content.entity.Content
     */
    @Override
    public Content getPageLatestContentById(Long pageId) {
        return contentMapper.getLatestContent(pageId);
    }

    /**
     * <b>Get content authors</b>
     * <p>Get all authors of one content</p>
     *
     * @param contentId content id to match
     * @return java.util.List<top.horizonask.hoawiki.content.entity.User>
     */
    @Override
    public List<User> getContentAuthorsByContentId(Long contentId) {
        return userMapper.selectBatchIds(contentAuthorMapper.getAuthorsOfContent(contentId));
    }

    /**
     * <b>Get all Content</b>
     * <p>Get all content id list of page id</p>
     *
     * @param pageId page id to match
     * @return java.util.List<top.horizonask.hoawiki.content.entity.Content>
     */
    @Override
    public List<Content> getAllContentByPageId(Long pageId) {
        return contentMapper.getAllContentsOfPage(pageId);
    }


}
