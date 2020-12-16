package jingzhou.Controller;

import jingzhou.MySQLTable.User;
import jingzhou.POJO.Result;
import jingzhou.Service.UserService;
import jingzhou.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;
import sun.jvm.hotspot.runtime.ResultTypeFinder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(value = "个人信息管理子系统")
@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册接口")
    @PostMapping("register")
    public Result register(@RequestBody Map<String,Object>map){
        String username = map.get("username").toString();
        String password = map.get("password").toString();
        String email = map.get("email").toString();

        if (userService.getUserByName(username) != null)
            return new Result("用户名已存在",400);

        User user = new User();
        user.setUsername(username);user.setPassword(password);
        user.setIsauth(0);user.setDownloadauth(0);user.setEmail(email);
        user.setTime(new Date());user.setPhone(null);
        userService.insertUser(user);
        return new Result("注册成功",200);
    }

    @ApiOperation(value = "登录接口")
    @PostMapping("login")
    public Result login(@RequestBody Map<String, Object> map){
        String username = map.get("username").toString();
        String password = map.get("password").toString();
        if(userService.login(username,password)!=null)
            return new Result("登录成功",200);

        else return new Result("登陆失败",400);
    }

    @ApiOperation(value = "用户信息显示接口")
    @GetMapping("showuserinfo")
    public Result showuserinfo(@RequestParam("username") String username){
        User user = userService.getUserByName(username);
        if(user!=null){
            Result result = new Result();
            result.setCode(200);
            result.setMsg("已显示用户信息");
            //直接放对象是否可行？
            result.getData().put("user", user);
            return result;
        }
        else
            return new Result("显示用户信息失败", 400);
    }

    @ApiOperation(value = "修改用户密码")
    @PostMapping("changeuserinfo/password")
    public Result changeuserpassword(@RequestBody Map<String, Object> map){
        String origin = map.get("password1").toString();
        String password = map.get("password2").toString();
        int userid = Integer.parseInt(map.get("userid").toString());

        if (!origin.equals(password))return new Result("原密码错误", 400);
        User user = userService.getUserById(userid);
        if (user == null)return new Result("用户不存在", 200);

        user.setPassword(password);
        userService.updateUser(user);
        return new Result("修改用户密码成功", 200);
    }

    @ApiOperation(value = "修改用户邮箱")
    @PostMapping("changeuserinfo/email")
    public Result changeuseremail(@RequestBody Map<String, Object> map){
        String email = map.get("password1").toString();
        int userid = Integer.parseInt(map.get("userid").toString());

        User user = userService.getUserById(userid);
        if (user == null)return new Result("用户不存在", 200);

        user.setEmail(email);
        userService.updateUser(user);
        return new Result("修改用户邮箱成功", 200);
    }

}
