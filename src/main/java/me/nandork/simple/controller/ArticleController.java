package me.nandork.simple.controller;

import me.nandork.simple.model.Article;
import me.nandork.simple.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

//@Controller
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @ModelAttribute("articles")
    public List<Article> getArticles() {
        ArrayList<Article> articles = new ArrayList<>();
        for (Article article : articleRepository.findAll()) {
            articles.add(article);
        }
        return articles;
    }

    @RequestMapping("/articles")
    public String articles() {
        return "articles";
    }
}
