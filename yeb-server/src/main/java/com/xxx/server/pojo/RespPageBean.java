package com.xxx.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespPageBean {
    /**
     * 总条数
     */
    private Long total;
    /*
    数据
     */
    private List<?> data;
}
