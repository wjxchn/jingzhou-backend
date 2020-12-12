package jingzhou.repository;

import jingzhou.MySQLTable.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Integer> {
    Institution findInstitutionByInstituteid(Integer instituteid);
}
