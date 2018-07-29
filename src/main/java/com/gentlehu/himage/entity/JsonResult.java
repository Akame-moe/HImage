package com.gentlehu.himage.entity;

import com.gentlehu.himage.utils.GsonUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by gentle-hu on 2018/7/29 3:55.
 * Email:me@gentlehu.com
 */
@Setter
@Getter
public class JsonResult {

    private int code;
    private String msg;

    private Object data;

    public JsonResult(int code,String msg){
        this.code = code;
        this.msg = msg;
        this.data = null;
    }
    public JsonResult(int code,String msg,Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult ok(){
        return new JsonResult(Code.SUCCESS,"success");
    }
    public static JsonResult ok(Object data){
        return new JsonResult(Code.SUCCESS,"success",data);
    }
    public static JsonResult error(){
        return new JsonResult(Code.ERROR,"error");
    }
    public static JsonResult error(String msg){
        return new JsonResult(Code.ERROR,msg);
    }
    @Override
    public String toString() {
        return GsonUtil.toJson(this);
    }

    public String json(){
        return GsonUtil.toJson(this);
    }

    public static class Code{
        public static final int SUCCESS = 0;
        public static final int ERROR = 1;
    }

}
