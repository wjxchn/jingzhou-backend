package jingzhou.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingzhou.MySQLTable.AuthUser;
import jingzhou.MySQLTable.Paperrank;
import jingzhou.POJO.*;
import jingzhou.Service.AuthUserService;
import jingzhou.Service.AuthorService;
import jingzhou.Service.PaperService;
import jingzhou.repository.PaperRankRepository;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.MultiSearchResponse;
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

/*
* @Description 用于处理nosql数据中的[paper]数据请求
* */
@Api(value = "检索系统-Paper")
@RestController
@RequestMapping("/data/")
public class PaperController {

    @Autowired
    PaperService paperService;

    @Autowired
    AuthUserService authUserService;

    @Autowired
    AuthorService authorService;
    @Autowired
    PaperRankRepository paperRankRepository;
    /*
    * 可用
    * 通过mongodb的[_id]字段查找
    * 精确查询
    * */
//    @GetMapping("paper/id")
//    @ApiOperation(value = "通过ID精确查询paper")
    private Paper findPaperById(@RequestParam("id") String id){

        System.out.println("------try to get paper with id: "+id);
        Paper paper = paperService.getById(id);
        return paper;
    }

    /*精确查询
    * 可用
    * 通过title获取paper
    * */
    @GetMapping("paper/title")
    @ApiOperation(value = "通过titlp匹配查询paper(title中有关键词全匹配则获取)")
    private Result findPaperByTitle(@RequestParam("title") String title, @RequestParam("pagenum") int pagenum) throws IOException {
        Result result = new Result();

        SearchResponse searchResponse = paperService.getByTitleExact(title,pagenum);
        if (searchResponse.status() != RestStatus.OK)
            return new Result("没有搜索结果", 400);
        System.out.println("searchresponse返回");
        SearchHits hits = searchResponse.getHits();
        TotalHits totalHits = hits.getTotalHits();
        result.getData().put("total",totalHits.value);
        List<Paper>  paperList= new ArrayList<Paper>();
        SearchHit[] searchHits = hits.getHits();
        int i = 1;
        for (SearchHit hit:searchHits
        ) {
            Map<String,Object> map = hit.getSourceAsMap();
            ObjectMapper objectMapper = new ObjectMapper();
            Paper paper = objectMapper.convertValue(map,Paper.class);
            paperList.add(paper);
        }
        result.getData().put("paperlist",paperList);
        if (paperList == null || paperList.size()==0)return new Result("没有搜索结果", 400);

        return result;
    }

    @GetMapping("paper/fuzzytitle")
    @ApiOperation(value = "通过title模糊查询paper")
    private Result findPaperByTitleFuzzy(@RequestParam("title") String title, @RequestParam("pagenum") int pagenum) throws IOException {
        Result result = new Result();

        SearchResponse searchResponse = paperService.getTitleFuzzy(title,pagenum);
        if (searchResponse.status() != RestStatus.OK)
            return new Result("没有搜索结果", 400);
        System.out.println("searchresponse返回");
        SearchHits hits = searchResponse.getHits();
        TotalHits totalHits = hits.getTotalHits();
        result.getData().put("total",totalHits.value);
        List<Paper>  paperList= new ArrayList<Paper>();
        SearchHit[] searchHits = hits.getHits();
        int i = 1;
        for (SearchHit hit:searchHits
        ) {
            Map<String,Object> map = hit.getSourceAsMap();
            ObjectMapper objectMapper = new ObjectMapper();
            Paper paper = objectMapper.convertValue(map,Paper.class);
            paperList.add(paper);
        }
        result.getData().put("paperlist",paperList);
        if (paperList == null || paperList.size()==0)return new Result("没有搜索结果", 400);

        return result;
    }

    @GetMapping("paper/keyword")
    @ApiOperation(value = "通过keyword精确查询paper")
    private Result findPaperByKeyword(@RequestParam("keyword") String keyword, @RequestParam("pagenum") int pagenum) throws IOException {
        System.out.println("------try to get paper with keyword: "+keyword);

        Result result = new Result("获取信息成功", 200);
        List<Paper> papers = paperService.getByKeyword(keyword, pagenum);
        if (papers != null && papers.size() != 0){
            result.getData().put("papers", papers);
            return result;
        }
        return new Result("获取信息失败", 400);
    }

