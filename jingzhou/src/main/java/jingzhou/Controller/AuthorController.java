package jingzhou.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingzhou.POJO.Author;
import jingzhou.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 *这个控制类用于处理:nosql数据中的[作者]数据
 *
 */
@Api(value = "检索系统-Author")
@RestController
@RequestMapping("/data/")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    /*精确查找*/
    @GetMapping("author/byname")
    @ApiOperation(value = "通过name精确查询author")
    private Author findByName(@RequestParam("name") String name){
        return authorService.getByName(name);
    }

    /*精确查找
    * @Param id:数据自带的id项
    * */
    @GetMapping("author/byrealid")
    @ApiOperation(value = "通过数据集自带id精确查询author")
    private Author findAuthorByRealId(@RequestParam("id") String id){

        Author author = authorService.getByRealId(id);
        System.out.println("------id:"+author.getAuthorid());
        return author;
    }
}
