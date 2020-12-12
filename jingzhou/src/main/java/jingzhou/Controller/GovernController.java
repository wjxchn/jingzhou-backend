package jingzhou.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingzhou.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Api(value = "门户与学术管理子系统")
public class GovernController {
    @Autowired
    AuthUserRepository authUserRepository;

    @ApiOperation(value = "认领门户接口")
    @PostMapping("claimportal")
    public Map<String, Object> claimportal(@RequestParam("username") String username){
        HashMap<String, Object> result = new HashMap<>();

    }

    @ApiOperation(value = "门户信息修改接口")
    @PostMapping("changeportal/{changetype}")
    public Map<String, Object> changeportal(){

    }

}
