package org.example.components;

import com.google.gson.Gson;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.io.Serializable;

public class RedisAsyncComponent {

    private RedisAsyncCommands redisAsync;
    Gson gson = new Gson();

    private String redisHost="redis://localhost:6379";

    public RedisAsyncComponent(){
        RedisClient client = RedisClient.create(redisHost);
        StatefulRedisConnection<String, String> connection = client.connect();
        this.redisAsync = connection.async();
    }

    public void addObjectAsync(String key, Object value){
        redisAsync.set(key, gson.toJson(value));
    }

    public <T extends Serializable> T getObjectAsync(String key, Class<T> type){
        try {
            RedisFuture redisFuture = redisAsync.get(key);
            return gson.fromJson((String) redisFuture.get(), type);
        }catch (Exception e){
            return null;
        }

    }

}