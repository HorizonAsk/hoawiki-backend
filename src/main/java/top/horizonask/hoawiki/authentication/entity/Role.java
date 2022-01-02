package top.horizonask.hoawiki.authentication.entity;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "roles")
public class Role extends Model<Role> {

    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    @TableField(value = "role_name")
    private String roleName;
    
    @TableField(value = "role_description")
    private String roleDescription;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    public JSONObject getJson() {
        JSONObject data = JSONUtil.createObj();
        data.set("roleId", this.getRoleId())
                .set("roleName", this.getRoleName())
                .set("roleDescription", this.getRoleDescription());
        return data;
    }
}
