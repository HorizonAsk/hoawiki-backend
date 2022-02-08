package top.horizonask.hoawiki.content.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.horizonask.hoawiki.authorization.entity.User;
import top.horizonask.hoawiki.authorization.mapper.UserMapper;
import top.horizonask.hoawiki.common.ApiStatus;
import top.horizonask.hoawiki.content.entity.Content;
import top.horizonask.hoawiki.content.entity.PageContent;
import top.horizonask.hoawiki.content.mapper.ConceptPageMapper;
import top.horizonask.hoawiki.content.mapper.ContentAuthorMapper;
import top.horizonask.hoawiki.content.mapper.ContentMapper;
import top.horizonask.hoawiki.content.mapper.PageContentMapper;
import top.horizonask.hoawiki.content.service.ContentService;

import java.util.List;

@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    private final ContentAuthorMapper contentAuthorMapper;

    private final ContentMapper contentMapper;

    private final UserMapper userMapper;

    private final PageContentMapper pageContentMapper;

    private final ConceptPageMapper conceptPageMapper;

    public ContentServiceImpl(ContentAuthorMapper contentAuthorMapper, ContentMapper contentMapper, UserMapper userMapper, PageContentMapper pageContentMapper, ConceptPageMapper conceptPageMapper) {
        this.contentAuthorMapper = contentAuthorMapper;
        this.contentMapper = contentMapper;
        this.userMapper = userMapper;
        this.pageContentMapper = pageContentMapper;
        this.conceptPageMapper = conceptPageMapper;
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
     * <b>Contents</b>
     * <p>Get the <b>first part</b> of latest page content by page id.</p>
     *
     * @param pageId page id to get latest content.
     * @return top.horizonask.hoawiki.content.entity.Content
     */
    @Override
    public Content getPageLatestContentBriefById(Long pageId) {
        return contentMapper.getLatestContentBrief(pageId);
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
        return userMapper.selectBatchIds(contentAuthorMapper.getAuthorsIdsOfContent(contentId));
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

    /**
     * <b>Create Content for page</b>
     * <p>Get all content id list of page id</p>
     *
     * @param newContent new content of the page
     * @param pageId id of page to create content
     * @return top.horizonask.hoawiki.common.ApiStatus
     */
    @Override
    @Transactional
    public ApiStatus createContentOfPage(Content newContent, Long pageId) {

        int affectedCountContent = contentMapper.insert(newContent);
        PageContent newPageContent = new PageContent();
        newPageContent.setContentId(newContent.getContentId());
        newPageContent.setPageId(pageId);
        conceptPageMapper.updateTimeById(pageId);
        int affectedCountPageContent = pageContentMapper.insert(newPageContent);
        if(affectedCountContent==1&&affectedCountPageContent==1){
            return ApiStatus.API_RESPONSE_CONTENT_CREATED;
        }
        else{
            return ApiStatus.API_RESPONSE_DATABASE_ERROR;
        }
    }
}
