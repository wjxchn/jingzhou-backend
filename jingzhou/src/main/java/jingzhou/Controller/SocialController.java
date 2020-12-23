//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jingzhou.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jingzhou.MySQLTable.Follow;
import jingzhou.MySQLTable.Message;
import jingzhou.MySQLTable.User;
import jingzhou.POJO.Result;
import jingzhou.Service.FollowService;
import jingzhou.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("社交子系统")
@RestController
@RequestMapping({"/community/"})
public class SocialController {
    @Autowired
    private FollowService followService;
    @Autowired
    private MessageService messageService;

    public SocialController() {
    }

    @ApiOperation("关注接口")
    @PostMapping({"follow"})
    public Result follow(@RequestBody Map<String, Object> map) {
        int follower = Integer.parseInt(map.get("followerid").toString());
        int researcher = Integer.parseInt(map.get("researcherid").toString());
        Follow follow = new Follow(follower, researcher);
        this.followService.follows(follow);
        return new Result("关注成功", 200);
    }

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

    @ApiOperation("接收动态")
    @GetMapping({"receivemessage"})
    public Result receivemessage(@RequestParam("receiver") int receiver) {
        Result result = new Result("查看消息成功", 200);
        List<Message> messages = this.messageService.getMessagesByReceiver(receiver);
        result.getData().put("message", messages);
        return result;
    }

    @ApiOperation("关注人数")
    @GetMapping({"follow/num"})
    public Result followNum(@RequestParam("userid") int follower) {
        Result result = new Result("查看关注成功", 200);
        int num = this.followService.getFollowNum(follower);
        result.getData().put("cnt", num);
        return result;
    }

    @ApiOperation("被关注人数")
    @GetMapping({"follower/num"})
    public Result followerNum(@RequestParam("userid") int follow) {
        Result result = new Result("返回人数", 200);
        int num = this.followService.getFollowerNum(follow);
        result.getData().put("cnt", num);
        return result;
    }

    @ApiOperation("关注列表")
    @GetMapping({"follow/list"})
    public Result myFollow(@RequestParam("userid") int follow) {
        Result result = new Result("查看消息成功", 200);
        List<User> userList = this.followService.getResearcherList(follow);
        result.getData().put("researcherlist", userList);
        return result;
    }

    @ApiOperation("被关注列表")
    @GetMapping({"follower/list"})
    public Result myResearchers(@RequestParam("userid") int follow) {
        Result result = new Result("查看消息成功", 200);
        List<User> userList = this.followService.getFollowerList(follow);
        result.getData().put("followerlist", userList);
        return result;
    }
}
