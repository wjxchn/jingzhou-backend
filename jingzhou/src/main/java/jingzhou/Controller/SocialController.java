package jingzhou.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingzhou.MySQLTable.Follow;
import jingzhou.MySQLTable.Message;
import jingzhou.MySQLTable.User;
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

    @ApiOperation(value = "关注人数")
    @GetMapping("follow/num")
    public Result followNum(@RequestParam("userid") int follower){
        Result result = new Result("查看关注成功", 200);
        //关注的人的人数
        int num = followService.getFollowNum(follower);
        result.getData().put("cnt",num);
        return result;
    }

    @ApiOperation(value = "被关注人数")
    @GetMapping("follower/num")
    public Result followerNum(@RequestParam("userid") int follow){
        Result result = new Result("返回人数", 200);
        //粉丝人数
        int num = followService.getFollowerNum(follow);
        result.getData().put("cnt",num);
        return result;
    }

    @ApiOperation(value = "关注列表")
    @GetMapping("follow/list")
    public Result myFollow(@RequestParam("userid") int follow){
        Result result = new Result("查看消息成功", 200);
        //user关注的列表
        List<User> userList = followService.getResearcherList(follow);
        result.getData().put("researcherlist",userList);
        return result;
    }

    @ApiOperation(value = "被关注列表")
    @GetMapping("follower/list")
    public Result myResearchers(@RequestParam("userid") int follow){
        Result result = new Result("查看消息成功", 200);
        //获取关注我的人
        List<User> userList = followService.getFollowerList(follow);
        result.getData().put("followerlist",userList);
        return result;
    }


}
