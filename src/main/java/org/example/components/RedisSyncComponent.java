package org.example.components;

import com.google.gson.Gson;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.io.Serializable;


public class RedisSyncComponent {

    private RedisCommands redisSync;

    private String redisHost="redis://localhost:6379";

    public RedisSyncComponent(){
        RedisClient client = RedisClient.create(redisHost);
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisCommands<String, String> sync = connection.sync();
        this.redisSync= sync;

    }

    Gson gson = new Gson();
    public void addObjectSync(String key, Object value){
        redisSync.set(key, gson.toJson(value));
    }

    public <T extends Serializable> T getObjectSync(String key, Class<T> type){
        return gson.fromJson((String) redisSync.get(key), type);
    }

}