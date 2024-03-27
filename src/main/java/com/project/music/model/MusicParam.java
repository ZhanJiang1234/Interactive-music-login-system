package com.project.music.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MusicParam extends BaseParam{

    private String userId;
}
