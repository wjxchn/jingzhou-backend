package jingzhou.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingzhou.MySQLTable.AuthUser;
import jingzhou.POJO.Result;
import jingzhou.Service.AuthUserService;
import jingzhou.Service.InstitutionService;
import jingzhou.repository.AuthUserRepository;
import jingzhou.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(value = "门户与学术管理子系统")
@RestController
public class GovernController {
    @Autowired
    AuthUserService authUserService;

    @Autowired
    InstitutionService institutionService;

    @ApiOperation(value = "认领门户接口")
    @PostMapping("claimportal")
    public Result claimportal(@RequestParam("username") String username,@RequestParam("institutionname") String institutionname, @RequestParam("researchfield") String researchfield, @RequestParam("realname") String realname){

        AuthUser authUser = new AuthUser();
        authUser.setUsername(username);
        authUser.setInstitutionid(institutionService.getByName(institutionname).getInstitutionid());
        authUser.setResearchfield(researchfield);
        authUser.setRealname(realname);

        Result result = new Result("获取信息成功", 200);
        result.getData().put("authUser", authUser);

        return result;

    }

    @ApiOperation(value = "门户信息修改接口")
    @PostMapping("changeportal/{changetype}")
    public Result changeportal(@PathVariable("changetype") String changetype, @RequestParam("username") String username, @RequestParam("changecontent") String changecontent){
        AuthUser authUser = authUserService.getAuthUserByUsername(username);

        Result result = new Result();
        if(changetype.equals("researchfield")){
            authUser.setResearchfield(changecontent);
            result.setMsg("门户信息中的研究领域修改成功");
            result.setCode(201);
            return result;
        }
        else if(changetype.equals("realname")){
            authUser.setRealname(changecontent);
            result.setMsg("门户信息中的真实姓名 修改成功");
            result.setCode(202);
            return result;
        }
        else{
            result.setMsg("门户信息修改失败");
            result.setCode(400);
            return result;
        }
    }

}
