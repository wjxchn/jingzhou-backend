package jingzhou.MySQLTable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AuthUser {
    //这里的username和user表中的username相同
    @Id
    private String username;
    private String institutionid;
    private String achievements;
    private String researchfield;
    private String realname;
    //nosql中的author id字段比较长
    private String authorid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

}
