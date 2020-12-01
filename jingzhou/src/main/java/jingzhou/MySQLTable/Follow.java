package jingzhou.MySQLTable;

import jingzhou.idclass.FollowId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;


@Entity
@IdClass(FollowId.class)
public class Follow {
    @Id
    private int follower;

    @Id
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
