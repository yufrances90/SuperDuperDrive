package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS")
    List<User> getAllUsers();

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUserByUsername(String username);

    @Select("SELECT * FROM USERS WHERE userid = #{userid}")
    User getUserById(Integer userid);

    @Insert("INSERT INTO USERS(username,salt,password,firstname,lastname) VALUES (" +
            "#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    int insert(User user);

    @Delete("DELETE FROM USERS WHERE userid = #{userid}")
    void delete(Integer userid);
}
