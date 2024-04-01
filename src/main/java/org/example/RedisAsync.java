package org.example;

import com.google.gson.Gson;
import org.example.components.RedisAsyncComponent;
import org.example.entities.RedisObjectExample;

import java.util.concurrent.CompletableFuture;

public class RedisAsync {

    public static void main(String[] args) throws InterruptedException {
        Gson gson = new Gson();
        RedisAsyncComponent redisSyncComponent = new RedisAsyncComponent();
        String keyValue = "example key to redis async";
        RedisObjectExample redisObjectExample = new RedisObjectExample("text example - Redis ASYNC", 100);
        saveObjectWithDelay(redisObjectExample, redisSyncComponent, keyValue);

        RedisObjectExample objectAsync = redisSyncComponent.getObjectAsync(keyValue, RedisObjectExample.class);
        System.out.println(String.format("Fetching Redis Object: %s", gson.toJson(objectAsync)));
        Thread.sleep(3000);
        System.out.println("End");
    }

    public static CompletableFuture<Void> saveObjectWithDelay(RedisObjectExample redisObjectExample, RedisAsyncComponent redisSyncComponent, String keyValue) {
        return CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
                redisSyncComponent.addObjectAsync(keyValue, redisObjectExample);
            } catch (Exception e) {
                System.out.println("Error");
            } finally {
                System.out.println("Saving to redis");
            }
        });
    }
}
