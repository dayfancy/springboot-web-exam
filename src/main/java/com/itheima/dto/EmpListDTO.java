package com.itheima.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Author: RightSquare
 * @Date: 2025/10/23 9:34
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpListDTO {

    private String name; //姓名
    private Integer gender; //性别, 1:男, 2:女

    private LocalDate begin; //开始时间
    private LocalDate end; //结束时间

    private Integer page = 1;
    private Integer pageSize = 10;
}
