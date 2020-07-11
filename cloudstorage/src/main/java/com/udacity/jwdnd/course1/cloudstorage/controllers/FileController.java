package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/files")
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public String uploadFile(
            @RequestParam("fileUpload") MultipartFile fileUpload,
            Authentication authentication
    )  {

        String username = (String) authentication.getPrincipal();

        // check if file is empty
        if (fileUpload.isEmpty()) {
            return "redirect:/result?isSuccess=" + false;
        }

        String fileName = fileUpload.getOriginalFilename();

        if(!this.fileService.isFileNameAvailableForUser(username, fileName)) {
            return "redirect:/result?isSuccess=" + false;
        }

        try {
            this.fileService.saveFile(fileUpload, username);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/result?isSuccess=" + true;
    }

    @GetMapping("/delete")
    public String deleteFile(
            @RequestParam(required = false, name = "fileId") Integer fileId) {

        Boolean isSuccess = this.fileService.deleteFile(fileId);

        return "redirect:/result?isSuccess=" + isSuccess;
    }

}
