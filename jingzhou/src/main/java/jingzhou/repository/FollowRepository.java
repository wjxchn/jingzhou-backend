package jingzhou.repository;

import jingzhou.MySQLTable.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow,String> {
    Follow getByFolloweridAndResearcherid(int followerid, int researcherid);
    List<Follow> getFollowsByResearcherid(int researcherid);
}
