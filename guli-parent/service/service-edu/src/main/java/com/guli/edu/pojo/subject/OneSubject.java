package com.guli.edu.pojo.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: gali
 * @Date: 2022-09-04 16:20
 * @Description:
 */
@Data
public class OneSubject {
    private String id;

    private String title;

    private List<TwoSubject> children = new ArrayList<>();
}
