package jingzhou.repository;

import jingzhou.MySQLTable.Follow;
import jingzhou.MySQLTable.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface FollowRepository extends JpaRepository<Follow,Long> {
    Follow findFollowByFollowerAndAndResearcher(int follower, int researcher);
}
