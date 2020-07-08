package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.UserNoteVO;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@MybatisTest
public class UserNoteMapperTests {

    @Autowired
    private UserNoteMapper userNoteMapper;

    @Test
    public void getNotesByUsername() {

        List<UserNoteVO> userNoteVOList = this.userNoteMapper.getNotesByUsername("byu00");

        Assertions.assertTrue(userNoteVOList.isEmpty());
    }
}
