package com.udacity.jwdnd.course1.cloudstorage.Model;

import lombok.Data;

@Data
public class FileModel {
    private Integer fileId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private Integer userId;
    private byte[] fileData;
}
