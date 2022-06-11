package com.power.setting.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class User {
    private String userId;

    private String userName;

    private String userAvatar;

    private String createtime;

    private String logintime;

    private String nameauth;

    private Integer userAge;

    private Integer userNum;

    private String userPassword;

    private String userTele;

    private String useDesc;

    private String userSex;

    }