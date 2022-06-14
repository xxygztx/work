package com.power.function.Controller;

import com.power.function.domain.Goods;
import com.power.function.mapper.GoodsMapper;
import com.power.function.service.GoodsService;
import com.power.setting.Contains.Contains;
import com.power.setting.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GetGoodsController {

    @Autowired
    GoodsService goodsService;

    /**
     * 获取自己的全部作品，这个要从新设计
     * @return
     */
    @ResponseBody
    @GetMapping("getMyProduct")
    public Object getMyProduct(){
        List<Goods> goods = goodsService.selectAll();
        //建立一个集合储存返回结果
        Map map = new HashMap();
        if(goods!=null&&goods.size()>0){
            map.put("status",200);
            map.put("data",goods);
            map.put("info", Contains.EXECUTE_SUCCESS);
        }else{
            map.put("status",403);
            map.put("info",Contains.EXECUTE_FINAL);
        }
        return map;
    }

    /**
     * 获取首页的作品
     * @return
     */
    @ResponseBody
    @GetMapping("/getTopProduct")
    public Object getTopProduct(@RequestParam("offset") Integer offest,
                                @RequestParam("limit") Integer limit){
        //创建衣蛾集合接受请求参数
        int startPages = (offest-1)*limit;
        Map map1 = new HashMap();
        map1.put("start",startPages);
        map1.put("limit",limit);
        //新建一个集合用来存储数据
        Map map = new HashMap();
        List<Map> maps = goodsService.selectGetTopProduct(map1);
        if(!maps.isEmpty()|| maps==null){
            map.put("status",200);
            map.put("product",maps);
            map.put("info",Contains.EXECUTE_SUCCESS);
        }
        else{
            map.put("status",200);
            map.put("product","没有作品");
            map.put("info",Contains.EXECUTE_SUCCESS);
        }
        return map;
    }

}
