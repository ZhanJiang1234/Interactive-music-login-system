package com.project.music.model;

import com.project.music.entity.Music;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MyMusicVo extends Music {

    private String collectId;

    private String userId;

    private String musicId;

    private String createdTime;

}
