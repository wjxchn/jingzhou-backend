package jingzhou.MySQLTable;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity @Data
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectid;
    private String journal;
    private String introduction;
    private String type;
    private String projectname;
    private String institution;
    private String researcher;
    private Date time;


}
