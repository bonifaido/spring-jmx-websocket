package me.nandork.simple.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "article")
public class Article {

    @Id
    private String title;
    private String text;
    private String href;

    public Article() {
    }

    public Article(String title, String text, String href) {
        this.title = title;
        this.text = text;
        this.href = href;
    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
