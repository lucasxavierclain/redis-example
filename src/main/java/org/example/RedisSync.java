package org.example;

import com.google.gson.Gson;
import org.example.components.RedisSyncComponent;
import org.example.entities.RedisObjectExample;

public class RedisSync {

    public static void main(String[] args) {
        Gson gson = new Gson();
        RedisSyncComponent redisSyncComponent = new RedisSyncComponent();

        RedisObjectExample redisObjectExample = new RedisObjectExample("text example - Redis SYNC", 100);
        String keyValue="example key to redis async";

        redisSyncComponent.addObjectSync(keyValue, redisObjectExample);

        RedisObjectExample objectSync = redisSyncComponent.getObjectSync(keyValue, RedisObjectExample.class);
        System.out.println(String.format("Fetching Redis Object: %s",gson.toJson(objectSync)));
        System.out.println("End");
    }
}
