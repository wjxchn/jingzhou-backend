package com.example.jingzhou.Service.ServiceImpl;

import com.example.jingzhou.Dao.UserDao;
import com.example.jingzhou.POJO.User;
import com.example.jingzhou.Service.UserService;
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
