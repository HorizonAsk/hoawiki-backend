package top.horizonask.hoawiki.content.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @description: tool class for data split to pages query parameter `currentPage`.
 * @time: 2022/2/3 1:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchPageParam extends PaginationParam {

    @NotBlank(message = "(・∀・(・∀・?)你不告诉我关键词我怎么帮你找数据？")
    @Size(max = 15, message = "Σ(っ °Д °;)っ你这是什么页码？")
    private String searchKeyWord;
}