    @GetMapping("paper/authorname")
    @ApiOperation(value = "通过authorname精确查询paper")
    private Result findPaperByauthorname(@RequestParam("authorname") String author, @RequestParam("pagenum") int pagenum) throws IOException {
        Result result = new Result();

        SearchResponse searchResponse = paperService.getByAuthornameExact(author,pagenum);
        if (searchResponse.status() != RestStatus.OK)
            return new Result("没有搜索结果", 400);
        System.out.println("searchresponse返回");
        SearchHits hits = searchResponse.getHits();
        TotalHits totalHits = hits.getTotalHits();
        result.getData().put("total",totalHits.value);
        List<Paper>  paperList= new ArrayList<Paper>();
        SearchHit[] searchHits = hits.getHits();
        int i = 1;
        for (SearchHit hit:searchHits
        ) {
            Map<String,Object> map = hit.getSourceAsMap();
            ObjectMapper objectMapper = new ObjectMapper();
            Paper paper = objectMapper.convertValue(map,Paper.class);
            paperList.add(paper);
        }
        result.getData().put("paperlist",paperList);
        if (paperList == null || paperList.size()==0)return new Result("没有搜索结果", 400);

//        Result result = new Result();
//
//        List<Paper> paperList = paperService.getByAuthornameExactEx(author, pagenum);
//
//        System.out.println("searchresponse返回");
//
//        if (paperList == null || paperList.size()==0)return new Result("没有搜索结果", 400);
//
//        result.getData().put("paperlist",paperList);
//        result.setCode(200);
//        result.setMsg("搜索成功！");

        return result;
    }

    @GetMapping("paper/realname")
    @ApiOperation(value = "通过真实姓名查询paper")
    private Result findPaperByrealname(@RequestParam("realname") String realname) throws IOException {
        Result result = new Result();
        int total = 0;
        SearchResponse searchResponse = paperService.getByAuthornameExactRank(realname);
        if (searchResponse.status() != RestStatus.OK)
            return new Result("没有搜索结果", 400);
        System.out.println("searchresponse返回");
        SearchHits hits = searchResponse.getHits();
        TotalHits totalHits = hits.getTotalHits();
        result.getData().put("total",totalHits.value);
        List<Paper>  paperList= new ArrayList<Paper>();
        SearchHit[] searchHits = hits.getHits();
        int i = 1;
        for (SearchHit hit:searchHits
        ) {
            Map<String,Object> map = hit.getSourceAsMap();
            ObjectMapper objectMapper = new ObjectMapper();
            Paper paper = objectMapper.convertValue(map,Paper.class);
            paperList.add(paper);
        }
        if (paperList == null || paperList.size()==0)
            return new Result("没有搜索结果", 400);
        result.getData().put("paperlist",paperList);

        return result;
    }

    @GetMapping("paper/authorname/rank")
    @ApiOperation(value = "通过authorname精确获取最高引用")
    private Result findPaperByauthornameRank(@RequestParam("authorname") String author) throws IOException {
        Result result = new Result();
        int total = 0;

        SearchResponse searchResponse = paperService.getByAuthornameExactRank(author);
        if (searchResponse.status() != RestStatus.OK)
            return new Result("没有搜索结果", 400);
        System.out.println("searchresponse返回");
        SearchHits hits = searchResponse.getHits();
        TotalHits totalHits = hits.getTotalHits();
        result.getData().put("total",totalHits.value);
        List<Paper>  paperList= new ArrayList<Paper>();
        SearchHit[] searchHits = hits.getHits();
        int i = 1;
        for (SearchHit hit:searchHits
        ) {
            Map<String,Object> map = hit.getSourceAsMap();
            ObjectMapper objectMapper = new ObjectMapper();
            Paper paper = objectMapper.convertValue(map,Paper.class);
            paperList.add(paper);
        }
        if (paperList == null || paperList.size()==0)return new Result("没有搜索结果", 400);
//        List<Paper> paperList1 = new ArrayList<>();
//        for (Paper paper : paperList){
//            for (Paper_Author paper_author : paper.getAuthors()){
//                if (paper_author.getName().equals(author)){
//                    paperList1.add(paper);
//                    total++;
//                    break;
//                }
//            }
//        }
//        result.getData().put("total",total);
        if (paperList.size()<=5){
            result.getData().put("paperlist",paperList);
        }
        else {
            result.getData().put("paperlist",paperList.subList(0,5));
        }

//        Result result = new Result();
//
//        List<Paper> paperList = paperService.getByAuthornameExactEx(author, pagenum);
//
//        System.out.println("searchresponse返回");
//
//        if (paperList == null || paperList.size()==0)return new Result("没有搜索结果", 400);
//
//        result.getData().put("paperlist",paperList);
//        result.setCode(200);
//        result.setMsg("搜索成功！");

        return result;
    }

