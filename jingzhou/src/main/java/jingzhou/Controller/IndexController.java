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




    @RequestMapping("/")
    public String index(){
        return "forward:/index.html";
    }





}
