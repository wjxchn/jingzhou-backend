//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jingzhou.Service;

import java.util.List;
import jingzhou.MySQLTable.Follow;
import jingzhou.MySQLTable.User;
import jingzhou.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
    @Autowired
    FollowRepository followRepository;

    public FollowService() {
    }

    public void follows(Follow follow) {
        this.followRepository.save(follow);
    }

    public void disfollow(Follow follow) {
        this.followRepository.removeByFolloweridAndResearcherid(follow.getFollowerid(), follow.getResearcherid());
    }

    public List<Follow> getByResearcherID(int researcherid) {
        return this.followRepository.getFollowsByResearcherid(researcherid);
    }

    public List<Follow> getByFollowerID(int followerid) {
        return this.followRepository.getFollowsByFollowerid(followerid);
    }

    public int getFollowNum(int follow) {
        return this.followRepository.countByFollowerid(follow);
    }

    public List<User> getResearcherList(int follow) {
        return this.followRepository.getResearchers(follow);
    }

    public int getFollowerNum(int researcher) {
        return this.followRepository.countByResearcherid(researcher);
    }

    public List<User> getFollowerList(int researcher) {
        return this.followRepository.getFollows(researcher);
    }
}
