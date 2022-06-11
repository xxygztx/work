package com.power.function.service;

import com.power.function.domain.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    int insert(Goods record);
    List<Goods> selectAll();
    List<Map> selectGetTopProduct();
}
