package me.nandork.simple.parser;

import me.nandork.simple.model.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ConditionalOnProperty("crawler.index.hu")
public class IndexHuArticleParser implements ArticleParser {

    @Override
    public List<Article> apply(Element body) {
        Elements articles = body.select("article");
        return articles.stream().map(article -> {
            String title = article.select(".cikkcim").text();
            String text = article.select("span").text();
            return new Article(title, text);
        }).collect(Collectors.toList());
    }

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://index.hu").get();

        Element body = doc.body();

        List<Article> apply = new IndexHuArticleParser().apply(body);

        for (Article article : apply) {
            System.out.println(article.getTitle());
        }
    }
}
