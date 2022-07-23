package com.learn.springmybatis;

import org.apache.ibatis.annotations.Insert;

public interface UserDao {

    @Insert(" insert into user ( id, name, title ) values (#{id},#{name}, #{title}) ")
    public int insert(User user);
}
