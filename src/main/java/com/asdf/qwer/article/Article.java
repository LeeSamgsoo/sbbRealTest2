package com.asdf.qwer.article;

import com.asdf.qwer.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String title;

    @Column(columnDefinition = "text")
    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    private Long views;

    @ManyToMany
    private Set<SiteUser> votes;

    @ManyToOne
    private SiteUser author;
}
