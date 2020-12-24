package jingzhou.Config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchRestClientConfig {

    @Bean
    public RestHighLevelClient client(){
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("106.14.12.11", 9200, "http")));
    }
}

