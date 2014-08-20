package me.nandork.simple.service;

import me.nandork.simple.model.Article;
import me.nandork.simple.parser.ArticleParser;
import me.nandork.simple.repository.ArticleRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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

//@Component
//@ManagedResource
public class WebCrawler {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${crawler.url}")
    String url;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleParser articleParser;

    @ManagedOperation
    @Scheduled(fixedDelay = 3600000)
    public void crawlSite() throws IOException {

        Document doc = Jsoup.connect(url).get();

        Element body = doc.body();

        List<Article> articles = articleParser.apply(body);

        articleRepository.save(articles);

        logger.info("Saved {} articles", articles.size());
    }
}
