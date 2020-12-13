package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Model.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FilesService {
    private UserService userService;
    private FileMapper fileMapper;

    public FilesService(UserService userService, FileMapper fileMapper) {
        this.userService = userService;
        this.fileMapper = fileMapper;
    }

    public int upload(MultipartFile fileUpload, String username)
            throws IOException {
        FileModel file = new FileModel();
        file.setUserId(userService.getUser(username).getUserid());
        file.setContentType(fileUpload.getContentType());
        file.setFileName(fileUpload.getOriginalFilename());
        file.setFileSize(String.valueOf(fileUpload.getSize()));
        file.setData(fileUpload.getBytes());
        return fileMapper.insert(file);
    }

    public List<FileModel> filesOf(String username){
        return fileMapper.filesForId(userService.getUser(username).getUserid());
    }
}
