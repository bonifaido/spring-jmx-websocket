package me.nandork.simple.controller;

import me.nandork.simple.model.Article;
import me.nandork.simple.repository.ArticleRepository;
import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @ModelAttribute("articles")
    public List<Article> getArticles() {
        return Lists.newArrayList(articleRepository.findAll());
    }

    @RequestMapping("/articles")
    public String articles() {
        return "articles";
    }
}
