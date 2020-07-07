package com.udacity.jwdnd.course1.cloudstorage.mappers;

import org.junit.Test;
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
    private UserFileMapper userFileMapper;

    @Test
    public void test() {
        this.userFileMapper.selectByFileId(1);
    }
}
