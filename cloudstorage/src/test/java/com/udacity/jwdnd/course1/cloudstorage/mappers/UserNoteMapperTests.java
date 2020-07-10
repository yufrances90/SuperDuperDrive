package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.models.UserNoteVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@MybatisTest
public class UserNoteMapperTests {

    public static final String USERNAME = "byu00";
    public static final String NOTE_TITLE = "hello-world";
    public static final String NOTE_TITLE_I = "hello world";
    public static final String NOTE_DESCRIPTION = "world";
    public static final String NOTE_DESCRIPTION_I = "world-3";

    private Logger logger = LoggerFactory.getLogger(UserNoteMapperTests.class);

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserNoteMapper userNoteMapper;

    @Before
    public void beforeAll() {

        User user = new User(
                USERNAME,
                "1234",
                "1234",
                "Hello",
                "World");

        this.userMapper.insert(user);

        this.userNoteMapper.insertNoteByUsername(USERNAME, NOTE_TITLE, NOTE_DESCRIPTION);
    }

    @Test
    public void insertNoteByUsername() {

        User user = this.userMapper.getUserByUsername(USERNAME);

        List<UserNoteVO> userNoteVOList = this.userNoteMapper.getNotesByUsername(USERNAME);

        Assertions.assertFalse(userNoteVOList.isEmpty());

        UserNoteVO userNoteVO = userNoteVOList.get(0);

        Assertions.assertEquals(NOTE_TITLE, userNoteVO.getNoteTitle());
        Assertions.assertEquals(NOTE_DESCRIPTION, userNoteVO.getNoteDescription());
        Assertions.assertEquals(user.getUserid(), userNoteVO.getUserId());
    }
}
