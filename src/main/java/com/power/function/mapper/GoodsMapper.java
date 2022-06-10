package com.power.function.mapper;

import com.power.function.domain.Goods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsMapper {
    int deleteByPrimaryKey(String goodsId);

    /**
     * 创建作品。
     * @param goods
     * @return
     */
    int insert(Goods goods);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(String goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);


}