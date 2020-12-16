package jingzhou.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jingzhou.MySQLTable.Follow;
import jingzhou.MySQLTable.Message;
import jingzhou.repository.FollowRepository;
import jingzhou.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(value = "社交子系统")
@RestController
public class SocialController {
    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private MessageRepository messageRepository;

    @ApiOperation(value = "关注接口")
    @PostMapping("follow")
    public Map<String, Object> follow(@RequestParam("followerusername") String follower, @RequestParam("researchersuername") String researcher){
        HashMap<String,Object> result = new HashMap<>();
        Follow followobject = new Follow();
        followobject.setFollowerid();
        followobject.setResearcherid();
        followRepository.save(followobject);
        result.put("code",200);
        result.put("msg","关注成功");
        return result;
    }

    @ApiOperation(value = "生成动态")
    @PostMapping("generatemessage")
    public Map<String, Object> generatemessage(@RequestParam("sender") String sender, @RequestParam("content") String content){
        HashMap<String,Object> result = new HashMap<>();
        List<Follow> followList = followRepository.getFollowsByResearcher(sender);
        Iterator<Follow> followIterator = followList.iterator();
        while(followIterator.hasNext()){
            Message message = new Message();
            message.setMessageid(messageRepository.getMaxId()+1);
            message.setSender(sender);
            message.setReceiver(followIterator.next().getFollower());
            message.setContent(content);
            messageRepository.save(message);
        }
        result.put("code",200);
        result.put("msg","生成动态成功");
        return result;
    }

    @ApiOperation(value = "接收动态")
    @GetMapping("receivemessage/{receiver}")
    public Map<String, Object> receivemessage(@PathVariable("receiver") String receiver){
        HashMap<String, Object> result = new HashMap<>();
        List<Message> messages = messageRepository.findMessagesByReceiver(receiver);
        result.put("code", 200);
        result.put("msg","接收动态成功");
        result.put("message",messages);
        return result;
    }

}
