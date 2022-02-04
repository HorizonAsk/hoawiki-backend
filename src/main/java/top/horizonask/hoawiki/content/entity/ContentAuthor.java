package top.horizonask.hoawiki.content.entity;

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
@TableName(value = "content_authors")
public class ContentAuthor extends Model<ContentAuthor> {
    @TableId(value = "content_authors_id", type = IdType.AUTO)
    private Long contentAuthorsId;

    @TableField(value = "content_id")
    private Long contentId;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "minor_revise")
    private Boolean minorRevise;
}
