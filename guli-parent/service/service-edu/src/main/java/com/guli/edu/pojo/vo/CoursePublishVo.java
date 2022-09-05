package com.guli.edu.pojo.vo;

import lombok.Data;

/**
 * @Auther: gali
 * @Date: 2022-09-05 20:39
 * @Description:
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
