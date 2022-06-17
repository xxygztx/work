package com.power.function.service.impl;

import com.power.function.domain.Goods;
import com.power.function.mapper.GoodsMapper;
import com.power.function.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public int insert(Goods goods) {
       return goodsMapper.insert(goods);
    }

    @Override
    public List<Goods> selectAll(String userId) {
        return goodsMapper.selectAll(userId);
    }

    @Override
    public List<Map> selectGetTopProduct(Map map) {
        return goodsMapper.selectGetTopProduct(map);
    }

    @Override
    public int insertComsId(Map map) {
        return goodsMapper.insertComsId(map);
    }

    @Override
    public List<Goods> selectGoods(String s) {
        return goodsMapper.selectGoods(s);
    }
}
