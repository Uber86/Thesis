package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AvatarDTO {

    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    private byte[] data;
}
