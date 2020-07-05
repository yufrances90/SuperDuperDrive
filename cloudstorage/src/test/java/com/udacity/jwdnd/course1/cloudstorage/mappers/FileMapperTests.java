package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.CloudStorageApplication;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(CloudStorageApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileMapperTests {

    private Logger logger = LoggerFactory.getLogger(FileMapperTests.class);

    @Autowired
    private FileMapper fileMapper;

    @Test
    public void insertFile() {

        File newFile = new File(
                null,
                "hello",
                "txt",
                "3GB",
                null
        );

        this.fileMapper.insert(newFile);

        Integer fileId = newFile.getFileid();

        Assertions.assertNotNull(fileId);

        File file = this.fileMapper.getFileById(fileId);

        Assertions.assertNotNull(file);
        Assertions.assertEquals(newFile.getContenttype(), file.getContenttype());
        Assertions.assertEquals(newFile.getFilename(), file.getFilename());
        Assertions.assertNull(file.getUserid());
    }

    @Test
    public void deleteFile() {

        File newFile = new File(
                null,
                "hello",
                "txt",
                "3GB",
                null
        );

        this.fileMapper.insert(newFile);

        Integer fileId = newFile.getFileid();

        Assertions.assertNotNull(fileId);

        this.fileMapper.delete(fileId);

        File file = this.fileMapper.getFileById(fileId);

        Assertions.assertNull(file);
    }

    @Test
    public void getAllFiles() {

        File newFile = new File(
                null,
                "hello",
                "txt",
                "3GB",
                null
        );

        this.fileMapper.insert(newFile);

        Integer fileId = newFile.getFileid();

        Assertions.assertNotNull(fileId);

        List<File> fileList = this.fileMapper.getAllFiles();

        Assertions.assertFalse(fileList.isEmpty());
        Assertions.assertTrue(fileList.size() == 1);
    }
}
