package jingzhou.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingzhou.MySQLTable.Paperrank;
import jingzhou.POJO.Paper;
import jingzhou.POJO.Result;
import jingzhou.Service.PaperService;
import jingzhou.repository.InstitutionRankRepository;
import jingzhou.repository.PaperRankRepository;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(value = "热点信息管理子系统")
@RestController
@RequestMapping("/trend/")
public class HotController {
    @Autowired
    private InstitutionRankRepository institutionRankRepository;

    @Autowired
    private PaperRankRepository paperRankRepository;

    @Autowired
    private  PaperService paperservice;

    @ApiOperation(value = "获取论文点击次数排名接口")
    @GetMapping("paper/amount/rank")
    public Result PaperRank(@RequestParam("pagenum") int pagenum) throws IOException{
        Sort sort = Sort.by(Sort.Direction.DESC, "amount");
        List<Paperrank> allpaperrank = paperRankRepository.findAll(sort);
        List<Integer> amountlist = new ArrayList<Integer>();
        List<Paper> paperlist = new ArrayList<>();
        for(int i = pagenum * 20; i < (pagenum + 1) * 20 && i < allpaperrank.size(); i++){
            Paperrank paperrank = allpaperrank.get(i);
            String paper_id = paperrank.getPaperid();
            MultiSearchResponse response = paperservice.getByAnId(paper_id);
            MultiSearchResponse.Item firstResponse = response.getResponses()[0];
            SearchResponse searchResponse1 = firstResponse.getResponse();
            MultiSearchResponse.Item secondResponse = response.getResponses()[1];
            SearchResponse searchResponse2 = secondResponse.getResponse();
  //        System.out.println(searchResponse1);
  //        System.out.println(searchResponse2);
            if (searchResponse2.status() != RestStatus.OK && searchResponse1.status() != RestStatus.OK)
                return new Result("查询失败", 400);
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
                paperlist.add(paper);
                amountlist.add(paperrank.getAmount());
            }
        }
        Result result = new Result("获取热点论文排名成功",200);
        result.getData().put("paperlist", paperlist);
        result.getData().put("amountlist", amountlist);
        return result;
    }

    @ApiOperation(value = "获取论文被引次数排名接口,排序，每次取前20")
    @GetMapping("paper/citation/rank")
    public Result Papercitationrank(@RequestParam("pagenum") int pagenum) throws IOException {
        System.out.println("begin");
        SearchResponse searchResponse = paperservice.getByCitation(pagenum);
        SearchHits searchHits = searchResponse.getHits();
        long total = searchHits.getTotalHits().value;
        SearchHit[] hits = searchHits.getHits();
        List<Paper> paperlist = new ArrayList<Paper>();
        for (SearchHit hit:hits
             ) {
            Map<String, Object> map = hit.getSourceAsMap();
            ObjectMapper objectMapper = new ObjectMapper();
            Paper paper = objectMapper.convertValue(map, Paper.class);
            paperlist.add(paper);
        }
        Result result = new Result("获取论文被引次数排名成功",200);
        result.getData().put("paperlist", paperlist);
        result.getData().put("total", total);
        return result;
    }
}
