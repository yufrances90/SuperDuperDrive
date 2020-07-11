package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserFileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.UserFileVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    public Boolean isFileNameAvailableForUser(String username, String filename) {

        Map<String, Object> paraMap = new HashMap<>();

        paraMap.put("username", username);
        paraMap.put("filename", filename);

        return this.userFileMapper.getFileByUsernameAndFileName(paraMap) == null;
    }

    public Boolean saveFile(MultipartFile file, String username) throws IOException {

        byte[] fileData = file.getBytes();
        String contentType = file.getContentType();
        String fileSize = String.valueOf(file.getSize());
        String fileName = file.getOriginalFilename();

        this.logger.error(contentType);

        return null;
    }
}
