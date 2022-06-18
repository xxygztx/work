package com.power.function.mapper;

import com.power.function.domain.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsMapper {
    int deleteByPrimaryKey(String goodsId);

    /**
     * 创建作品。
     * @param goods
     * @return
     */
    int insert(Goods goods);

    /**
     * 查询所有作品
     * @return
     */
    List<Goods> selectAll(String userId);

    /**
     * 查询首页推荐的产品
     * @return
     */
    List<Map> selectGetTopProduct(Map map);

    /**
     * 给评论区添加商品和评论区id
     * @param map
     * @return
     */
    int insertComsId(Map map);
    List<Goods> selectGoods(String s);
    int selectCount();
    int insertSelective(Goods record);

    Goods selectByPrimaryKey(String goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);


}