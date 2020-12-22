package jingzhou.repository;

import jingzhou.MySQLTable.Follow;
import jingzhou.MySQLTable.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FollowRepository extends JpaRepository<Follow,String> {
    Follow getByFolloweridAndResearcherid(int followerid, int researcherid);
    List<Follow> getFollowsByResearcherid(int researcherid);
    void removeByFolloweridAndResearcherid(int followerid, int researcherid);
    List<Follow> getFollowsByFollowerid(int follower);

    //获取关注人数
    @Select("select count(*) from follow where followerid=#{follower}")
    int getFollowNum(int follower);


    //学者获取关注者的人数
    @Select("select count(*) from follow where researcherid = #{researcher}")
    int getFollowerNum(int researcher);

    //获取关注者列表
    @Select("select * from user,follow where follow.researcherid=#{researcher} and follow.followerid=user.userid")
    List<User> getFollows(int researcher);

    //获取关注的学者列表
    @Select("select * from user,follow where follow.followerid=#{follow} and follow.researcherid=user.userid")
    List<User> getResearchers(int follow);
}
