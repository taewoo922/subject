package com.cod.market.article.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ArticleForm {
    @Size(min = 5, max = 25)
    @NotBlank
    private String title;
    @Size(min = 10)
    @NotBlank(message = "내용은 필수항목입니다.")
    private String content;
}
