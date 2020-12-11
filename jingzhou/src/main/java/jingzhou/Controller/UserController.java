package jingzhou.Controller;

import jingzhou.MySQLTable.User;
import jingzhou.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(value = "个人信息管理子系统")
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "注册接口")
    @PostMapping("register")
    public Map<String,Object> register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email){
        HashMap<String,Object> result = new HashMap<>();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setIsauth(0);
        user.setDownloadauth(0);
        user.setEmail(email);
        user.setTime(new Date());
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

    @ApiOperation(value = "用户信息显示接口")
    @GetMapping("showuserinfo/{username}")
    public Map<String,Object> showuserinfo(@PathVariable("username") String username){
        User user = userRepository.findUserByUsername(username);
        HashMap<String,Object> result = new HashMap<>();
        if(user!=null){
            result.put("code",200);
            result.put("msg","登录成功");
            result.put("username",user.getUsername());
            result.put("isauth",user.getIsauth());
            result.put("downloadauth",user.getDownloadauth());
            result.put("email",user.getEmail());
            result.put("registertime",user.getTime());
            return result;
        }
        else{
            result.put("code",400);
            result.put("msg","显示用户信息失败");
            return result;
        }
    }

    @ApiOperation(value = "用户信息修改接口")
    @PostMapping("changeuserinfo/{username}/{changecontent}")
    public Map<String,Object> changeuserinfo(@PathVariable("username") String username, @PathVariable("changecontent") String changecontent, @RequestParam("content") String content){
        User user = userRepository.findUserByUsername(username);
        HashMap<String,Object> result = new HashMap<>();
        if(user!=null){
            if(changecontent.equals("password")){
                user.setPassword(content);
                result.put("code",201);
                result.put("msg","修改用户密码成功");
                return result;
            }
            else if(changecontent.equals("email")){
                user.setEmail(content);
                result.put("code",202);
                result.put("msg","修改用户邮箱成功");
                return result;
            }
            else{
                result.put("code",401);
                result.put("msg","修改用户信息类型错误");
                return result;
            }
        }
        else{
            result.put("code",400);
            result.put("msg","找不到用户");
            return result;
        }
    }

}
