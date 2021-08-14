package com.myproject.learneng.dao;

import org.apache.log4j.Logger;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VideoRepository {
    private static final Logger logger = Logger.getLogger(VideoRepository.class);
    private static final String KEY = "video";
    private HashOperations<String, String, byte[]> hashOps;

    public VideoRepository(RedisTemplate redisTemplate) {
        this.hashOps = redisTemplate.opsForHash();
    }

    public void addVideo(String description, byte[] bytes) {
        logger.info("add video "+description+"to redis database");
        hashOps.putIfAbsent(KEY, description, bytes);
    }

    public void updateVideo(String description, byte[] bytes) {
        logger.info("update video "+description);
        hashOps.put(KEY, description, bytes);
    }

    public byte[] getVideo(String description){
            logger.info("fetch video "+description+" from redis database");
        return hashOps.get(KEY, description);
    }

    public long deleteVideo(String description) {
            logger.info("delete video "+description);
        return hashOps.delete(KEY, description);
    }
}
