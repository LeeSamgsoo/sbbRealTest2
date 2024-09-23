package com.asdf.qwer.article;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleForm {
    @NotEmpty(message = "제목이 비어있습니다.")
    private String title;

    @NotEmpty(message = "내용이 비어있습니다.")
    private String content;
}