    @GetMapping("paper/authorusername/rank")
    @ApiOperation(value = "通过authorusername精确获取最高引用")
    private Result findPaperByauthorusernameRank(@RequestParam("authorusername") String authorusername) throws IOException {
        Result result = new Result();
        int total = 0;
        AuthUser authUser = authUserService.getAuthUserByUsername(authorusername);
        String authorrealname = authUser.getRealname();
        SearchResponse searchResponse = paperService.getByAuthornameExactRank(authorrealname);
        if (searchResponse.status() != RestStatus.OK)
            return new Result("没有搜索结果", 400);
        System.out.println("searchresponse返回");
        SearchHits hits = searchResponse.getHits();
        TotalHits totalHits = hits.getTotalHits();
        result.getData().put("total",totalHits.value);
        List<Paper>  paperList= new ArrayList<Paper>();
        SearchHit[] searchHits = hits.getHits();
        int i = 1;
        for (SearchHit hit:searchHits
        ) {
            Map<String,Object> map = hit.getSourceAsMap();
            ObjectMapper objectMapper = new ObjectMapper();
            Paper paper = objectMapper.convertValue(map,Paper.class);
            paperList.add(paper);
        }
        if (paperList == null || paperList.size()==0)
            return new Result("没有搜索结果", 400);
        if (paperList.size()<=5){
            result.getData().put("paperlist",paperList);
        }
        else {
            result.getData().put("paperlist",paperList.subList(0,5));
        }
        return result;
    }

    @GetMapping("paper/fuzzykeyword")
    @ApiOperation(value = "通过keyword模糊查询paper")
    private Result elasticsearchFuzzyKeyword(@RequestParam("keyword") String keyword, @RequestParam("pagenum") int pagenum) throws IOException {
        Result result = new Result();
        SearchResponse searchResponse = paperService.getByFuzzyKeyword(keyword,pagenum);
        if (searchResponse.status() != RestStatus.OK)
            return new Result("没有搜索结果", 400);
        SearchHits hits = searchResponse.getHits();
        TotalHits totalHits = hits.getTotalHits();
        result.getData().put("total",totalHits.value);
        List<Paper>  paperList= new ArrayList<Paper>();
        SearchHit[] searchHits = hits.getHits();
        int i = 1;
        for (SearchHit hit:searchHits
             ) {
            Map<String,Object> map = hit.getSourceAsMap();
            ObjectMapper objectMapper = new ObjectMapper();
            Paper paper = objectMapper.convertValue(map,Paper.class);
            paperList.add(paper);
        }
        result.getData().put("paperlist",paperList);
        if (paperList == null || paperList.size()==0)return new Result("没有搜索结果", 400);

        return result;
    }

    @GetMapping("paper/authorfuzzyname")
    @ApiOperation(value = "通过authorname模糊查询paper")
    private Result elasticsearchAuthorName(@RequestParam("author") String name, @RequestParam("pagenum") int pagenum) throws IOException {
        Result result = new Result();
        SearchResponse searchResponse = paperService.getByAuthorname(name,pagenum);
        if (searchResponse.status() != RestStatus.OK)
            return new Result("没有搜索结果", 400);
        SearchHits hits = searchResponse.getHits();
        TotalHits totalHits = hits.getTotalHits();
        result.getData().put("total",totalHits.value);
        List<Paper>  paperList= new ArrayList<Paper>();
        SearchHit[] searchHits = hits.getHits();
        int i = 1;
        for (SearchHit hit:searchHits
        ) {
            Map<String,Object> map = hit.getSourceAsMap();
            ObjectMapper objectMapper = new ObjectMapper();
            Paper paper = objectMapper.convertValue(map,Paper.class);
            paperList.add(paper);
        }
        result.getData().put("paperlist",paperList);
        if (paperList == null || paperList.size()==0)return new Result("没有搜索结果", 400);

        return result;
    }

