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
@TableName(value = "concept_pages")
public class ConceptPage extends Model<ConceptPage> {
    @TableId(value = "page_id", type = IdType.AUTO)
    private Long pageId;

    @TableField(value = "page_title")
    private String pageTitle;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "delete_time", fill = FieldFill.INSERT)
    @TableLogic()
    private LocalDateTime deleteTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    public JSONObject getJson() {
        JSONObject data = JSONUtil.createObj();
        boolean available = this.available();
        data.set("pageId", this.getPageId());
        if (available) {
            data.set("pageTitle", this.getPageTitle())
                    .set("updateTime", this.getUpdateTime());
        }
        return data;
    }

    public JSONObject getJsonofDeleted() {
        JSONObject data = JSONUtil.createObj();
        Boolean available = this.available();
        data.set("available", available)
                .set("pageId", this.getPageId())
                .set("pageTitle", this.getPageTitle())
                .set("deleteTime", this.getUpdateTime());
        return data;
    }

    public boolean available() {
        if (this.getDeleteTime() == null) {
            return true;
        } else {
            return this.getDeleteTime().isAfter(LocalDateTime.now());
        }
    }
}
