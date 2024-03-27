package com.project.music.entity;

import lombok.Getter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author test
 * @since 2023-11-19
 */
@Getter
public class Music implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String musicName;

    private String singer;

    private String fileUrl;

    private String categoryId;

    private String categoryName;

    private String imgUrl;

    private String albumCoverUrl;


    public void setAlbumCoverUrl(String albumCoverUrl) {
        this.albumCoverUrl = albumCoverUrl;
    }

    private String createdTime;

    public void setId(String id) {
        this.id = id;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "Music{" +
            "id=" + id +
            ", musicName=" + musicName +
            ", singer=" + singer +
            ", fileUrl=" + fileUrl +
            ", categoryId=" + categoryId +
            ", categoryName=" + categoryName +
            ", imgUrl=" + imgUrl +
            ", createdTime=" + createdTime +
            ", albumCoverUrl=" + albumCoverUrl +
        "}";
    }
}
