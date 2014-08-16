package me.nandork.simple.repository;

import me.nandork.simple.model.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, String> {
}
