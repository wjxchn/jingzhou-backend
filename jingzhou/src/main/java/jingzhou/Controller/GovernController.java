package jingzhou.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingzhou.MySQLTable.AuthUser;
import jingzhou.repository.AuthUserRepository;
import jingzhou.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Api(value = "门户与学术管理子系统")
public class GovernController {
    @Autowired
    AuthUserRepository authUserRepository;

    @Autowired
    InstitutionRepository institutionRepository;

    @ApiOperation(value = "认领门户接口")
    @PostMapping("claimportal")
    public Map<String, Object> claimportal(@RequestParam("username") String username,@RequestParam("institutionname") String institutionname, @RequestParam("researchfield") String researchfield, @RequestParam("realname") String realname){
        HashMap<String, Object> result = new HashMap<>();
        AuthUser authUser = new AuthUser();
        authUser.setUsername(username);
        authUser.setInstitutionid(institutionRepository.findInstitutionByInstitutionname(institutionname).getInstitutionid());
        authUser.setResearchfield(researchfield);
        authUser.setRealname(realname);
        authUserRepository.save(authUser);
        result.put("code",200);
        result.put("msg","认领门户成功");
        return result;

    }

    @ApiOperation(value = "门户信息修改接口")
    @PostMapping("changeportal/{changetype}")
    public Map<String, Object> changeportal(@PathVariable("changetype") String changetype, @RequestParam("username") String username, @RequestParam("changecontent") String changecontent){
        HashMap<String, Object> result = new HashMap<>();
        AuthUser authUser = authUserRepository.findAuthUserByUsername(username);
        if(changetype.equals("researchfield")){
            authUser.setResearchfield(changecontent);
            result.put("code",201);
            result.put("msg","门户信息中的研究领域修改成功");
            return result;
        }
        else if(changetype.equals("realname")){
            authUser.setRealname(changecontent);
            result.put("code",202);
            result.put("msg","门户信息中的真实姓名 修改成功");
            return result;
        }
        else{
            result.put("code",400);
            result.put("msg","门户信息修改失败");
            return result;
        }
    }

}
