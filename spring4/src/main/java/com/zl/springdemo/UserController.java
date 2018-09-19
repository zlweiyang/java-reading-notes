package com.zl.springdemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    //自动装载使用byName的方式，需要结合@Qualifier注解一起使用
    @Autowired()
    @Qualifier("myUserService")
    private UserService userService;

//    public UserService getUserService() {
//        return userService;
//    }
//
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    public User getUserById(int id){
        return userService.getUserById(id);
    }
}
