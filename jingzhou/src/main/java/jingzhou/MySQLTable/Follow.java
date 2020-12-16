package jingzhou.MySQLTable;


import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "follow")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int followid;

    private int followerid;

    private int researcherid;
}
