package com.ntu.edu.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * redis 配置
 *
 *  Spring 封装了 {@link RedisTemplate} 和 {@link StringRedisTemplate} 来操作 redis，但是这两个都不太好用。
 *  所以我们配置了 RedisTemplate<String,Object> 的 Bean，详见 {@link #redisTemplate(RedisConnectionFactory)}
 *
 *  {@link EnableCaching} 开启 spring 缓存，spring 内置了很多缓存器
 *
 *  当用 redis 作为缓存的时候，需要移出 spring-devTools 等相关插件，不然会引起类型转错误，比如 X can not cast to X。
 */
@Configuration
@EnableCaching
public class RedisConfig {

    /**
     * 自定义 springSessionDefaultRedisSerializer 对象，将会替代默认的 SESSION 序列化对象。
     * 默认是 JdkSerializationRedisSerializer，缺点是需要类实现 Serializable 接口。
     * 并且在反序列化时如果异常会抛出 SerializationException 异常，
     * 而 SessionRepositoryFilter 又没有处理异常，故如果序列化异常时就会导致请求异常
     */
    @Bean(name = "springSessionDefaultRedisSerializer")
    public GenericJackson2JsonRedisSerializer getGenericJackson2JsonRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

    /**
     * JacksonJsonRedisSerializer 和 GenericJackson2JsonRedisSerializer的区别：
     *
     * - GenericJackson2JsonRedisSerializer 在 json 中加入 @class 属性，类的全路径包名，方便反系列化。
     * - JacksonJsonRedisSerializer 如果存放了 List 则在反系列化的时候，如果没指定 TypeReference 则会报错
     *   java.util.LinkedHashMap cannot be cast。
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {

        // 返回对象使用Jackson2JsonRedisSerialize 替换默认序列化
        ObjectMapper objectMapper = new ObjectMapper();
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 配置使用 redis 缓存
     *
     * @param redisConnectionFactory /
     * @return /
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration
                = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(1));
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration).build();
    }

}
