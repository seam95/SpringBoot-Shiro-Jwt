package com.example.common.lang;


import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private String code;
    private String msg;
    private Object data;
    public static Result succ(Object data){
         Result m = new Result();
         m.setCode("200");
         m.setMsg("操作成功");
         m.setData(data);
         return m;
    }

    public static Result succ(String mess,Object data){
        Result m = new Result();
        m.setCode("200");
        m.setMsg(mess);
        m.setData(data);
        return m;
    }

    public static Result succ(String code,String mess,Object data){
        Result m = new Result();
        m.setCode(code);
        m.setMsg(mess);
        m.setData(data);
        return m;
    }
    public static Result fail(String mess){
        Result m = new Result();
        m.setCode("-1");
        m.setMsg(mess);
        m.setData(null);
        return m;
    }
    public static Result fail(String mess,Object data){
        Result m = new Result();
        m.setCode("-1");
        m.setMsg(mess);
        m.setData(data);
        return m;
    }

    public static Result fail(String code,String mess,Object data){
        Result m = new Result();
        m.setCode(code);
        m.setMsg(mess);
        m.setData(data);
        return m;
    }


}
