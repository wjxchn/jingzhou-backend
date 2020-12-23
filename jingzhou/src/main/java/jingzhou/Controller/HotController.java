package jingzhou.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingzhou.MySQLTable.InstitutionRank;
import jingzhou.MySQLTable.PaperRank;
import jingzhou.POJO.Result;
import jingzhou.POJO.Paper;
import jingzhou.Service.PaperService;
import jingzhou.repository.InstitutionRankRepository;
import jingzhou.repository.PaperRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    PaperService paperservice;

    @ApiOperation(value = "获取论文点击次数排名接口")
    @GetMapping("paperamountrank")
    public Result PaperRank(){
        List<PaperRank> allpaperrank = paperRankRepository.findAll();
        List<Paper> paperlist = new List<Paper>();
        for(PaperRank paperrank : allpaperrank){
            String paper_id = paperrank.getPaperid();
            Paper paper = paperservice.getById(paper_id);
            paperlist.add(paper);
        }
        Result result = new Result("获取热点论文排名成功",200);
        result.getData().put("paperlist", paperlist);
        return result;
    }

    @ApiOperation(value = "获取论文被引次数排名接口")
    @GetMapping("papercitationrank")
    public Result Papercitationrank(){
        List<Paper> paperlist = paperservice.getByCitation();

        Result result = new Result("获取论文被引次数排名成功",200);
        result.getData().put("paperlist", paperlist);
        return result;
    }
}
