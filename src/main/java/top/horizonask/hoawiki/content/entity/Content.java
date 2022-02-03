package top.horizonask.hoawiki.content.entity;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @description: ConceptPage entity stores page item data.
 * @author: Yanbo Han
 * @time: 2022/1/23 23:26
 */

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "contents")
public class Content extends Model<Content> {
    @TableId(value = "content_id", type = IdType.AUTO)
    private Long contentId;

    @TableField(value = "content_text")
    private String contentText;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "delete_time")
    private LocalDateTime deleteTime;

    public JSONObject getJson() {
        JSONObject data = JSONUtil.createObj();
        data.set("contentText", this.getContentText());
        data.set("createTime", this.getCreateTime());
        return data;
    }
}
