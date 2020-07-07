package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.models.UserFileVO;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@MybatisTest
public class UserFileMapperTests {

    private Logger logger = LoggerFactory.getLogger(FileMapperTests.class);

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserFileMapper userFileMapper;

    @Test
    public void getFileByUsername() {

        String username = "byu00";
        String fileName = "hello-world";

        User user = new User(
                username,
                "1234",
                "1234",
                "Hello",
                "World");

        this.userMapper.insert(user);

        Integer userId = user.getUserid();

        Assertions.assertNotNull(userId);

        File file = new File(
                null,
                fileName,
                "txt",
                "3MB",
                userId,
                null);

        this.fileMapper.insert(file);

        Integer fileId = file.getFileid();

        Assertions.assertNotNull(fileId);

        UserFileVO userFileVO = this.userFileMapper.getFileByUsername(username);

        Assertions.assertNotNull(userFileVO);

        Assertions.assertEquals(userId, userFileVO.getUserId());
        Assertions.assertEquals(fileId, userFileVO.getFileId());
        Assertions.assertEquals(fileName, userFileVO.getFileName());
    }
}
