package com.project.music.controller;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.music.common.Globals;
import com.project.music.common.Result;
import com.project.music.entity.Music;
import com.project.music.entity.MusicCategory;
import com.project.music.entity.User;
import com.project.music.model.MusicParam;
import com.project.music.model.MyMusicVo;
import com.project.music.service.IMusicCategoryService;
import com.project.music.service.IMusicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author test
 * @since 2023-11-19
 */
@Controller
@Slf4j
public class MusicController {

    @Resource
    private IMusicService iMusicService;

    @Resource
    private IMusicCategoryService iMusicCategoryService;

    @PostMapping(value = "/music/page")
    @ResponseBody
    public Result page(@RequestBody MusicParam musicParam){
        Page<Music> page = iMusicService.queryPage(musicParam);
        return Result.successData(page);
    }

    @GetMapping(value = "/music")
    public String music(){
        return "music";
    }

    @GetMapping(value = "/my")
    public String my(){
        return "my";
    }


    @GetMapping(value = "/mgr")
    public String mgr(){
        return "mgr";
    }

    @PostMapping(value = "/my/page")
    @ResponseBody
    public Result myPage(@RequestBody MusicParam musicParam, HttpSession session){
        User user = (User) session.getAttribute(Globals.USER_SESSION);
        musicParam.setUserId(user.getId());
        Page<MyMusicVo> page = iMusicService.queryMyPage(musicParam);
        return Result.successData(page);
    }

    @GetMapping(value = "/music/info")
    public String musicInfo(String musicId, ModelMap modelMap){
        if(StrUtil.isNotBlank(musicId)){
            Music music = iMusicService.getById(musicId);
            modelMap.put("music", music);
        }
        List<MusicCategory> list = iMusicCategoryService.list();
        modelMap.put("category", list);
        return "music_update";
    }

    @PostMapping(value = "/music/update")
    public String musicUpdate(Music music,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              @RequestParam("songUrl") MultipartFile songUrl,
                              @RequestParam("albumCover") MultipartFile albumCover

                              ){

        if(Objects.nonNull(imageFile)){
            String originalFilename = imageFile.getOriginalFilename();
            if(StrUtil.isNotBlank(originalFilename)){
                String suffix = FileUtil.getSuffix(originalFilename);
                String fileName = System.currentTimeMillis()+ "."+suffix;
                String filePath = upload+ File.separator+fileName;
                try{
                    byte[] bytes = imageFile.getBytes();
                    Path path= Paths.get(filePath);
                    Files.write(path,bytes);
                    music.setImgUrl(fileName);
                }catch (IOException e){
                    log.error(e.getMessage(), e);
                }
            }

        }

        String categoryId = music.getCategoryId();
        MusicCategory musicCategory = iMusicCategoryService.getById(categoryId);
        music.setCategoryName(musicCategory.getCategoryName());

        if(Objects.nonNull(songUrl)){
            String originalFilename = songUrl.getOriginalFilename();
            if(StrUtil.isNotBlank(originalFilename)){
                String suffix = FileUtil.getSuffix(originalFilename);
                String fileName = System.currentTimeMillis()+ "."+suffix;
                String filePath = upload+ File.separator+fileName;
                try{
                    byte[] bytes = songUrl.getBytes();
                    Path path= Paths.get(filePath);
                    Files.write(path,bytes);
                    music.setFileUrl(fileName);
                }catch (IOException e){
                    log.error(e.getMessage(), e);
                }
            }
        }

        if(Objects.nonNull(albumCover)){
            String originalFilename = albumCover.getOriginalFilename();
            if(StrUtil.isNotBlank(originalFilename)){
                String suffix = FileUtil.getSuffix(originalFilename);
                String fileName = System.currentTimeMillis()+ "."+suffix;
                String filePath = upload+ File.separator+fileName;
                try{
                    byte[] bytes = albumCover.getBytes();
                    Path path= Paths.get(filePath);
                    Files.write(path,bytes);
                    music.setAlbumCoverUrl(fileName);
                }catch (IOException e){
                    log.error(e.getMessage(), e);
                }
            }
        }

        music.setCreatedTime(DateUtil.format(new Date(), DatePattern.NORM_DATETIME_FORMAT));

        if(StrUtil.isNotBlank(music.getId())){
            iMusicService.updateById(music);
        }else{
            iMusicService.save(music);
        }
        return "redirect:/mgr";
    }

    @Value("${upload}")
    private String upload;


    @GetMapping(value = "/music/delete")
    @ResponseBody
    public Result delete(String musicId){
        iMusicService.removeById(musicId);
        return Result.success();
    }
}
