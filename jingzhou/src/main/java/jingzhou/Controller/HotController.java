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
import org.elasticsearch.action.search.SearchResponse;
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
    public Result PaperRank(){
        List<Paperrank> allpaperrank = paperRankRepository.findAll();
        List<Paper> paperlist = new ArrayList<>();
        for(Paperrank paperrank : allpaperrank){
            String paper_id = paperrank.getPaperid();
            Paper paper = paperservice.getById(paper_id);
            paperlist.add(paper);
        }
        Result result = new Result("获取热点论文排名成功",200);
        result.getData().put("paperlist", paperlist);
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
