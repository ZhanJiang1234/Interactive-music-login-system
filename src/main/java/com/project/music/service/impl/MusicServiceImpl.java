package com.project.music.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.music.entity.Music;
import com.project.music.mapper.MusicMapper;
import com.project.music.model.MusicParam;
import com.project.music.model.MusicVo;
import com.project.music.model.MyMusicVo;
import com.project.music.service.IMusicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Driver;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author test
 * @since 2023-11-19
 */
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements IMusicService {

    @Resource
    private MusicMapper musicMapper;
    @Override
    public Page<Music> queryPage(MusicParam musicParam) {
        Page<Music> rowPage = new Page<>(musicParam.getPage(), musicParam.getPageSize());
        LambdaQueryWrapper<Music> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Music::getCreatedTime);
        rowPage = this.musicMapper.selectPage(rowPage, queryWrapper);
        return rowPage;
    }

    @Override
    public Page<MyMusicVo> queryMyPage(MusicParam musicParam) {
        IPage<MyMusicVo> page = new Page<>(musicParam.getPage(), musicParam.getPageSize());
        return musicMapper.queryMyPage(page, musicParam);
    }

    @Override
    public List<MusicVo> queryHostMusicList() {
        return musicMapper.queryHostMusicList();
    }
}
