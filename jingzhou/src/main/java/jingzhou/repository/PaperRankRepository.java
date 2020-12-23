package jingzhou.repository;

import jingzhou.MySQLTable.PaperRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperRankRepository extends JpaRepository<PaperRank, Integer> {
    List<PaperRank> findAll();
}
