package jingzhou.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingzhou.MySQLTable.AuthUser;
import jingzhou.MySQLTable.Institution;
import jingzhou.repository.AuthUserRepository;
import jingzhou.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(value = "信息检索子系统")
@RestController
public class SearchController {
    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @ApiOperation(value = "获取科研人员信息接口")
    @GetMapping("showauthuser/{username}")
    public Map<String, Object> showauhtuser(@PathVariable("username") String username){
        HashMap<String, Object> result = new HashMap<>();
        AuthUser authuserobject = authUserRepository.findAuthUserByUsername(username);
        result.put("code",200);
        result.put("msg","获取科研人员信息成功");
        result.put("authuser",authuserobject);
        return result;
    }

    @ApiOperation(value = "获取科研机构信息接口")
    @GetMapping("showinstitution/{instituteid}")
    public Map<String, Object> showinstitution(@PathVariable("instituteid") String instituteid){
        HashMap<String, Object> result = new HashMap<>();
        Institution institutionobject = institutionRepository.findInstitutionByInstituteid(Integer.parseInt(instituteid));
        result.put("code",200);
        result.put("msg","获取科研机构信息成功");
        result.put("authuser",institutionobject);
        return result;
    }

}
