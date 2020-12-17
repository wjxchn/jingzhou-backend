package jingzhou.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingzhou.MySQLTable.InstitutionRank;
import jingzhou.MySQLTable.PaperRank;
import jingzhou.POJO.Result;
import jingzhou.repository.InstitutionRankRepository;
import jingzhou.repository.PaperRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "热点信息管理子系统")
@RestController
public class HotController {
    @Autowired
    private InstitutionRankRepository institutionRankRepository;

    @Autowired
    private PaperRankRepository paperRankRepository;

    @ApiOperation(value = "获取热点论文排名接口")
    @GetMapping("showpaperrank")
    public Result PaperRank(){
        List<PaperRank> allpaperrank = paperRankRepository.findAll();

        Result result = new Result("获取热点论文排名成功",200);
        result.getData().put("allpaperrank", allpaperrank);
        return result;
    }

    @ApiOperation(value = "获取科研成果排名接口")
    @GetMapping("showinstitutionrank/{type}")
    public Result showinstitutionrank(@PathVariable("type") String type){
        List<InstitutionRank> institutionrankselected = institutionRankRepository.findAllByType(type);

        Result result = new Result("获取科研成果排名成功",200);
        result.getData().put("instituionrankselected",institutionrankselected);
        return result;
    }
}
