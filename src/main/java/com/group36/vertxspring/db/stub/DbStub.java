package com.group36.vertxspring.db.stub;

import com.group36.vertxspring.model.Article;
import com.group36.vertxspring.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@Component
public class DbStub implements CommandLineRunner {

    private final ArticleRepository articleRepository;

    @Autowired
    public DbStub(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Populating DB...");
        IntStream.range(0,10).forEach(i -> articleRepository.save(new Article(new Random().nextLong(), UUID.randomUUID().toString())));
        System.out.println("DB Populated...");
    }
}
