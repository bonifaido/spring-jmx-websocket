package me.nandork.simple.parser;

import me.nandork.simple.model.Article;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.function.Function;

public interface ArticleParser extends Function<Element, List<Article>> {
}
