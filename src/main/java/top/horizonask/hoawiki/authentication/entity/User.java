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

    @TableField(value = "username")
    private String username;

    @TableField(value = "email")
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
        Boolean available = this.available();
        data.set("available", available).set("userId", this.getUserId());
        if (available) {
            data.set("username", this.getUsername())
                    .set("email", this.getEmail());
        }
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
