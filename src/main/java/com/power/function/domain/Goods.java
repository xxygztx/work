package com.power.function.domain;

public class Goods {
    private String goodsId;

    private String comsId;

    private String userId;

    private String goodsTags;

    private String goodsCreatetime;

    private Integer goodsNum;

    private String goodsPicture;

    private String goodsDesc;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getComsId() {
        return comsId;
    }

    public void setComsId(String comsId) {
        this.comsId = comsId == null ? null : comsId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getGoodsTags() {
        return goodsTags;
    }

    public void setGoodsTags(String goodsTags) {
        this.goodsTags = goodsTags == null ? null : goodsTags.trim();
    }

    public String getGoodsCreatetime() {
        return goodsCreatetime;
    }

    public void setGoodsCreatetime(String goodsCreatetime) {
        this.goodsCreatetime = goodsCreatetime == null ? null : goodsCreatetime.trim();
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsPicture() {
        return goodsPicture;
    }

    public void setGoodsPicture(String goodsPicture) {
        this.goodsPicture = goodsPicture == null ? null : goodsPicture.trim();
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc == null ? null : goodsDesc.trim();
    }
}