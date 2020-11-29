package jingzhou.Controller;

import jingzhou.POJO.Author;
import jingzhou.Service.ServiceImpl.AuthorServiceImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *这个控制类用于处理:nosql数据中的[作者]数据
 *
 */

@RestController
public class AuthorController {

    @Autowired
    AuthorServiceImpl authorService;

    /*精确查找*/
    @RequestMapping("/api/author/{name}")
    private Author findByName(@PathVariable("name") String name){
        return authorService.getByName(name);
    }

    /*精确查找
    * @Param id:mongodb生成的_id项
    * */
    @RequestMapping("/api/author/byid")
    private Author findAuthorById(@RequestParam("id") String id){
        ObjectId objectId = new ObjectId(id);
        System.out.println("------try to get author:"+id);
        Author author = authorService.getById(objectId);
        System.out.println("------id:"+author.get_id());
        return author;
    }

    /*精确查找
    * @Param id:数据自带的id项
    * */
    @RequestMapping("/api/author/byrealid")
    private Author findAuthorByRealId(@RequestParam("id") String id){

        Author author = authorService.getByRealId(id);
        System.out.println("------id:"+author.get_id());
        return author;
    }
}
