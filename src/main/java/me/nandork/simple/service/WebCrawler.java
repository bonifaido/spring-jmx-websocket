package me.nandork.simple.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WebCrawler {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Scheduled(fixedDelay = 3600000)
    public void crawlTheWeb() throws IOException {

        Document doc = Jsoup.connect("http://index.hu").get();

        logger.info("Crawled:\n{}", doc.body().select("span").text());
    }
}
