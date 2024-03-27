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
@TableName("music_category")
public class MusicCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String categoryName;

    private Integer orderBy;

    public void setId(String id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return "MusicCategory{" +
            "id=" + id +
            ", categoryName=" + categoryName +
            ", orderBy=" + orderBy +
        "}";
    }
}
