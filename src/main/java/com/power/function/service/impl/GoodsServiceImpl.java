package com.power.function.service.impl;

import com.power.function.domain.Goods;
import com.power.function.mapper.GoodsMapper;
import com.power.function.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public int insert(Goods goods) {
       return goodsMapper.insert(goods);
    }
}
