package jingzhou.repository;

import jingzhou.MySQLTable.Follow;
import jingzhou.MySQLTable.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public interface FollowRepository extends JpaRepository<Follow,String> {
    Follow getByFolloweridAndResearcherid(int followerid, int researcherid);
    List<Follow> getFollowsByResearcherid(int researcherid);
    @Transactional
    void removeByFolloweridAndResearcherid(int followerid, int researcherid);
    List<Follow> getFollowsByFollowerid(int follower);

    //获取关注人数
    int countByFollowerid(int follower);


    //学者获取关注者的人数
    int countByResearcherid(int researcher);

    //获取关注者列表
    @Query(value = "SELECT * FROM user,follow where follow.researcherid=researcher and follow.followerid=user.userid", nativeQuery = true)
    List<User> getFollows(@Param("researcher") int researcher);

    //获取关注的学者列表
    @Query(value = "select * from user,follow where follow.followerid=follow and follow.researcherid=user.userid", nativeQuery = true)
    List<User> getResearchers(@Param("follow")int follow);


}
