package jingzhou.Service;

import jingzhou.POJO.Author;
import jingzhou.repository.AuthorRepository;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

   @Autowired
   RestHighLevelClient client;

    public Author getByName(String name) {
//        Query query = new Query(Criteria.where("name").is(name));
//        return mongoTemplate.findOne(query,Author.class);
        return authorRepository.findByName(name);
    }


    public Author getByRealId(String id) {
//        Query query = new Query(Criteria.where("id").is(id));
//
//        return mongoTemplate.findOne(query,Author.class);


        /*这个地方可能会有问题*/
        return authorRepository.findByAuthorid(id);
    }

    public SearchResponse getByFuzzyName(String name, int pagenum) throws IOException {

//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("106.14.12.11", 9200, "http")));
        SearchRequest searchRequest = new SearchRequest("jingzhou.author");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(pagenum*20);
        searchSourceBuilder.size(20);
        searchSourceBuilder.timeout(new TimeValue(30, TimeUnit.SECONDS));
        searchRequest.source(searchSourceBuilder);
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("name", name).fuzziness(Fuzziness.AUTO).maxExpansions(10);
        searchSourceBuilder.query(matchQueryBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        return searchResponse;
    }


}
