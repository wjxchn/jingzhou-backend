package jingzhou.MySQLTable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Patent {
    @Id
    Integer patentid;
    String inventor;
    Date pubdate;
    String name;
    String pubnumber;
    String patentnumber;
    String applicant;
    String address;
    String classification;
    String abstractcontent;
    Date appdate;

    public Integer getPatentid() {
        return patentid;
    }

    public void setPatentid(Integer patentid) {
        this.patentid = patentid;
    }

    public String getInventor() {
        return inventor;
    }

    public void setInventor(String inventor) {
        this.inventor = inventor;
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPubnumber() {
        return pubnumber;
    }

    public void setPubnumber(String pubnumber) {
        this.pubnumber = pubnumber;
    }

    public String getPatentnumber() {
        return patentnumber;
    }

    public void setPatentnumber(String patentnumber) {
        this.patentnumber = patentnumber;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getAbstractcontent() {
        return abstractcontent;
    }

    public void setAbstractcontent(String abstractcontent) {
        this.abstractcontent = abstractcontent;
    }

    public Date getAppdate() {
        return appdate;
    }

    public void setAppdate(Date appdate) {
        this.appdate = appdate;
    }

}
