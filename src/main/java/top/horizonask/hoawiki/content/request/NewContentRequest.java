package top.horizonask.hoawiki.content.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2022/1/2 18:08
 */
@Data
public class NewContentRequest {
    @NotBlank(message = "不能创建空白的内容")
    private String contentText;
}
