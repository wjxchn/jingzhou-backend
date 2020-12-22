package jingzhou.Service;

import jingzhou.MySQLTable.Follow;
import jingzhou.MySQLTable.User;
import jingzhou.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {

    @Autowired
    FollowRepository followRepository;

    //添加一条记录
    public void follows(Follow follow) {
        followRepository.save(follow);
    }

    public void disfollow(Follow follow) {
        followRepository.removeByFolloweridAndResearcherid(follow.getFollowerid(), follow.getResearcherid());
    }

    public List<Follow> getByResearcherID(int researcherid) {
        return followRepository.getFollowsByResearcherid(researcherid);
    }

    public List<Follow> getByFollowerID(int followerid) {
        return followRepository.getFollowsByFollowerid(followerid);
    }

    //获取我关注的人数
    public int getFollowNum(int follow){
        return followRepository.getFollowNum(follow);
    }

    //我关注的列表
    public List<User> getResearcherList(int follow){
        return followRepository.getResearchers(follow);
    }

    //被关注的人数
    public int getFollowerNum(int researcher){
        return followRepository.getFollowerNum(researcher);
    }

    public List<User> getFollowerList(int researcher){
        return followRepository.getFollows(researcher);
    }
}
