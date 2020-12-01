package jingzhou.MySQLTable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Portal {
    @Id
    private int portalid;
    private int authorid;
    private String researchername;

    public int getPortalid() {
        return portalid;
    }

    public void setPortalid(int portalid) {
        this.portalid = portalid;
    }

    public int getAuthorid() {
        return authorid;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public String getResearchername() {
        return researchername;
    }

    public void setResearchername(String researchername) {
        this.researchername = researchername;
    }

}
