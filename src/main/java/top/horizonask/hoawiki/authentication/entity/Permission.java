package top.horizonask.hoawiki.authentication.entity;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "permissions")
public class Permission extends Model<Permission> {

    @TableId(value = "permission_id", type = IdType.AUTO)
    private Long permissionId;

    @TableField(value = "permission_name")
    private String permissionName;

    /**
     * permissionUrl:
     *  if permission_type is a page (value 1), url be the route;
     *  else permission_type will be a button (value 2), url be the button.
     */
    @TableField(value = "permission_url")
    private String permissionUrl;

    /**
     * permissionType:
     *  if value 1, a page;
     *  else a button (value 2).
     */
    @TableField(value = "permission_type")
    private PermissionType permissionType;

    /**
     * permissionClass:
     *  if a page, `page:admin`... ;
     *  else a button, `btn:delete`... .
     */
    @TableField(value = "permission_class")
    private String permissionClass;

    /**
     * permissionMethod: POST, GET, DELETE ...
     */
    @TableField(value = "permission_method")
    private String permissionMethod;

    /**
     * permissionSort:
     *  Permission show order. For inner range permission.
     */
    @TableField(value = "permission_sort")
    private Long permissionSort;

//    /**
//     * permissionParentId:
//     *  Permission father. For extensive permission.
//     */
//    @TableField(value = "permission_parent_id")
//    private Long permissionParentId;


    
    /*
     * @author Yanbo Han
     * @description //TODO 
     * @createTime 2021/12/29 22:24
     * @param []
     * @return cn.hutool.json.JSONObject
     **/
    public JSONObject getJson() {
        JSONObject data = JSONUtil.createObj();
        data.set("PermissionId", this.getPermissionId())
                .set("PermissionName", this.getPermissionName())
                .set("PermissionUrl", this.getPermissionUrl())
                .set("PermissionType",this.getPermissionType())
                .set("PermissionClass",this.getPermissionClass())
                .set("PermissionMethod",this.getPermissionMethod())
                .set("PermissionSort",this.getPermissionSort());
//        if (this.permissionParentId!=null){
//            data.set("PermissionParentId",this.getPermissionParentId());
//        }
//        else{
//            data.set("RootPermission",true);
//        }
        return data;
    }
}
