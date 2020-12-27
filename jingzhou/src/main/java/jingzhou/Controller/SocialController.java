//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jingzhou.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingzhou.MySQLTable.AuthUser;
import jingzhou.MySQLTable.Follow;
import jingzhou.MySQLTable.Message;
import jingzhou.MySQLTable.User;
import jingzhou.POJO.Result;
import jingzhou.Service.AuthUserService;
import jingzhou.Service.FollowService;
import jingzhou.Service.MessageService;
import jingzhou.Service.UserService;
import jingzhou.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api("社交子系统")
@RestController
@RequestMapping({"/community/"})
public class SocialController {
    @Autowired
    private FollowService followService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private AuthUserService authUserservice;
    @Autowired
    private UserService userService;
    @Autowired
    private FollowRepository followRepository;

    @ApiOperation("关注接口")
    @GetMapping({"follow"})
    public Result follow(@RequestParam("followername") String followername, @RequestParam("researchername") String researchername) {
        AuthUser authuser = authUserservice.getAuthUserByRealname(researchername);
        User user = userService.getUserByName(followername);
        if(authuser == null || user == null){
            return new Result("error", 400);
        }
        int follower = user.getUserid();
        int researcher = authuser.getUserid();
        Follow follow = new Follow(follower, researcher);
        this.followService.follows(follow);

        Message message = new Message();
        message.setSenderusername("GuanZhu");
        message.setReceiverusername(researchername);
        message.setContent("您收到了来自 "+researchername+" 的关注");
        messageService.insertMessage(message);

        return new Result("关注成功", 200);
    }
/*
    @ApiOperation("生成消息")
    @PostMapping({"generatemessage"})
    public Result generatemessage(@RequestBody Map<String, Object> map) {
        int sender = Integer.parseInt(map.get("sender").toString());
        String content = map.get("content").toString();
        List<Follow> followList = this.followService.getByResearcherID(sender);
        Iterator<Follow> followIterator = followList.iterator();
        ArrayList messageList = new ArrayList();

        while(followIterator.hasNext()) {
            Message message = new Message();
            message.setSenderid(sender);
            message.setReceiverid(((Follow)followIterator.next()).getFollowerid());
            message.setContent(content);
            messageList.add(message);
        }

        this.messageService.insertMessageList(messageList);
        return new Result("发布消息成功", 200);
    }
*/
    @ApiOperation("接收动态")
    @GetMapping({"receivemessage"})
    public Result receivemessage(@RequestParam("receiver") String receiver) {
        Result result = new Result("查看消息成功", 200);
        List<Message> messages = this.messageService.getMessagesByReceiver(receiver);
        result.getData().put("message", messages);
        return result;
    }

    @ApiOperation("关注人数")
    @GetMapping("follow/num")
    public Result followNum(@RequestParam("userid") int follower) {
        Result result = new Result("查看关注成功", 200);
        int num = this.followService.getFollowNum(follower);
        result.getData().put("cnt", num);
        return result;
    }

    @ApiOperation("被关注人数")
    @GetMapping("follower/num")
    public Result followerNum(@RequestParam("userid") int follow) {
        Result result = new Result("返回人数", 200);
        int num = this.followService.getFollowerNum(follow);
        result.getData().put("cnt", num);
        return result;
    }

    @ApiOperation("我的关注人数")
    @GetMapping("follow/num/self")
    public Result followNumSelf(@RequestParam("username") String username) {
        Result result = new Result("查看关注成功", 200);
        int id = this.userService.getUserByName(username).getUserid();
        int num = this.followService.getFollowNum(id);
        result.getData().put("cnt", num);
        return result;
    }

    @ApiOperation("我的被关注人数")
    @GetMapping("follower/num/self")
    public Result followerNumSelf(@RequestParam("username") String username) {
        Result result = new Result("返回人数", 200);
        int id = this.userService.getUserByName(username).getUserid();
        int num = this.followService.getFollowerNum(id);
        result.getData().put("cnt", num);
        return result;
    }

    @ApiOperation("关注列表")
    @GetMapping("follow/list")
    public Result myFollow(@RequestParam("userid") int follow) {
        Result result = new Result("查看消息成功", 200);
        List<User> userList = this.followService.getResearcherList(follow);
        result.getData().put("researcherlist", userList);
        return result;
    }

    @ApiOperation("被关注列表")
    @GetMapping("follower/list")
    public Result myResearchers(@RequestParam("userid") int follow) {
        Result result = new Result("查看消息成功", 200);
        List<User> userList = this.followService.getFollowerList(follow);
        result.getData().put("followerlist", userList);
        return result;
    }

    @ApiOperation(value = "取消关注接口")
    @GetMapping("cancelfollow")
    public Result cancelfollow(@RequestParam("followername") String followername, @RequestParam("researchername") String researchername)
    {
        AuthUser authuser = authUserservice.getAuthUserByRealname(researchername);
        User user = userService.getUserByName(followername);
        if(authuser == null || user == null){
            return new Result("error", 400);
        }
        int follower = user.getUserid();
        int researcher = authuser.getUserid();
        Follow follow = new Follow(follower,researcher);
        followService.disfollow(follow);
        return new Result("取消关注成功", 200);
    }

    @ApiOperation(value = "判断是否已关注接口")
    @GetMapping("isfollow")
    public Result isfollow(@RequestParam("followername") String followername, @RequestParam("researchername") String researchername)
    {
        AuthUser authuser = authUserservice.getAuthUserByUsername(researchername);
        if(authuser != null){
            User user = userService.getUserByName(followername);
            if(user == null){
                return new Result("发生错误", 404);
            }
            int follower = user.getUserid();
            int researcher = authuser.getUserid();
            Follow follow = followRepository.getByFolloweridAndResearcherid(follower, researcher);
            if(follow != null){
                return new Result("已关注", 200);
            }
            else return new Result("未关注", 402);
        }
        return new Result("未认证", 401);
    }

    @ApiOperation(value = "获取个人主页数据")
    @PostMapping("getpersonalinfo")
    public Result getpersonalinfo(@RequestBody Map<String, Object> map)
    {
        String username = map.get("username").toString();
        AuthUser authuser = authUserservice.getAuthUserByUsername(username);

        Result result = new Result("获取成功",200);
        result.getData().put("user",authuser);
        return result;
    }

    @ApiOperation(value = "获取收到的关注消息")
    @PostMapping("getfollowmessage")
    public Result getfollowmessage(@RequestParam("receivername") String receivername)
    {
        List<Message> mlist = messageService.getMessagesBySenderAndReceiver("GuanZhu",receivername);

        Result result = new Result("获取成功",200);
        result.getData().put("messages",mlist);
        return result;
    }

}
