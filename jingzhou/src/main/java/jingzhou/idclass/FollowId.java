package jingzhou.idclass;

import java.io.Serializable;

public class FollowId implements Serializable {
    private String follower;
    private String researcher;

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getResearcher() {
        return researcher;
    }

    public void setResearcher(String researcher) {
        this.researcher = researcher;
    }

}
