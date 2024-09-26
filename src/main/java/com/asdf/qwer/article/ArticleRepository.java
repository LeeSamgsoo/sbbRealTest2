package com.asdf.qwer.article;

import com.asdf.qwer.user.SiteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    @Query("select "
            + "distinct A "
            + "from Article A "
            + "left outer join SiteUser U on A.author=U "
            + "where "
            + "   A.title like %:keyword% "
            + "   or A.content like %:keyword% "
            + "   or U.username like %:keyword% ")
    Page<Article> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
