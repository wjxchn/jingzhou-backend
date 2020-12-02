package jingzhou.Controller;

import jingzhou.MySQLTable.User;
import jingzhou.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.*;

import java.util.Date;

@Api(value = "用户控制类")
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "获取用户", notes = "根据用户ID获取用户注册时间")
    @GetMapping("find_user_register_time/{id}")
    public String findUserById(@PathVariable("id") Integer id){
        return userRepository.findUserById((int)id).getTime().toString();
    }

}
