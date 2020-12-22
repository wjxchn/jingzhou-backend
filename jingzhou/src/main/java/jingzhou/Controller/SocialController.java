package jingzhou.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingzhou.MySQLTable.Follow;
import jingzhou.MySQLTable.Message;
import jingzhou.POJO.Result;
import jingzhou.Service.FollowService;
import jingzhou.Service.MessageService;
import jingzhou.repository.FollowRepository;
import jingzhou.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(value = "社交子系统")
@RestController
@RequestMapping("/community/")
public class SocialController {
    @Autowired
    private FollowService followService;

    @Autowired
    private MessageService messageService;

    @ApiOperation(value = "关注接口")
    @PostMapping("follow")
    public Result follow(@RequestBody Map<String, Object> map){
        int follower = Integer.parseInt(map.get("followerid").toString());
        int researcher = Integer.parseInt(map.get("researcherid").toString());
        Follow follow = new Follow(follower,researcher);
        followService.follows(follow);
        return new Result("关注成功", 200);
    }

    @ApiOperation(value = "取消关注接口")
    @PostMapping("cancelfollow")
    public Result cancelfollow(@RequestBody Map<String, Object> map)
    {
        int followername = Integer.parseInt(map.get("followerid").toString());
        int researcher = Integer.parseInt(map.get("researcherid").toString());
        Follow follow = new Follow(follower,researcher);
        followService.disfollows(follow);
        return new Result("关注成功", 200);
    }

    @ApiOperation(value = "判断是否已关注接口")
    @PostMapping("isfollow")
    public Result isfollow(@RequestBody Map<String, Object> map)
    {
        int followername = Integer.parseInt(map.get("followerid").toString());
        int researcher = Integer.parseInt(map.get("researcherid").toString());
        Follow follow = new Follow(follower,researcher);
        if(follow != null){
            return new Result("已关注", 1);
        }
        else return new Result("已关注", 0);
    }

    @ApiOperation(value = "获取个人主页数据")
    @PostMapping("getpersonalinfo")
    public Result getpersonalinfo(@RequestBody Map<String, Object> map)
    {
        int id = Integer.parseInt(map.get("userid").toString()),
        AuthUser user = AuthUserService.getAuthUserByUserID(id);

        Result result = new Result("获取成功",200);
        result.getData().put("user",user);
    }

    @ApiOperation(value = "生成消息")
    @PostMapping("generatemessage")
    public Result generatemessage(@RequestBody Map<String,Object> map){
        int sender = Integer.parseInt(map.get("sender").toString());
        String content = map.get("content").toString();
        //作者发布消息，对所有的followers都操作
        List<Follow> followList = followService.getByResearcherID(sender);
        Iterator<Follow> followIterator = followList.iterator();
        List<Message> messageList = new ArrayList<>();
        while(followIterator.hasNext()){
            Message message = new Message();
            message.setSenderid(sender);
            message.setReceiverid(followIterator.next().getFollowerid());
            message.setContent(content);
            //一条一条插入数据会增大数据库压力，采用批量插入
            messageList.add(message);
        }
        messageService.insertMessageList(messageList);
        return new Result("发布消息成功", 200);
    }

    @ApiOperation(value = "接收动态")
    @GetMapping("receivemessage")
    public Result receivemessage(@RequestParam("receiver") int receiver){
        Result result = new Result("查看消息成功", 200);
        List<Message> messages = messageService.getMessagesByReceiver(receiver);

        result.getData().put("message",messages);
        return result;
    }

}
