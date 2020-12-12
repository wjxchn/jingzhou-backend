package jingzhou.MySQLTable;

import jingzhou.idclass.InstitutionRankId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(InstitutionRankId.class)
public class InstitutionRank {
    @Id
    private Integer institution;
    @Id
    private String type;
    int amount;

    public Integer getInstitution() {
        return institution;
    }

    public void setInstitution(Integer institution) {
        this.institution = institution;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
