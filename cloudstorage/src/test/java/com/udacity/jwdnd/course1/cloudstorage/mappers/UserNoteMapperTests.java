package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.models.UserNoteVO;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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

    private Logger logger = LoggerFactory.getLogger(UserNoteMapperTests.class);

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserNoteMapper userNoteMapper;

    @Test
    public void getNotesByUsername() {

        String username = "byu00";
        String noteTitle = "hello-world";

        User user = new User(
                username,
                "1234",
                "1234",
                "Hello",
                "World");

        this.userMapper.insert(user);

        Integer userId = user.getUserid();

        Assertions.assertNotNull(userId);

        Note note = new Note(
                null,
                noteTitle,
                "hello test",
                userId);

        Note noteWithoutUser = new Note(
                null,
                noteTitle,
                "hello test",
                null);

        this.noteMapper.insert(note);
        this.noteMapper.insert(noteWithoutUser);

        Integer noteId = note.getNoteid();

        Assertions.assertNotNull(noteId);

        Note savedNote = this.noteMapper.getNoteById(noteId);

        Assertions.assertEquals(userId, savedNote.getUserid());

        List<UserNoteVO> userNoteVOList = this.userNoteMapper.getNotesByUsername("byu00");

        Assertions.assertFalse(userNoteVOList.isEmpty());
        Assertions.assertEquals(1, userNoteVOList.size());

        UserNoteVO userNoteVO = userNoteVOList.get(0);

        Assertions.assertNotNull(userNoteVO);

        Assertions.assertEquals(userId, userNoteVO.getUserId());
        Assertions.assertEquals(noteId, userNoteVO.getNoteId());
        Assertions.assertEquals(noteTitle, userNoteVO.getNoteTitle());
    }

    @Test
    public void insertNoteByUsername() {

        String username = "byu00";
        String noteTitle = "hello";

        User user = new User(
                username,
                "1234",
                "1234",
                "Hello",
                "World");

        this.userMapper.insert(user);

        Integer userId = user.getUserid();

        Assertions.assertNotNull(userId);

        this.userNoteMapper.insertNoteByUsername(username, noteTitle, "world");

        List<UserNoteVO> userNoteVOList = this.userNoteMapper.getNotesByUsername(username);

        Assertions.assertFalse(userNoteVOList.isEmpty());

        UserNoteVO userNoteVO = userNoteVOList.get(0);

        Assertions.assertEquals(userId, userNoteVO.getUserId());
        Assertions.assertEquals(noteTitle, userNoteVO.getNoteTitle());
    }
}
