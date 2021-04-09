package com.group36.vertxspring.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.springframework.stereotype.Component;

@Component
public class SenderVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        super.start();

        Router router = Router.router(vertx);

        router.get("/articles").handler(this::getVertxArticleHandler);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(config().getInteger("http.port",8080));
        System.out.println("SenderVerticle created HTTP server at port 8080");
    }

    private void getVertxArticleHandler(RoutingContext routingContext) {
        vertx.eventBus().<String>request(ArticleRecipientVerticle.GET_ALL_ARTICLES,
                "",result ->{
                    System.out.println("SenderVerticle is sending request...");

                    if(result.succeeded()){
                        routingContext.response()
                                .putHeader("content-type","application/json")
                                .setStatusCode(200)
                                .end(result.result().body());
                        System.out.println("SenderVerticle is logging request successfull");
                    }else{
                        routingContext.response()
                                .setStatusCode(500)
                                .end();
                        System.out.println("SenderVerticle is logging request failed");
                    }
                }
        );
    }
}
