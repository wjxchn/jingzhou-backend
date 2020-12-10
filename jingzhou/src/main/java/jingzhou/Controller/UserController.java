package jingzhou.Controller;

import jingzhou.MySQLTable.Follow;
import jingzhou.MySQLTable.User;
import jingzhou.repository.FollowRepository;
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

    @Autowired
    private FollowRepository followRepository;

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


    /**关注相关的接口**/
    @ApiOperation(value = "关注用户")
    @PostMapping("follow")
    public Map<String,Object> follow(@RequestParam("followername") String followername, @RequestParam("researchername") String researchername){
        HashMap<String,Object> result = new HashMap<>();
        User follower = userRepository.findUserByUsername(followername);
        User researcher = userRepository.findUserByUsername(researchername);
        if (followRepository.findFollowByFollowerAndAndResearcher(follower.getId(),researcher.getId()) == null){
            Follow follow = new Follow();
            follow.setFollower(follower.getId());
            follow.setResearcher(researcher.getId());
            followRepository.save(follow);

            result.put("code",200);
            result.put("msg","关注成功");
            return result;
        }
        else {
            result.put("code",400);
            result.put("msg","关注失败");
            return result;
        }
    }

    @ApiOperation(value = "取消关注用户")
    @PostMapping("unfollow")
    public Map<String,Object> unfollow(@RequestParam("followername") String followername, @RequestParam("researchername") String researchername){
        HashMap<String,Object> result = new HashMap<>();
        User follower = userRepository.findUserByUsername(followername);
        User researcher = userRepository.findUserByUsername(researchername);
        Follow follow = followRepository.findFollowByFollowerAndAndResearcher(follower.getId(),researcher.getId());
        if (follow != null){
            followRepository.delete(follow);

            result.put("code",200);
            result.put("msg","取消关注成功");
            return result;
        }
        else {
            result.put("code",400);
            result.put("msg","取消关注失败");
            return result;
        }
    }







    @ApiOperation(value = "获取用户", notes = "根据用户ID获取用户注册时间")
    @GetMapping("find_user_register_time/{id}")
    public String findUserById(@PathVariable("id") Integer id){
        return userRepository.findUserById((int)id).getTime().toString();
    }

}
