package me.nandork.simple.repository;

import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, String> {
}
