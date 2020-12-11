package jingzhou.MySQLTable;

import jingzhou.idclass.FollowId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;


@Entity
@IdClass(FollowId.class)
public class Follow {
    @Id
    private String follower;

    @Id
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
