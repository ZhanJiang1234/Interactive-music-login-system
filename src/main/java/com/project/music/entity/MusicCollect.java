package com.project.music.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("music_collect")
public class MusicCollect implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String userId;

    private String musicId;

    private String createdTime;

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "MusicCollect{" +
            "id=" + id +
            ", userId=" + userId +
            ", musicId=" + musicId +
            ", createdTime=" + createdTime +
        "}";
    }
}
