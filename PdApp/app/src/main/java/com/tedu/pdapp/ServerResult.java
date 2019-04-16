package com.tedu.pdapp;

import java.io.Serializable;

/**
 * Created by cgb on 12/26/2018.
 */
//对应服务器返回的json{status:200,msg:"",data:""}
public class ServerResult implements Serializable{
    int status;
    String msg;
    Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
