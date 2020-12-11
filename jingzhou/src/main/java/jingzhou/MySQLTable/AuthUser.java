package jingzhou.MySQLTable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AuthUser {
    @Id
    private String authusername;
    private String institutionid;
    private String achievements;
    private String researchfield;
    private String realname;
    //nosql中的author id字段比较长
    private String authorid;
    private int userid;

    public String getAuthuserid() {
        return authusername;
    }

    public void setAuthuserid(int authuserid) {
        this.authusername = authusername;
    }

    public String getInstitutionid() {
        return institutionid;
    }

    public void setInstitutionid(String institutionid) {
        this.institutionid = institutionid;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public String getResearchfield() {
        return researchfield;
    }

    public void setResearchfield(String researchfield) {
        this.researchfield = researchfield;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

}
