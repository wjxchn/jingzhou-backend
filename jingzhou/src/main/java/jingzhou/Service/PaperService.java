package jingzhou.Service;

import jingzhou.POJO.Paper;
import jingzhou.repository.PaperRepository;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    @Autowired
    RestHighLevelClient client;

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

//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("106.14.12.11", 9200, "http")));
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

//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("106.14.12.11", 9200, "http")));
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

    public List<Paper> getByAuthornameExactEx(String name, int pagenum){

        System.out.println("开始查询");
        // 第一个参数是页数page，第二个参数是每页数据数量pageSize
        Pageable pageable = PageRequest.of(pagenum, 20);

        Query query = new Query(Criteria.where("authors.name").is(name)).with(pageable);

        return mongoTemplate.find(query, Paper.class);
    }

    public SearchResponse getByAuthornameExact(String name, int pagenum) throws IOException {

//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("106.14.12.11", 9200, "http")));
        SearchRequest searchRequest = new SearchRequest("jingzhou.paper");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(pagenum*20);
        searchSourceBuilder.size(20);
        searchSourceBuilder.timeout(new TimeValue(30, TimeUnit.SECONDS));
        searchRequest.source(searchSourceBuilder);
//        TermQueryBuilder termQueryBuilder = new TermQueryBuilder("authors.name", name).caseInsensitive(false);
        QueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery("authors.name", name);
        searchSourceBuilder.query(queryBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        return searchResponse;
    }
    public SearchResponse getByTitleExact(String title, int pagenum) throws IOException {

//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("106.14.12.11", 9200, "http")));
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

//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("106.14.12.11", 9200, "http")));
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

//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("106.14.12.11", 9200, "http")));
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

    public MultiSearchResponse getByAnId(String id) throws IOException {
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("106.14.12.11", 9200, "http")));
        MultiSearchRequest request = new MultiSearchRequest();
        SearchRequest firstSearchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termsQuery("id", id));
        firstSearchRequest.source(searchSourceBuilder);
        request.add(firstSearchRequest);
        SearchRequest secondSearchRequest = new SearchRequest();
        searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termsQuery("paperid", id));
        secondSearchRequest.source(searchSourceBuilder);
        request.add(secondSearchRequest);

        return client.msearch(request, RequestOptions.DEFAULT);
    }

    public SearchResponse getByCitation(int pagenum) throws IOException {
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("106.14.12.11", 9200, "http")));
        System.out.println("new client");
        SearchRequest searchRequest = new SearchRequest("jingzhou.paper");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        System.out.println("new searchbuilder");
        searchSourceBuilder.query(QueryBuilders.matchAllQuery()).from(pagenum*20).size(20).sort("n_citation", SortOrder.DESC);
        searchRequest.source(searchSourceBuilder);
        System.out.println("in getByCitation in paperservice");
        return client.search(searchRequest,RequestOptions.DEFAULT);
    }
}
