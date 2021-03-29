package me.hj.zhibo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 统一响应浏览器请求的VO
 * @param <T> 返回数据类型
 */
@AllArgsConstructor
@Data
public class RespVO<T> {
    public RespVO(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static RespVO ok(String msg){
        return new RespVO(200,msg);
    }
    public static RespVO ok(String msg, Object obj){
        return  new RespVO(200,msg,obj);
    }
    public static RespVO error(String msg){
        return new RespVO(500, msg);
    }
    private Integer status;
    private String msg;
    private T data;
}
