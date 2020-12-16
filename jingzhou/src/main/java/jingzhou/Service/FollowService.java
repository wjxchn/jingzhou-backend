package jingzhou.Service;

import jingzhou.MySQLTable.Follow;
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
}
