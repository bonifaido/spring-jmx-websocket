package me.nandork.simple.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "article")
public class Article {

//    private String id;
    @Id
    private String title;
    private String text;

    public Article() {
    }

    public Article(String title, String text) {
        this.title = title;
        this.text = text;
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

    @Override
    public String toString() {
        return "Article{" +
//                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
