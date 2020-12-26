package jingzhou.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingzhou.POJO.Author;
import jingzhou.POJO.Result;
import jingzhou.Service.AuthorService;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 *这个控制类用于处理:nosql数据中的[作者]数据
 *
 */
@Api(value = "检索系统-Author")
@RestController
@RequestMapping("/data/")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    /*精确查找*/
    @GetMapping("author/byname")
    @ApiOperation(value = "通过name精确查询author")
    private Result findByName(@RequestParam("name") String name) throws IOException {
        Result result = new Result("搜索成功", 200);
        SearchResponse searchResponse = authorService.getByName(name);
        if (searchResponse.status() != RestStatus.OK)
            return new Result("没有搜索结果", 400);
        SearchHits hits = searchResponse.getHits();
        TotalHits totalHits = hits.getTotalHits();
        result.getData().put("total",totalHits.value);
        List<Author> authorList= new ArrayList<Author>();
        SearchHit[] searchHits = hits.getHits();
        int i = 1;
        for (SearchHit hit:searchHits
        ) {
            Map<String,Object> map = hit.getSourceAsMap();
            ObjectMapper objectMapper = new ObjectMapper();
            Author author = objectMapper.convertValue(map,Author.class);
            authorList.add(author);
        }
        result.getData().put("author",authorList.get(0));
        if (authorList == null || authorList.size()==0)return new Result("没有搜索结果", 400);

        return result;
    }

    /*精确查找
    * @Param id:数据自带的id项
    * */
    @GetMapping("author/byrealid")
    @ApiOperation(value = "通过数据集自带id精确查询author")
    private Author findAuthorByRealId(@RequestParam("id") String id){

        Author author = authorService.getByRealId(id);
        //System.out.println("------id:"+author.getAuthorid());
        return author;
    }

    @GetMapping("author/byfuzzyname")
    @ApiOperation(value = "通过name模糊查询author")
    private Result findAuthorByFuzzyName(@RequestParam("name") String name, @RequestParam("pagenum") int pagenum) throws IOException {

        Result result = new Result("搜索成功", 200);
        SearchResponse searchResponse = authorService.getByFuzzyName(name,pagenum);
        if (searchResponse.status() != RestStatus.OK)
            return new Result("没有搜索结果", 400);
        SearchHits hits = searchResponse.getHits();
        TotalHits totalHits = hits.getTotalHits();
        result.getData().put("total",totalHits.value);
        List<Author> authorList= new ArrayList<Author>();
        SearchHit[] searchHits = hits.getHits();
        int i = 1;
        for (SearchHit hit:searchHits
        ) {
            Map<String,Object> map = hit.getSourceAsMap();
            ObjectMapper objectMapper = new ObjectMapper();
            Author author = objectMapper.convertValue(map,Author.class);
            authorList.add(author);
        }
        result.getData().put("authorList",authorList);
        if (authorList == null || authorList.size()==0)return new Result("没有搜索结果", 400);

        return result;
    }
}
