package top.horizonask.hoawiki.content.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @description: tool class for data split to pages query parameter `currentPage`.
 * @time: 2022/2/3 1:37
 */
@Data
public class PaginationParam {
    @NotBlank(message = "(・∀・(・∀・?)你不告诉我页码我怎么帮你找数据？")
    @Pattern(regexp = "^[0-9]*$", message = "Σ(っ °Д °;)っ你这是什么页码？")
    private String currentPage;

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public Long getCurrentPageLong() {
        return Long.parseLong(currentPage);
    }
}
