package com.power.setting.Contains;

import lombok.Data;


@Data
public class ReturnObject {
   public  Integer status ;
   public  String data;
    public  String info;

    public  String getData() {
        return data;
    }

    public  void setData(String data) {
        this.data = data;
    }

    public  Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public  String getInfo() {
        return info;
    }

    public  void setInfo(String info) {
        this.info = info;
    }
}
