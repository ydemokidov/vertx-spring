package com.group36.vertxspring;

import com.group36.vertxspring.vertx.ArticleRecipientVerticle;
import com.group36.vertxspring.vertx.SenderVerticle;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Configuration
@EnableJpaRepositories("com.group36.vertxspring.repository")
@EntityScan("com.group36.vertxspring.model")
public class VertxSpringApplication {

    @Autowired
    SenderVerticle senderVerticle;

    @Autowired
    ArticleRecipientVerticle articleRecipientVerticle;

    public static void main(String[] args) {
        SpringApplication.run(VertxSpringApplication.class, args);
    }

    @PostConstruct
    public void deployVerticle(){
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(senderVerticle);
        vertx.deployVerticle(articleRecipientVerticle);
    }

}
