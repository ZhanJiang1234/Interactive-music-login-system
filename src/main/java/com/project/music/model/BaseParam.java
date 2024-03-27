package com.project.music.model;

import lombok.Data;

@Data
public class BaseParam {

    /**
     * 第几页
     */
    private int page = 1;
    /**
     * 每页个数
     */
    private int pageSize = 10;
}
