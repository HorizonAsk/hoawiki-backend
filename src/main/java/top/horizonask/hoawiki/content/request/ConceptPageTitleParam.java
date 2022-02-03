package top.horizonask.hoawiki.content.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @description:
 * @time: 2022/1/25 2:20
 */

@Data
public class ConceptPageTitleParam {
    @NotBlank(message = "页面名再短也不能啥都没有啊(・∀・(・∀・?)")
    @Size(max = 200, message = "名字太长啦Σ(っ °Д °;)っ")
    private String pageTitle;
}
