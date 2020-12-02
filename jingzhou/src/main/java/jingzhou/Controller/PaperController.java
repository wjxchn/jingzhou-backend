package jingzhou.Controller;


import jingzhou.POJO.Paper;
import jingzhou.Service.ServiceImpl.PaperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
* @Description 用于处理nosql数据中的[paper]数据请求
* */
@RestController
public class PaperController {

    @Autowired
    PaperServiceImpl paperService;

    /*
    * 可用
    * 通过mongodb的[_id]字段查找
    * 精确查询
    * */
    @RequestMapping("/api/paper/id")
    private Paper findPaperById(@RequestParam("id") String id){

        System.out.println("------try to get paper:"+id);
        Paper paper = paperService.getById(id);
        return paper;
    }

    /*精确查询
    * 可用
    * 通过title获取paper
    * */
    @RequestMapping("/api/paper/title")
    private Paper findPaperByTitle(@RequestParam("title") String title){

        System.out.println("------try to get paper:"+title);
        Paper paper = paperService.getByTitle(title);
        return paper;
    }


    @RequestMapping("/api/paper/fuzzytitle")
    private List<Paper> findPaperByTitleFuzzy(@RequestParam("title") String title){

        System.out.println("------try to get paper:"+title);
        List<Paper> papers = paperService.getByFuzzyTitle(title);
        return papers;
    }
}
