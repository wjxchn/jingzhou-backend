package jingzhou.Service;

import jingzhou.POJO.Paper;
import jingzhou.POJO.Result;
import jingzhou.repository.PaperRepository;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class PaperService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    PaperRepository paperRepository;

    public Paper getById(String id) {
        return paperRepository.findByPaperid(id);
    }

    public Paper getByTitle(String title) {
        return paperRepository.findByTitle(title);
    }

    public List<Paper> getByFuzzyTitle(String title, int pagenum) {
        // 第一个参数是页数page，第二个参数是每页数据数量pageSize
        Pageable pageable = PageRequest.of(pagenum, 20);
        Page<Paper> paperPage = paperRepository.findAllByTitleLike(title, pageable);
        return paperPage.getContent();

    }

    public List<Paper> getByKeyword(String keyword, int pagenum) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
                new HttpHost("localhost", 8443, "http")));
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.termQuery("keywords", keyword));
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC))
                .withPageable(PageRequest.of(pagenum, 20))
                .build();
            SearchRequest searchRequest = new SearchRequest("jingzhou.paper");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder).from(pagenum).size(20);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        // 第一个参数是页数page，第二个参数是每页数据数量pageSize
        Pageable pageable = PageRequest.of(pagenum, 20);

        Page<Paper> paperPage = paperRepository.findAllByKeywordsLike(keyword, pageable);

        return paperPage.getContent();
    }

    public List<Paper> getByAuthor(String auhtorname, int pagenum){
        // 第一个参数是页数page，第二个参数是每页数据数量pageSize
        Pageable pageable = PageRequest.of(pagenum, 20);
        Page<Paper> paperPage = paperRepository.findAllByAuthorsLike(auhtorname, pageable);
        return paperPage.getContent();
    }

    public SearchResponse getByFuzzyKeyword(String keyword, int pagenum) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("106.14.12.11", 9200, "http")));
        SearchRequest searchRequest = new SearchRequest("jingzhou.paper");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(pagenum*20);
        searchSourceBuilder.size(20);
        searchSourceBuilder.timeout(new TimeValue(30, TimeUnit.SECONDS));
        searchRequest.source(searchSourceBuilder);
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("keywords", keyword).fuzziness(Fuzziness.AUTO).maxExpansions(3);
        searchSourceBuilder.query(matchQueryBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        return searchResponse;
    }

    public SearchResponse getByAuthorname(String name, int pagenum) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("106.14.12.11", 9200, "http")));
        SearchRequest searchRequest = new SearchRequest("jingzhou.paper");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(pagenum*20);
        searchSourceBuilder.size(20);
        searchSourceBuilder.timeout(new TimeValue(30, TimeUnit.SECONDS));
        searchRequest.source(searchSourceBuilder);
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("authors.name", name).fuzziness(Fuzziness.AUTO).maxExpansions(3);
        searchSourceBuilder.query(matchQueryBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        return searchResponse;
    }

    public SearchResponse getByAuthornameExact(String name, int pagenum) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("106.14.12.11", 9200, "http")));
        SearchRequest searchRequest = new SearchRequest("jingzhou.paper");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(pagenum*20);
        searchSourceBuilder.size(20);
        searchSourceBuilder.timeout(new TimeValue(30, TimeUnit.SECONDS));
        searchRequest.source(searchSourceBuilder);
        TermQueryBuilder termQueryBuilder = new TermQueryBuilder("authors.name", name).caseInsensitive(false);
        searchSourceBuilder.query(termQueryBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        return searchResponse;
    }
    public SearchResponse getByTitleExact(String title, int pagenum) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("106.14.12.11", 9200, "http")));
        SearchRequest searchRequest = new SearchRequest("jingzhou.paper");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(pagenum*20);
        searchSourceBuilder.size(20);
        searchSourceBuilder.timeout(new TimeValue(30, TimeUnit.SECONDS));
        searchRequest.source(searchSourceBuilder);
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = new MatchPhraseQueryBuilder("title", title);
        searchSourceBuilder.query(matchPhraseQueryBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        return searchResponse;
    }

    public SearchResponse getTitleFuzzy(String title, int pagenum) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("106.14.12.11", 9200, "http")));
        SearchRequest searchRequest = new SearchRequest("jingzhou.paper");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(pagenum*20);
        searchSourceBuilder.size(20);
        searchSourceBuilder.timeout(new TimeValue(30, TimeUnit.SECONDS));
        searchRequest.source(searchSourceBuilder);
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("title", title).fuzziness(Fuzziness.AUTO).maxExpansions(5);
        searchSourceBuilder.query(matchQueryBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        return searchResponse;
    }

    public SearchResponse getIssn(String issn, int pagenum) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("106.14.12.11", 9200, "http")));
        SearchRequest searchRequest = new SearchRequest("jingzhou.paper");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(pagenum*20);
        searchSourceBuilder.size(20);
        searchSourceBuilder.timeout(new TimeValue(30, TimeUnit.SECONDS));
        searchRequest.source(searchSourceBuilder);
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = new MatchPhraseQueryBuilder("issn", issn);
        searchSourceBuilder.query(matchPhraseQueryBuilder);
        return client.search(searchRequest,RequestOptions.DEFAULT);
    }
}
