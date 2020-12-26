package jingzhou.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingzhou.MySQLTable.User;
import jingzhou.POJO.Result;
import jingzhou.Service.SessionService;
import jingzhou.Service.UserService;
import jingzhou.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Api(value = "个人信息管理子系统")
@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

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
        user.setPic("/root/accessory/pic.jpg");
        userService.insertUser(user);
        return new Result("注册成功",200);
    }

    @ApiOperation(value = "登录接口")
    @PostMapping("login")
    public Result login(@RequestBody Map<String, Object> map, HttpServletRequest request){
        String username = map.get("username").toString();
        String password = map.get("password").toString();
        User user = userService.login(username,password);
        if(user != null){
            sessionService.setCurrentUser(request, user);
            Result res = new Result("登录成功",200);
            res.getData().put("user",user);
            return res;
        }
        else return new Result("登陆失败",400);
    }

    @ApiOperation(value = "登出接口")
    @PostMapping("logout")
    public Result logout(HttpServletRequest request){
        sessionService.removeCurrentUser(request);
        return new Result("登出成功", 200);
    }

    @ApiOperation(value = "用户信息显示接口")
    @GetMapping("showuserinfo")
    public Result showuserinfo(HttpServletRequest request){
        User user = sessionService.getCurrentUser(request);
        if(user!=null){
            Result result = new Result("已显示用户信息", 200);
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
    public Result changeuseremail(@RequestBody Map<String, Object> map, HttpServletRequest request){
        String email = map.get("email").toString();
        User user = sessionService.getCurrentUser(request);
        if (user == null)return new Result("用户不存在", 200);

        user.setEmail(email);
        userService.updateUser(user);
        return new Result("修改用户邮箱成功", 200);
    }

    @ApiOperation(value = "修改用户头像")
    @PostMapping("changeuserinfo/Userpic")
    public Result changeuserpic(@RequestBody Map<String, Object> map, HttpServletRequest request){
        MultipartFile userpic =(MultipartFile) map.get("userpic");
        int userid = Integer.parseInt(map.get("userid").toString());

        User user1 = sessionService.getCurrentUser(request);
        if (user1.getUserid() != userid){
            return new Result("非当前登录用户", 400);
        }
        User user = userService.getUserById(userid);
        if (user == null)return new Result("用户不存在", 200);

        //文件上传
        if (!userpic.isEmpty()) {
            //图片命名
            String filename = userpic.getOriginalFilename();
            String suffixName = filename.substring(filename.lastIndexOf("."));  // 后缀名
            String path = "/root/accessory/";//图片要上传到哪个文件夹，部署时注意
            //String path = "/img/";
            //String path ="/static/img/";
            filename = UUID.randomUUID() + suffixName;// 新文件名

            // 新建文件
            File filepath = new File(path, filename);
            // 判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            try {
                // 写入文件
                //file.transferTo(new File(path + File.separator + filename));
                userpic.transferTo(filepath);
                /*Thumbnails
                        .of("F:"+path + filename)
                        .size(150, 150)
                        .outputQuality(1.0f)
                        .keepAspectRatio(false)
                        .toFile("F:"+path + filename);压缩图片用*/
            } catch ( IOException e) {
                e.printStackTrace();
                return new Result("上传失败", 400);
            }
            System.out.println(path+filename);
            user.setPic(path+filename);
            userService.updateUser(user);
            return new Result("修改头像成功", 200);
        }
        return new Result("图片为空！", 400);

    }

}