    @GetMapping("paper/issn")
    @ApiOperation(value = "通过issn模糊查询paper")
    private Result elasticsearchIssn(@RequestParam("issn") String issn, @RequestParam("pagenum") int pagenum) throws IOException {
        Result result = new Result();
        SearchResponse searchResponse = paperService.getIssn(issn,pagenum);
        ;
        if (searchResponse.status() != RestStatus.OK)
            return new Result("没有搜索结果", 400);
        System.out.println("searchResponse ok");
        SearchHits hits = searchResponse.getHits();
        TotalHits totalHits = hits.getTotalHits();
        result.getData().put("total",totalHits.value);
        List<Paper>  paperList= new ArrayList<Paper>();
        SearchHit[] searchHits = hits.getHits();
        int i = 1;
        for (SearchHit hit:searchHits
        ) {
            Map<String,Object> map = hit.getSourceAsMap();
            ObjectMapper objectMapper = new ObjectMapper();
            Paper paper = objectMapper.convertValue(map,Paper.class);
            paperList.add(paper);
        }
        result.getData().put("paperlist",paperList);
        if (paperList == null || paperList.size()==0)return new Result("没有搜索结果", 400);

        return result;
    }

    @GetMapping("paper/idorpaperid")
    @ApiOperation(value = "通过_id或者paperid查询paper")
    private Result elasticsearchIssn(@RequestParam("id") String id) throws IOException {
        Result result = new Result();
        MultiSearchResponse response = paperService.getByAnId(id);
        MultiSearchResponse.Item firstResponse = response.getResponses()[0];
        SearchResponse searchResponse1 = firstResponse.getResponse();
        MultiSearchResponse.Item secondResponse = response.getResponses()[1];
        SearchResponse searchResponse2 = secondResponse.getResponse();
//        System.out.println(searchResponse1);
//        System.out.println(searchResponse2);
        if (searchResponse2.status() != RestStatus.OK && searchResponse1.status() != RestStatus.OK)
            return new Result("没有搜索结果", 400);
        System.out.println("searchResponse ok");
        SearchHit[] hit1 = searchResponse1.getHits().getHits();
        SearchHit[] hit2 = searchResponse2.getHits().getHits();
        Paper paper = null;
        ObjectMapper objectMapper = new ObjectMapper();
        if (hit1.length != 0){
            Map<String, Object> map = hit1[0].getSourceAsMap();
            paper = objectMapper.convertValue(map, Paper.class);
        } else if (hit2.length != 0){
            Map<String, Object> map = hit2[0].getSourceAsMap();
            paper = objectMapper.convertValue(map, Paper.class);
        }
        if (paper != null){
            result.getData().put("paper", paper);
            return result;
        }
        else return new Result("没有搜索结果", 400);
    }

    @GetMapping("paper/addclick")
    @ApiOperation(value = "访问paper时点击数+1")
    private Result addclick(@RequestParam("paperid") String paperid) throws IOException {

        Paperrank paperrank = paperRankRepository.findPaperrankByPaperid(paperid);
        if(paperrank!=null){
            paperrank.setAmount(paperrank.getAmount()+1);
            paperRankRepository.saveAndFlush(paperrank);
        }
        else{
            Paperrank new_paperrank = new Paperrank();
            new_paperrank.setPaperid(paperid);
            new_paperrank.setAmount(1);
            paperRankRepository.save(new_paperrank);
        }
        return new Result("添加点击次数成功", 200);
    }

    @GetMapping("paper/username")
    @ApiOperation(value = "通过用户名查询paper")
    private Result mySqlUsernameToElasticsearchPaper(@RequestParam("username") String username) throws IOException {
        Result result = new Result();
        int total = 0;
        AuthUser authUser = authUserService.getAuthUserByUsername(username);
        String authorrealname = authUser.getRealname();
        SearchResponse searchResponse = paperService.getByAuthornameExactRank(authorrealname);
        if (searchResponse.status() != RestStatus.OK)
            return new Result("没有搜索结果", 400);
        System.out.println("searchresponse返回");
        SearchHits hits = searchResponse.getHits();
        TotalHits totalHits = hits.getTotalHits();
        result.getData().put("total",totalHits.value);
        List<Paper>  paperList= new ArrayList<Paper>();
        SearchHit[] searchHits = hits.getHits();
        int i = 1;
        for (SearchHit hit:searchHits
        ) {
            Map<String,Object> map = hit.getSourceAsMap();
            ObjectMapper objectMapper = new ObjectMapper();
            Paper paper = objectMapper.convertValue(map,Paper.class);
            paperList.add(paper);
        }
        if (paperList == null || paperList.size()==0)
            return new Result("没有搜索结果", 400);
        result.getData().put("paperlist",paperList);

        return result;
    }
}
