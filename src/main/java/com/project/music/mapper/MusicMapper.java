package com.project.music.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.music.entity.Music;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.music.model.MusicParam;
import com.project.music.model.MusicVo;
import com.project.music.model.MyMusicVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author test
 * @since 2023-11-19
 */
public interface MusicMapper extends BaseMapper<Music> {

    Page<MyMusicVo> queryMyPage(IPage<MyMusicVo> page, MusicParam musicParam);

    List<MusicVo> queryHostMusicList();
}
