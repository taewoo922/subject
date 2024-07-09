package com.cod.market.article.controller;

import com.cod.market.article.entity.Article;
import com.cod.market.article.form.ArticleForm;
import com.cod.market.article.service.ArticleService;
import com.cod.market.member.entity.Member;
import com.cod.market.member.service.MemberService;
import com.cod.market.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;
    private final MemberService memberService;


    @GetMapping("/list")
    public String list(Model model) {
        List<Article> articles = articleService.getList();
        model.addAttribute("articles", articles);
        return "article/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Optional<Article> article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        return "article/detail";
    }

    @GetMapping("/write")
    public String write() {
        return "article/write";
    }
    @PostMapping("/write")
    public String write(@Valid ArticleForm articleForm, Principal principal) {
        Member member = memberService.getMember(principal.getName());
        articleService.create(articleForm.getTitle(), articleForm.getContent(), member);
        return "redirect:/article/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String modify(ArticleForm articleForm, @PathVariable("id") Long id,Principal principal) {
        Article article = articleService.getArticle(id);

        if (!article.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        articleForm.setTitle(article.getTitle());
        articleForm.setContent(article.getContent());

        return "article/articleForm";
    }

    @PostMapping("/modify/{id}")
    public String modify(@Valid ArticleForm articleForm, Principal principal, BindingResult bindingResult,@PathVariable("id") Long id) {

        if (bindingResult.hasErrors()) {
            return "article/articleForm";
        }
        Article article = articleService.getArticle(id);

        if (!article.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        articleService.modify(article, articleForm.getTitle(), articleForm.getContent());
        return "redirect:/article/detail/%s".formatted(id);
    }
}
