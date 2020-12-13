package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FilesController {
    FilesService filesService;

    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("fileUpload") MultipartFile fileUpload,
                         Model model, Authentication authentication){
        if (fileUpload.isEmpty())
            model.addAttribute("error","You must specify a file.");
        else
            try {
                filesService.upload(fileUpload, authentication.getName());
            } catch (Exception ex){
                model.addAttribute("error",ex.getMessage());
            }
        return "result";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id")Integer id,
                         Authentication authentication,
                         Model model){
        int deleted = filesService.delete(id, authentication.getName());
        if( deleted < 1){
            model.addAttribute("error",
                    "File doesn't exist, or doesn't belong to you. ");
        }
        return "result";
    }

    @GetMapping("/view")
    public ResponseEntity<byte[]> view(@RequestParam("id")Integer fileId,
                                       Authentication authentication){
        String username = authentication.getName();
        FileModel file = filesService.getFile(fileId, username);
        if (file == null)
            throw new RuntimeException(
                    "File doesn't exist, or doesn't belong to you.");
        return response(file.getFileData(), file.getFileName(), file.getContentType());
    }

    private ResponseEntity<byte[]> response(byte[] bytes, String fileName, String contentType) {
        MediaType mediaType = MediaType.parseMediaType(contentType);
        HttpHeaders headers = new HttpHeaders();
        String disposition = String.format("attachment; filename=%s", fileName);
        headers.set("Content-Disposition", disposition);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(bytes.length)
                .contentType(mediaType)
                .body(bytes);
    }
}
