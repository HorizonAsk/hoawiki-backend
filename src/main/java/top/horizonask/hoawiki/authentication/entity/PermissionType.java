package top.horizonask.hoawiki.authentication.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonRawValue;

public enum PermissionType{
    PAGE(1,"page"),
    BUTTON(2,"button");

    @EnumValue
    private final Integer Key;

    @JsonRawValue
    private final String displayName;

    PermissionType(Integer Key, String displayName){
        this.Key = Key;
        this.displayName = displayName;
    }

    public Integer getKey() {
        return Key;
    }

    public String getDisplayName() {
        return displayName;
    }
}
