package com.project.music.model;

import lombok.Data;

@Data
public class MusicVo {

    private String id;

    private String musicName;

    private String singer;

    private String fileUrl;

    private String categoryId;

    private String categoryName;

    private String imgUrl;

    private String albumCoverUrl;
}
