package com.power.setting.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Follower {
    private String userId;
    private String followerId;

}
