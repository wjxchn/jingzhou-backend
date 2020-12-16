package jingzhou.repository;

import jingzhou.MySQLTable.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser,String> {
    AuthUser findAuthUserByUsername(String username);
    AuthUser findByUserid(int userid);
}
