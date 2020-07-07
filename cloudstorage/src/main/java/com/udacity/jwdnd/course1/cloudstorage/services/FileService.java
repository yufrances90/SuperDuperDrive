package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserFileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.UserFileVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    private Logger logger = LoggerFactory.getLogger(FileService.class);

    private FileMapper fileMapper;
    private UserFileMapper userFileMapper;

    public FileService(
            FileMapper fileMapper, UserFileMapper userFileMapper) {

        this.fileMapper = fileMapper;
        this.userFileMapper = userFileMapper;
    }
}
