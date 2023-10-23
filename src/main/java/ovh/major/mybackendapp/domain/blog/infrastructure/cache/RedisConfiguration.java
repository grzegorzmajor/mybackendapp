package ovh.major.mybackendapp.domain.blog.infrastructure.cache;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import ovh.major.mybackendapp.domain.blog.dto.PostResponseDTO;


@Configuration
@EnableCaching
@Log4j2
@ConditionalOnProperty(value = "spring.cache.type", havingValue = "redis")
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Page<PostResponseDTO>> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Page<PostResponseDTO>> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
