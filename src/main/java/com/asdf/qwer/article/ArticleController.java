package com.asdf.qwer.article;

import com.asdf.qwer.user.SiteUser;
import com.asdf.qwer.user.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;
    private final UserService userService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<Article> paging = this.articleService.getPage(page, kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "article_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") Integer id, Model model,
                         HttpServletRequest request, HttpServletResponse response) {
        Article article = this.articleService.get(id);
        if (article == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "게시글이 존재하지 않습니다.");
        }
        String cookieName = "viewedPost_" + id;
        boolean alreadyViewed = false;
        // 요청에 있는 쿠키 확인
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(cookieName)) {
                    alreadyViewed = true;
                    break;
                }
            }
        }
        // 조회한 적이 없다면 조회수 증가 및 쿠키 설정
        if (!alreadyViewed) {
            this.articleService.viewing(article);
            // 쿠키에 조회 기록 추가 (1시간 유지)
            Cookie newCookie = new Cookie(cookieName, "true");
            newCookie.setMaxAge(60 * 60); // 1시간
            response.addCookie(newCookie);
        }
        model.addAttribute("article", article);
        return "article_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/votes/{id}")
    public String votes(@PathVariable(value = "id") Integer id, Principal principal) {
        Article article = this.articleService.get(id);
        SiteUser user = this.userService.get(principal.getName());
        this.articleService.vote(article, user);
        return "redirect:/article/detail/" + id;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String create(ArticleForm articleForm) {
        return "article_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String create(@Valid ArticleForm articleForm, BindingResult bindingResult,
                         Principal principal) {
        if (bindingResult.hasErrors()) {
            return "article_form";
        }
        SiteUser user = this.userService.get(principal.getName());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "생성 권한이 없습니다");
        }
        this.articleService.create(articleForm.getTitle(), articleForm.getContent(), user);
        return "redirect:/article/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String modify(ArticleForm articleForm, @PathVariable(value = "id") Integer id,
                         Principal principal) {
        Article article = this.articleService.get(id);
        if (article == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "게시글이 없습니다.");
        }
        if (!article.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다");
        }
        articleForm.setTitle(article.getTitle());
        articleForm.setContent(article.getContent());
        return "article_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String modify(@Valid ArticleForm articleForm, BindingResult bindingResult,
                         Principal principal, @PathVariable(value = "id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "article_form";
        }
        SiteUser user = this.userService.get(principal.getName());
        Article article = this.articleService.get(id);
        if (article == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "게시글이 없습니다.");
        }
        if (user == null || !article.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다");
        }
        this.articleService.modify(articleForm.getTitle(), articleForm.getContent(), article);
        return "redirect:/article/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String delete(Principal principal, @PathVariable(value = "id") Integer id) {
        SiteUser user = this.userService.get(principal.getName());
        Article article = this.articleService.get(id);
        if (article == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "게시글이 없습니다.");
        }
        if (user == null || !article.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다");
        }
        this.articleService.delete(article);
        return "redirect:/article/list";
    }
}
