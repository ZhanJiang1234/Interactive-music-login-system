package com.project.music.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.music.common.Globals;
import com.project.music.common.Result;
import com.project.music.entity.MusicCollect;
import com.project.music.entity.User;
import com.project.music.service.IMusicCollectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author test
 * @since 2023-11-19
 */
@Controller
public class MusicCollectController {

    @Resource
    private IMusicCollectService iMusicCollectService;

    @GetMapping(value = "/collect")
    @ResponseBody
    public Result collect(String musicId, HttpSession session){
        MusicCollect musicCollect = new MusicCollect();
        musicCollect.setMusicId(musicId);
        User user = (User)session.getAttribute(Globals.USER_SESSION);
        musicCollect.setUserId(user.getId());

        LambdaQueryWrapper<MusicCollect> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MusicCollect::getMusicId, musicId).eq(MusicCollect::getUserId, user.getId());
        List<MusicCollect> list = iMusicCollectService.list(queryWrapper);
        if(CollectionUtil.isNotEmpty(list)){
            MusicCollect musicCollect1 = list.get(0);
            musicCollect1.setCreatedTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            iMusicCollectService.updateById(musicCollect1);
        }else{
            musicCollect.setCreatedTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            iMusicCollectService.save(musicCollect);
        }
        return Result.success();
    }

    @GetMapping(value = "/collect/cancel")
    @ResponseBody
    public Result cancel(String collectId){
        iMusicCollectService.removeById(collectId);
        return Result.success();
    }
}
