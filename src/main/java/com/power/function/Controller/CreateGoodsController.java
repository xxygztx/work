package com.power.function.Controller;

import com.power.function.domain.Goods;
import com.power.function.service.GoodsService;
import com.power.setting.utills.RandomNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class CreateGoodsController {
    @Autowired
    GoodsService goodsService;

    /**
     * 上传作品
     * @param map
     * @return
     */
    @ResponseBody
    @PostMapping("/uploadProduct")
    public Object uploadProduct(@RequestParam Map map, HttpSession session){
        //将接受到的数据从map集合中拿出来
        String id= (String) map.get("id");
        String[] tags = (String[]) map.get("tag");
        String name = (String) map.get("name");
        //定义一个存储标签数组拼接的字符串变量
        String result =null;
        for(String tag:tags){
            result += tag+",";
        }
        String description = (String) map.get("description");
        String[] imgs = (String[]) map.get("imgs");
        //定义一个存储图片地址拼接的字符串变量
        String picture =null;
        for(String img :imgs){
            picture += tags+",";
        }
        //查询user的id.
        String userId= (String) session.getAttribute("userId");
        //将得到的作品数据放到Goods对象中
        Goods goods =new Goods();
        goods.setGoodsId(id);
        goods.setGoodsTags(result);
        goods.setGoodsDesc(description);
        goods.setGoodsPicture(picture);
        goods.setGoodsNum(0);
        goods.setGoodsCreatetime(RandomNumber.getNum());
        goods.setComsId(RandomNumber.getNum());
        goods.setUserId(userId);
        return null;
    }
}
