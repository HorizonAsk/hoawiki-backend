package top.horizonask.hoawiki.content.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @description: tool class for data split to pages query parameter `currentPage`.
 * @time: 2022/2/3 1:37
 */
@Data
public class SearchPageParam {
    @NotBlank(message = "(・∀・(・∀・?)你不告诉我页码我怎么帮你找数据？")
    @Pattern(regexp = "^[0-9]*$", message = "Σ(っ °Д °;)っ你这是什么页码？")
    private String currentPage;

    @NotBlank(message = "(・∀・(・∀・?)你不告诉我关键词我怎么帮你找数据？")
    @Size(max = 15, message = "Σ(っ °Д °;)っ你这是什么页码？")
    private String searchKeyWord;

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public Long getCurrentPageLong(){
        return Long.parseLong(currentPage);
    }
}
