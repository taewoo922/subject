package com.cod.market.article.service;

import com.cod.market.article.entity.Article;
import com.cod.market.article.repository.ArticleRepository;
import com.cod.market.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    public List<Article> getList() {
        return articleRepository.findAll();
    }

    public Article create(String title, String content, Member author) {
        Article a = new Article();
        a.setTitle(title);
        a.setContent(content);
        a.setCreateDate(LocalDateTime.now());
        a.setAuthor(author);
        articleRepository.save(a);

        return a;
    }

    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findById(id);
    }

    public Article getArticle(Long id) {
        Optional<Article> article = this.articleRepository.findById(id);
        if (article.isPresent()) {
            return article.get();
        } else {
            System.out.println("게시물을 찾을 수 없습니다.");
        }

        return getArticle(id);
    }

    public void modify(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);

        articleRepository.save(article);
    }
}
