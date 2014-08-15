package me.nandork.simple.service;

import me.nandork.simple.repository.Article;
import me.nandork.simple.repository.ArticleRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WebCrawler {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ArticleRepository articleRepository;

    @Scheduled(fixedDelay = 3600000)
    public void crawlTheWeb() throws IOException {

        Document doc = Jsoup.connect("http://index.hu").get();

        Elements htmlArticles = doc.body().select("article");

        List<Article> articles = parseHtmlArticles(htmlArticles);

        articleRepository.save(articles);

        logger.info("Saved {} articles: {}", articles.size(), articles);
    }

    private List<Article> parseHtmlArticles(Elements htmlArticles) {
        return htmlArticles.stream().map(article -> {
            String title = article.select("a").text();
            String text = article.select("span").text();
            return new Article(title, text);
        }).collect(Collectors.toList());
    }
}
