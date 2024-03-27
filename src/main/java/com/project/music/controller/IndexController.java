package com.project.music.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.music.entity.Music;
import com.project.music.entity.MusicCategory;
import com.project.music.model.MusicCategoryVo;
import com.project.music.model.MusicVo;
import com.project.music.service.IMusicCategoryService;
import com.project.music.service.IMusicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private IMusicCategoryService iMusicCategoryService;

    @Resource
    private IMusicService iMusicService;



    @RequestMapping(value = {"/index", "/"})
    public String index(ModelMap modelMap){
        LambdaQueryWrapper<MusicCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(MusicCategory::getOrderBy);
        List<MusicCategory> list = iMusicCategoryService.list(queryWrapper);
        List<MusicCategoryVo> vos = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(list)){
            MusicCategoryVo vo;
            for (MusicCategory musicCategory : list) {
                vo = new MusicCategoryVo();
                vo.setMusicCategory(musicCategory);
                LambdaQueryWrapper<Music> queryWrapper2 = new LambdaQueryWrapper<>();
                queryWrapper2.orderByDesc(Music::getCreatedTime);
                queryWrapper2.eq(Music::getCategoryId, musicCategory.getId());
                queryWrapper2.last("limit 10");
                List<Music> musicList = iMusicService.list(queryWrapper2);
                if(CollectionUtil.isNotEmpty(musicList)){
                    vo.setMusicList(musicList);
                    vos.add(vo);
                }

            }
        }
        modelMap.put("musicList", vos);
        List<MusicVo> hostMusicList = iMusicService.queryHostMusicList();
        modelMap.put("hostMusicList", hostMusicList);
        return "index";
    }
}
