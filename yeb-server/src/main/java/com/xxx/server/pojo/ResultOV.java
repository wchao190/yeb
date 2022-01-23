package com.xxx.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultOV {
    private Integer code;
    private String message;
    private Object obj;

    /**
     * 返回成功
     */
    public static ResultOV success(String message){
        return new ResultOV(200,message,null);
    }
    /**
     * 返回成功
     */
    public static ResultOV success(String message,Object obj){
        return new ResultOV(200,message,obj);
    }

    /**
     * 失败返回
     * @param message
     * @return
     */
    public static ResultOV error(String message){
        return new ResultOV(500,message,null);
    }
    /**
     * 失败返回
     * @param message
     * @return
     */
    public static ResultOV error(String message,Object obj){
        return new ResultOV(500,message,obj);
    }

}
