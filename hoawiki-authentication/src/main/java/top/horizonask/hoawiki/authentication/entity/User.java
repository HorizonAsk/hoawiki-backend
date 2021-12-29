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
@TableName(value = "users")
public class User extends Model<User> {

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @TableField(value = "is_super_admin")
    private Boolean isSuperAdmin;

    private String username;

    private String email;

    @TableField(value = "email_verified_at")
    private LocalDateTime emailVerifiedAt;

    private String password;

    @TableField(value = "remember_token")
    private String rememberToken;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "delete_time")
    private LocalDateTime deleteTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    public JSONObject getJson() {
        JSONObject data = JSONUtil.createObj();
        data.set("userId", this.getUserId())
                .set("username", this.getUsername())
                .set("email", this.getEmail());
        return data;
    }
}
