package ru.skypro.homework.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Image {

    @Id
    private String id;

    private String fileName;
    private long fileSize;
    private String mediaType;

    public Image() {
    }

    public Image(String id, String fileName, long fileSize, String mediaType) {
        this.id = id;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return fileSize == image.fileSize && Objects.equals(id, image.id) && Objects.equals(fileName, image.fileName) && Objects.equals(mediaType, image.mediaType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileName, fileSize, mediaType);
    }

    @Override
    public String toString() {
        return "Image{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                '}';
    }
}

