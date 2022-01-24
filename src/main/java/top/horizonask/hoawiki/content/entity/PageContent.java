package top.horizonask.hoawiki.content.entity;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "page_contents")
public class PageContent extends Model<PageContent> {
    @TableId(value = "page_content_id", type = IdType.AUTO)
    private Long pageContentId;

    @TableField(value = "page_id")
    private Long pageId;

    @TableField(value = "content_id")
    private Long contentId;

    public JSONObject getJson() {
        JSONObject data = JSONUtil.createObj();
        data.set("pageId", this.getPageId());
        data.set("contentId", this.getContentId());
        return data;
    }
}
