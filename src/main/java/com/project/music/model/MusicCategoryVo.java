package com.project.music.model;

import com.project.music.entity.Music;
import com.project.music.entity.MusicCategory;
import lombok.Data;

import java.util.List;

@Data
public class MusicCategoryVo {

    private MusicCategory musicCategory;

    private List<Music> musicList;
}
