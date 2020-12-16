package jingzhou.MySQLTable;


import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int followid;

    private int followerid;

    private int researcherid;

    public Follow(){}
    public Follow(int follow, int researcher){
        followerid=follow;
        researcherid=researcher;
    }
}
