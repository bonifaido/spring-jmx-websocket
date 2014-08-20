package me.nandork.simple.controller;

import me.nandork.simple.model.Article;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/articles")
    public Article articles() {
        return new Article("Interesting", "but nothing happened", "http://index.hu");
    }
}
