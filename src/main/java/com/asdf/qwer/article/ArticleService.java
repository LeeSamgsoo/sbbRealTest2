package com.asdf.qwer.article;

import com.asdf.qwer.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Page<Article> getPage(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.articleRepository.findAllByKeyword(kw, pageable);
    }

    public void create(String title, String content, SiteUser user) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setAuthor(user);
        article.setCreateDate(LocalDateTime.now());
        this.articleRepository.save(article);
    }

    public Article get(Integer id) {
        return this.articleRepository.findById(id).orElse(null);
    }

    public void modify(String title, String content, Article article) {
        article.setTitle(title);
        article.setContent(content);
        article.setModifyDate(LocalDateTime.now());
        this.articleRepository.save(article);
    }

    public void delete(Article article) {
        this.articleRepository.delete(article);
    }

    public void viewing(Article article) {
        if (article.getViews() == null) {
            article.setViews(1L);
        } else {
            article.setViews(article.getViews() + 1);
        }
        this.articleRepository.save(article);
    }

    public void vote(Article article, SiteUser user) {
        article.getVotes().add(user);
        this.articleRepository.save(article);
    }
}
