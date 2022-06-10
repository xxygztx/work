package com.power.setting.utills;


import java.text.SimpleDateFormat;
import java.util.Date;

public class TransfromDate {

    public static String toDateString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  simpleDateFormat.format(date);
    }
}
