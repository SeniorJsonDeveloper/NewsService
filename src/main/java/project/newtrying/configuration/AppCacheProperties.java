package project.newtrying.configuration;

import lombok.Data;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "cache")
public class AppCacheProperties {
    private final List<String> cacheNames = new ArrayList<>();
    private final Map<String,CacheProperties> cacheMap = new HashMap<>();
    private CacheType cacheType;
    @Data
    public static class CacheProperties{
       private Duration expire = Duration.ZERO;
    }
    public interface CacheNames{
        String USER_NAMES = "userCache";
        String NEWS_CACHE = "newsCache";
    }
    public enum CacheType{
        REDIS;
    }

}
