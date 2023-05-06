package com.qhl.salary_java.controller;

import com.qhl.salary_java.response.WebApiResponse;
import com.qhl.salary_java.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author qhl
 * @date 2023-5-5 16:35
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {
    @Autowired
    private FileUploadService service;

    @PostMapping("/upload")
    public WebApiResponse uploads(@RequestParam("file") MultipartFile file) throws Exception {
        return service.upload(file);
    }
}
