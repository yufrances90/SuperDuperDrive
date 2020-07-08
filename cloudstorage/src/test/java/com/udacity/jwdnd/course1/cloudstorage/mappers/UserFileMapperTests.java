package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.models.UserFileVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@MybatisTest
public class UserFileMapperTests {

    public final static String USERNAME = "byu00";
    public final static String FILE_NAME = "hello-world";

    private Integer userId;
    private Integer fileId;

    private Logger logger = LoggerFactory.getLogger(FileMapperTests.class);

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserFileMapper userFileMapper;

    @Before
    public void before() {

        User user = new User(
                USERNAME,
                "1234",
                "1234",
                "Hello",
                "World");

        this.userMapper.insert(user);

        this.userId = user.getUserid();

        File file = new File(
                null,
                FILE_NAME,
                "txt",
                "3MB",
                this.userId,
                null);

        this.fileMapper.insert(file);

        this.fileId = file.getFileid();
    }

    @Test
    public void getFileByUsername() {

        Assertions.assertNotNull(this.userId);

        Assertions.assertNotNull(this.fileId);

        List<UserFileVO> userFileVOList = this.userFileMapper.getFileByUsername(USERNAME);

        Assertions.assertFalse(userFileVOList.isEmpty());

        UserFileVO userFileVO = userFileVOList.get(0);

        Assertions.assertNotNull(userFileVO);

        Assertions.assertEquals(userId, userFileVO.getUserId());
        Assertions.assertEquals(fileId, userFileVO.getFileId());
        Assertions.assertEquals(FILE_NAME, userFileVO.getFileName());
    }

    @Test
    public void getFileByUsernameAndFileName() {

        Map<String, Object> paraMap = new HashMap<>();

        paraMap.put("username", USERNAME);
        paraMap.put("filename", FILE_NAME);

        List<UserFileVO> userFileVOList =
                this.userFileMapper.getFileByUsernameAndFileName(paraMap);

        Assertions.assertFalse(userFileVOList.isEmpty());

        UserFileVO userFileVO = userFileVOList.get(0);

        Assertions.assertNotNull(userFileVO);

        Assertions.assertEquals(userId, userFileVO.getUserId());
        Assertions.assertEquals(fileId, userFileVO.getFileId());
        Assertions.assertEquals(FILE_NAME, userFileVO.getFileName());
    }
}
