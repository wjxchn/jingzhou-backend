package jingzhou.Service.ServiceImpl;

import jingzhou.Dao.UserDao;
import jingzhou.POJO.User;
import jingzhou.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;


    @Override
    public List<User> allUser() {
        return userDao.listUser();
    }
}
