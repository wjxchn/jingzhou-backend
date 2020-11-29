package jingzhou.Controller;


import jingzhou.POJO.Paper;
import jingzhou.Service.ServiceImpl.PaperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    * */
    @RequestMapping("/api/paper/byid")
    private Paper findPaperById(@RequestParam("id") String id){

        System.out.println("------try to get paper:"+id);
        Paper paper = paperService.getById(id);
        //System.out.println("------id:"+paper.get_id());
        return paper;
    }

    /*不可用*/
    @RequestMapping("/api/paper/bytitle")
    private Paper findPaperByTitle(@RequestParam("title") String title){

        System.out.println("------try to get paper:"+title);
        Paper paper = paperService.getById(title);
        //System.out.println("------id:"+paper.get_id());
        return paper;
    }
}
