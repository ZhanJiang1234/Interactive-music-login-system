package com.project.music.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.music.entity.Music;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.music.model.MusicParam;
import com.project.music.model.MusicVo;
import com.project.music.model.MyMusicVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author test
 * @since 2023-11-19
 */
public interface IMusicService extends IService<Music> {

    Page<Music> queryPage(MusicParam musicParam);

    Page<MyMusicVo> queryMyPage(MusicParam musicParam);

    List<MusicVo> queryHostMusicList();
}
