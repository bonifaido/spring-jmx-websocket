package me.nandork.simple.service;

import me.nandork.simple.repository.Article;
import me.nandork.simple.repository.ArticleRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@ManagedResource
public class WebCrawler {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${crawler.url}")
    String url;

    @Autowired
    ArticleRepository articleRepository;

    // This can parse index.hu
    Function<Element, Article> mapper = article -> {
        String title = article.select("a").text();
        String text = article.select("span").text();
        return new Article(title, text);
    };

    @ManagedOperation
    @Scheduled(fixedDelay = 3600000)
    public void crawlSite() throws IOException {

        Document doc = Jsoup.connect(url).get();

        Elements htmlArticles = doc.body().select("article");

        List<Article> articles = parseHtmlArticles(htmlArticles);

        articleRepository.save(articles);

        logger.info("Saved {} articles", articles.size());
    }

    private List<Article> parseHtmlArticles(Elements htmlArticles) {
        return htmlArticles.stream().map(mapper).collect(Collectors.toList());
    }
}
