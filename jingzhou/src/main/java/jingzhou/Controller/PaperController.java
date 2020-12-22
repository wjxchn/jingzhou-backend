package jingzhou.Controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingzhou.POJO.Paper;
import jingzhou.Service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
* @Description 用于处理nosql数据中的[paper]数据请求
* */
@Api(value = "检索系统-Paper")
@RestController
@RequestMapping("/data/")
public class PaperController {

    @Autowired
    PaperService paperService;

    /*
    * 可用
    * 通过mongodb的[_id]字段查找
    * 精确查询
    * */
    @GetMapping("paper/id")
    @ApiOperation(value = "通过ID精确查询paper")
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
    @ApiOperation(value = "通过title精确查询paper")
    private Paper findPaperByTitle(@RequestParam("title") String title){

        System.out.println("------try to get paper with title:"+title);
        Paper paper = paperService.getByTitle(title);
        return paper;
    }

    @GetMapping("paper/fuzzytitle")
    @ApiOperation(value = "通过title模糊查询paper")
    private List<Paper> findPaperByTitleFuzzy(@RequestParam("title") String title, @RequestParam("pagenum") int pagenum){
        System.out.println("------try to get paper with title: "+title);
        List<Paper> papers = paperService.getByFuzzyTitle(title, pagenum);
        return papers;
    }

    @GetMapping("paper/keyword")
    @ApiOperation(value = "通过keyword精确查询paper")
    private List<Paper> findPaperByKeyword(@RequestParam("keyword") String keyword, @RequestParam("pagenum") int pagenum){
        System.out.println("------try to get paper with keyword: "+keyword);
        List<Paper> papers = paperService.getByKeyword(keyword, pagenum);
        return papers;
    }

    @GetMapping("paper/authorname")
    @ApiOperation(value = "通过authorname精确查询paper")
    private List<Paper> findPaperByauthorname(@RequestParam("authorname") String author, @RequestParam("pagenum") int pagenum){
        System.out.println("------try to get paper with keyword: "+author);
        List<Paper> papers = paperService.getByAuthor(author, pagenum);
        return papers;
    }
}
