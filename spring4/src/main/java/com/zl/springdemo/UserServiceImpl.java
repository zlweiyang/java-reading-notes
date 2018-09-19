package com.zl.springdemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("myUserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

//    public UserDao getUserDao() {
//        return userDao;
//    }
//
//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
}
