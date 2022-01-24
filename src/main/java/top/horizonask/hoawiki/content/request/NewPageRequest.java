package top.horizonask.hoawiki.content.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @description:
 * @time: 2022/1/25 2:20
 */

@Data
public class NewPageRequest {
    @NotBlank(message = "页面名不能为空")
    @Size(max = 200, message = "页面名过长")
    private String pageTitle;
}
