package jingzhou.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingzhou.MySQLTable.AuthUser;
import jingzhou.MySQLTable.Institution;
import jingzhou.MySQLTable.User;
import jingzhou.POJO.Author;
import jingzhou.POJO.Result;
import jingzhou.Service.AuthorService;
import jingzhou.Service.InstitutionService;
import jingzhou.Service.UserService;
import jingzhou.repository.AuthUserRepository;
import jingzhou.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.net.www.protocol.http.AuthScheme;

import java.util.HashMap;
import java.util.Map;

@Api(value = "信息检索子系统")
@RestController
public class SearchController {
    @Autowired
    private UserService userService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired

    private AuthorService authorService;

    @ApiOperation(value = "获取科研人员信息接口")
    @GetMapping("showauthuser")
    public Result showauhtuser(@RequestParam("userid") int userid){

        AuthUser authuserobject = userService.getAuthUserByUserID(userid);
        Author author = authorService.getByRealId(authuserobject.getAuthorid());
        Result result = new Result("获取信息成功", 200);
        User user = userService.getUserById(userid);
        result.getData().put("user", user);
        result.getData().put("author",author);
        return result;
    }

    @ApiOperation(value = "获取科研机构信息接口")
    @GetMapping("showinstitution")
    public Result showinstitution(@RequestParam("institutionid") int instituteid){

        Institution institutionobject = institutionService.getByID(instituteid);
        Result result = new Result();
        result.getData().put("institution",institutionobject);
        return result;
    }

}
