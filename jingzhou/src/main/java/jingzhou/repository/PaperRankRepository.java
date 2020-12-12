package jingzhou.repository;

import jingzhou.MySQLTable.PaperRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperRankRepository extends JpaRepository<PaperRank, Integer> {

}
