package com.letsgo.blog.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements  FileService{

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        //File name
        String originalFilename = file.getOriginalFilename();
        //abc.png

        //random name generate file
        String randomId = UUID.randomUUID().toString();
        String fileName = randomId.concat(originalFilename.substring(originalFilename.lastIndexOf('.')));

        //FullPath
        String filePath = path + File.separator + fileName;

        //create folder if not created
        File file1 = new File(path);
        if(!file1.exists()) {
            file1.mkdir();
        }

        //File Copy
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return  fileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }
}
