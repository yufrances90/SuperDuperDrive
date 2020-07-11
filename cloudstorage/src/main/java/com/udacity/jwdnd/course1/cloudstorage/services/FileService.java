package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserFileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.models.UserFileVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileService {

    private Logger logger = LoggerFactory.getLogger(FileService.class);

    private FileMapper fileMapper;
    private UserFileMapper userFileMapper;
    private UserMapper userMapper;

    public FileService(
            FileMapper fileMapper,
            UserFileMapper userFileMapper,
            UserMapper userMapper) {

        this.fileMapper = fileMapper;
        this.userFileMapper = userFileMapper;
        this.userMapper = userMapper;
    }

    public Boolean isFileNameAvailableForUser(String username, String filename) {

        Map<String, Object> paraMap = new HashMap<>();

        paraMap.put("username", username);
        paraMap.put("filename", filename);

        return this.userFileMapper.getFileByUsernameAndFileName(paraMap).isEmpty();
    }

    public List<UserFileVO> getFilesByUser(String username) {
        return this.userFileMapper.getFileByUsername(username);
    }

    public Boolean saveFile(MultipartFile file, String username) throws IOException {

        User user = this.userMapper.getUserByUsername(username);

        Integer userId = user.getUserid();

        byte[] fileData = file.getBytes();
        String contentType = file.getContentType();
        String fileSize = String.valueOf(file.getSize());
        String fileName = file.getOriginalFilename();

        this.fileMapper.insert(new File(null, fileName, contentType, fileSize, userId, fileData));

        return true;
    }

    public Boolean deleteFile(Integer fileId) {

        this.fileMapper.delete(fileId);

        return true;
    }
}
