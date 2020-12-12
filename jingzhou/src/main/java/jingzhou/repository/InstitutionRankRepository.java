package jingzhou.repository;

import jingzhou.MySQLTable.InstitutionRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionRankRepository extends JpaRepository<InstitutionRank, Integer> {
    List<InstitutionRank> findAllByType(String type);
}
