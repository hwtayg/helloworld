package com.learn.springmybatis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserService {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = context.getBean(UserDao.class);
        User user = new User();
        user.setId(1);
        user.setName("name1");
        user.setTitle("teacher");
        userDao.insert(user);
    }
}
