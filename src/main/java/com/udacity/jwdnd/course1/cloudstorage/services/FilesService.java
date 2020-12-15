package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Model.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FilesService {
    private UserService userService;
    private FileMapper fileMapper;
    @Value("${maxUploadSize}")
    private Long maximum;

    public FilesService(UserService userService, FileMapper fileMapper) {
        this.userService = userService;
        this.fileMapper = fileMapper;
    }

    public int upload(MultipartFile fileUpload, String username)
            throws IOException {
        if (fileMapper.getByFilename(userService.getId(username),
                fileUpload.getOriginalFilename()).size() > 0)
            throw new RuntimeException(
                    "You cannot upload two files with the same name.");
        if (fileUpload.getSize() > maximum)
            throw new RuntimeException(
                    "The file exceeds the maximum permitted size.");
        FileModel file = new FileModel();
        file.setUserId(userService.getId(username));
        file.setContentType(fileUpload.getContentType());
        file.setFileName(fileUpload.getOriginalFilename());
        file.setFileSize(String.valueOf(fileUpload.getSize()));
        file.setFileData(fileUpload.getBytes());
        return fileMapper.insert(file);
    }

    public List<FileModel> getFiles(String username) {
        return fileMapper.filesForId(userService.getId(username));
    }

    public int delete(Integer fileId, String username) {
        return fileMapper.deleteFileIdForUser(fileId, userService.getId(username));
    }

    public FileModel getFile(Integer fileId, String username) {
        return fileMapper.getFileIdForUser(fileId, userService.getId(username));
    }
}
