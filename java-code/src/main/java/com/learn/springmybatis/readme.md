### 参考资料
[how2j.cn spring mybatis](https://how2j.cn/k/spring-mybatis/spring-mybatis-tutorial/1134.html#nowhere)

### mysql
- windows下mysql启动,mysqld.exe 执行
- 密码配置

  一般mysql的root默认密码为空，如果你之前并没有设置过root密码就使用mysqladmin命令
  ```
  mysqladmin -u root -p password 123456
  mysql -u root -p123456
  ```
- 创库创表
```
CREATE DATABASE IF NOT EXISTS test;

CREATE TABLE IF NOT EXISTS `user`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `name` VARCHAR(100) NOT NULL,
   `title` VARCHAR(40) NOT NULL,
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into user(name, title) values('apple', 'teacher');
```

