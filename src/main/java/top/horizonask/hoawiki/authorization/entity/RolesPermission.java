package top.horizonask.hoawiki.authorization.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "roles_permission")
public class RolesPermission extends Model<RolesPermission> {

    @TableField(value = "role_id")
    private Long roleId;

    @TableField(value = "permission_id")
    private Long permissionId;
}
