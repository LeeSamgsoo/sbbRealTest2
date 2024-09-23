package com.asdf.qwer.user;

import com.asdf.qwer.article.Article;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;

    private String password;

    private String nickname;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Article> articles;
}
