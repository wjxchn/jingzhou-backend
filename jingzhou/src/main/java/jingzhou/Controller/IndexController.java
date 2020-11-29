package jingzhou.Controller;

import jingzhou.POJO.Author;
import jingzhou.POJO.Paper;
import jingzhou.Service.ServiceImpl.AuthorServiceImpl;
import jingzhou.Service.ServiceImpl.PaperServiceImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    AuthorServiceImpl authorService;

    @Autowired
    PaperServiceImpl paperService;

    @RequestMapping("/")
    public String index(){
        return "forward:/index.html";
    }

    @RequestMapping("/api/author/{name}")
    private Author findByName(@PathVariable("name") String name){
        return authorService.getByName(name);
    }

    @RequestMapping("/api/author/byid")
    private Author findAuthorById(@RequestParam String id){
        ObjectId objectId = new ObjectId(id);
        System.out.println("try to get author:"+id);
        Author author = authorService.getById(objectId);
        System.out.println("------id:"+author.get_id());
        return author;
    }
    @RequestMapping("/api/paper/byid")
    private Paper findPaperById(@RequestParam String id){

        System.out.println("try to get author:"+id);
        Paper paper = paperService.getById(id);
        //System.out.println("------id:"+paper.get_id());
        return paper;
    }
}
