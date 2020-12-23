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
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
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

//        Query query = new Query(Criteria.where("id").is(id));
//        Paper paper = mongoTemplate.findOne(query, Paper.class);
//        return paper;

        return paperRepository.findByPaperid(id);

    }


    public Paper getByTitle(String title) {
//        Query query = new Query(Criteria.where("title").is(title));
//        Paper paper = mongoTemplate.findOne(query, Paper.class);
//        return paper;
        return paperRepository.findByTitle(title);
    }


//    public List<Paper> getByFuzzyTitle(String title, int pagenum) {
//
//        String titles="^.*"+title+".*$";
//        Criteria criteria = new Criteria();
//        criteria.and("title").regex(titles);
//
//        Query query = new Query();
//        query.addCriteria(Criteria.where("title").regex(titles));
//        //50为一页的数量
//        if (pagenum != 1){
//            int num = (pagenum-1)*20;
//            query.limit(num);
//            List<Paper> lastpapers = mongoTemplate.find(query, Paper.class);
//            Paper last = lastpapers.get(lastpapers.size()-1);
//            ObjectId _id = last.get_id();
//            System.out.println("--------_id: "+_id);
//            criteria.and("_id").gt(_id);
//
//        }
//
//        query.addCriteria(criteria).limit(20);
//
//        List<Paper> papers = mongoTemplate.find(query, Paper.class);
//        return papers;
//    }

    public List<Paper> getByFuzzyTitle(String title, int pagenum) {

        // 第一个参数是页数page，第二个参数是每页数据数量pageSize
        Pageable pageable = PageRequest.of(pagenum, 20);

        Page<Paper> paperPage = paperRepository.findAllByTitleLike(title, pageable);

        return paperPage.getContent();

    }



//    public List<Paper> getByKeyword(String keyword, int pagenum){
//
//        System.out.println("service : get by keyword");
//        Criteria criteria = new Criteria();
//        Query query = new Query();
//        query.addCriteria(Criteria.where("keywords").is(keyword)).limit(20);
//        //50为一页的数量
//      if (pagenum != 1){
//            int num = (pagenum-1)*20;
//            query.limit(num);
//            List<Paper> lastpapers = mongoTemplate.find(query, Paper.class);
//            Paper last = lastpapers.get(lastpapers.size()-1);
//            ObjectId _id = last.get_id();
//            System.out.println("--------_id: "+_id);
//            criteria.and("_id").gt(_id);
//
//        }
//
//        query.addCriteria(criteria).limit(20);
//        List<Paper> papers = mongoTemplate.find(query, Paper.class);
//
//        return papers;
//    }

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

//    public List<Paper> getByAuthor(String auhtorname, int pagenum){
//
//        System.out.println("service : get by keyword");
//        Criteria criteria = new Criteria();
//        Query query = new Query();
//        query.addCriteria(Criteria.where("authors.name").is(auhtorname)).limit(20);
//        //50为一页的数量
//        if (pagenum != 1){
//            int num = (pagenum-1)*20;
//            query.limit(num);
//            List<Paper> lastpapers = mongoTemplate.find(query, Paper.class);
//            Paper last = lastpapers.get(lastpapers.size()-1);
//            ObjectId _id = last.get_id();
//            System.out.println("--------_id: "+_id);
//            criteria.and("_id").gt(_id);
//
//        }
//
//        query.addCriteria(criteria).limit(20);
//        List<Paper> papers = mongoTemplate.find(query, Paper.class);
//
//        return papers;
//    }

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
}
