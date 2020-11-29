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
    * 精确查询
    * */
    @RequestMapping("/api/paper/byid")
    private Paper findPaperById(@RequestParam("id") String id){

        System.out.println("------try to get paper:"+id);
        Paper paper = paperService.getById(id);
        return paper;
    }

    /*精确查询
    * 可用
    * 通过title获取paper
    * */
    @RequestMapping("/api/paper/bytitle")
    private Paper findPaperByTitle(@RequestParam("title") String title){

        System.out.println("------try to get paper:"+title);
        Paper paper = paperService.getByTitle(title);
        return paper;
    }


    @RequestMapping("/api/paper/byfuzzytitle")
    private Paper findPaperByTitleFuzzy(@RequestParam("title") String title){

        /*
        * //完全匹配
Pattern pattern = Pattern.compile("^hzb$", Pattern.CASE_INSENSITIVE);
//右匹配
Pattern pattern = Pattern.compile("^.*hzb$", Pattern.CASE_INSENSITIVE);
//左匹配
Pattern pattern = Pattern.compile("^hzb.*$", Pattern.CASE_INSENSITIVE);
//模糊匹配
Pattern pattern = Pattern.compile("^.*hzb.*$", Pattern.CASE_INSENSITIVE);
Query query = Query.query(Criteria.where(fieldName).regex(pattern));
        List<StorageBO> storages = mongoTemplate.find(query, StorageBO.class, collectionName);
        return storages;
        * */

        System.out.println("------try to get paper:"+title);
        Paper paper = paperService.getByTitle(title);
        return paper;
    }
}
