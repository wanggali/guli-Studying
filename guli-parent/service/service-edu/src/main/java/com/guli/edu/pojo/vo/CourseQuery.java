package com.guli.edu.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: gali
 * @Date: 2022-09-07 16:02
 * @Description:
 */
@Data
public class CourseQuery {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "二级类别id")
    private String status;
}
