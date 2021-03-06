package jingzhou.repository;

import jingzhou.MySQLTable.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findUserByUsernameAndPassword(String username,String password);
    User findUserByUsername(String username);
    User findUserByUserid(int userid);
}
