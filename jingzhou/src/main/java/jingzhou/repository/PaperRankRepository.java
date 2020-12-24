package jingzhou.repository;

import jingzhou.MySQLTable.Paperrank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperRankRepository extends JpaRepository<Paperrank, Integer> {
    findPaperrankByPaperid(int paperid);

}
