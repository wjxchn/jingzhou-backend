package jingzhou.idclass;

import java.io.Serializable;

public class FollowId implements Serializable {
    private int follower;
    private int researcher;

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getResearcher() {
        return researcher;
    }

    public void setResearcher(int researcher) {
        this.researcher = researcher;
    }

}
