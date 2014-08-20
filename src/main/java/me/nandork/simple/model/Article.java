package me.nandork.simple.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Article {

    @Id
    private String title;

    @Column(length = 2048)
    private String text;
    private String href;

    public Article() {
    }

    public Article(String title, String text, String href) {
        this.title = title;
        this.text = text;
        this.href = href;
    }

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
