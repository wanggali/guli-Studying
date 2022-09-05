package com.guli.edu.pojo.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: gali
 * @Date: 2022-09-05 16:04
 * @Description:
 */
@Data
public class ChapterVo {

    private String id;

    private String title;

    //小节
    private List<VideoVo> children = new ArrayList<>();
}
