package com.group36.vertxspring.vertx;

import com.group36.vertxspring.service.ArticleService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleRecipientVerticle extends AbstractVerticle {

    public static final String GET_ALL_ARTICLES = "get.articles.all";
    private final ArticleService articleService;

    @Autowired
    public ArticleRecipientVerticle(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public void start() throws Exception {
        super.start();

        vertx.eventBus()
                .<String>consumer(GET_ALL_ARTICLES)
                .handler(getAllArticleService(articleService));
    }

    private Handler<Message<String>> getAllArticleService(ArticleService articleService) {
        return msg -> vertx.<String>executeBlocking(future ->{
            try {
                System.out.println("Recipient verticle processing request...");
                future.complete(Json.encode(articleService.findAll()));
            }catch (Exception e){
                future.fail(e);
                e.printStackTrace();
                System.out.println("Recipient verticle processing request ERROR...");
            }
        }, result ->{
            if(result.succeeded()){
                System.out.println("Recipient verticle processing request SUCCESS...");
                msg.reply(result.result());
            }else{
                System.out.println("Recipient verticle processing request ERROR RESULT ...");
                msg.reply(result.cause().toString());
            }
        });
    }
}
