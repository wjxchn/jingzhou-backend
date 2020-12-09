package jingzhou.Controller;

import jingzhou.MySQLTable.User;
import jingzhou.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(value = "用户控制类")
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "注册接口")
    @PostMapping("register")
    public Map<String,Object> register(@RequestParam("username") String username, @RequestParam("password") String password){
        HashMap<String,Object> result = new HashMap<>();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        result.put("code",200);
        result.put("msg","注册成功");
        return result;
    }

    @ApiOperation(value = "登录接口")
    @PostMapping("login")
    public Map<String,Object> login(@RequestParam("username") String username, @RequestParam("password") String password){
        HashMap<String,Object> result = new HashMap<>();
        if(userRepository.findUserByUsernameAndPassword(username,password)!=null){
            result.put("code",200);
            result.put("msg","登录成功");
            return result;
        }
        else{
            result.put("code",400);
            result.put("msg","登录失败");
            return result;
        }
    }









    @ApiOperation(value = "获取用户", notes = "根据用户ID获取用户注册时间")
    @GetMapping("find_user_register_time/{id}")
    public String findUserById(@PathVariable("id") Integer id){
        return userRepository.findUserById((int)id).getTime().toString();
    }

}
