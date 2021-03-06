package jingzhou.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingzhou.MySQLTable.AuthUser;
import jingzhou.MySQLTable.Institution;
import jingzhou.MySQLTable.User;
import jingzhou.POJO.Author;
import jingzhou.POJO.Result;
import jingzhou.Service.AuthUserService;
import jingzhou.Service.AuthorService;
import jingzhou.Service.InstitutionService;
import jingzhou.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "信息检索子系统")
@RestController
@RequestMapping("/search/")
public class SearchController {
    @Autowired
    private UserService userService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthUserService authUserService;

    @ApiOperation(value = "获取科研人员信息接口")
    @GetMapping("showauthuser")
    public Result showauhtuser(@RequestParam("userid") int userid){

        AuthUser authuserobject = authUserService.getAuthUserByUserID(userid);
        Author author = authorService.getByRealId(authuserobject.getAuthorid());
        Result result = new Result("获取信息成功", 200);
        User user = userService.getUserById(userid);
        if (user != null){
            result.getData().put("user", user);
            result.getData().put("author",author);
            return result;
        }
        return new Result("获取信息失败", 400);
    }

    @ApiOperation(value = "获取科研机构信息接口")
    @GetMapping("showinstitution")
    public Result showinstitution(@RequestParam("institutionid") int instituteid){

        Result result = new Result("获取信息成功", 200);
        Institution institutionobject = institutionService.getByID(instituteid);
        if (institutionobject != null){
            result.getData().put("institution",institutionobject);
            return result;
        }

        return new Result("获取信息失败", 400);
    }

     /* 模糊查找 */
     @GetMapping("byinstitutionname")
     @ApiOperation(value = "通过name模糊查找机构")
     private Result findInstitutionByname(@RequestParam("name") String name){

         Result result = new Result("获取信息成功", 200);
         List<Institution> Institutions = institutionService.getBykeyword(name);
         if (Institutions != null && Institutions.size() != 0){
             result.getData().put("institutions", Institutions);
             return result;
         }
         return new Result("获取信息失败", 400);
     }

}
