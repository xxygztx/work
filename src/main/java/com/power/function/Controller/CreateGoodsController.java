package com.power.function.Controller;

import com.power.function.domain.Goods;
import com.power.function.service.GoodsService;
import com.power.setting.Contains.Contains;
import com.power.setting.utills.RandomNumber;
import com.power.setting.utills.TransfromDate;
import com.power.setting.utills.TwtUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
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
    public Object uploadProduct(@RequestParam Map map,
                                @RequestParam("imgs") String[] imgs,HttpSession session){
        //将接受到的数据从map集合中拿出来
        String userId= (String) map.get("userId");
        String tags = (String) map.get("tag");
        String name = (String) map.get("name");
        //定义一个存储标签数组拼接的字符串变量
        String[] result =tags.split(",");

        String description = (String) map.get("description");
        String img = null;
         StringBuffer bulider =new StringBuffer();
        for(String s: imgs){
            bulider.append(s);
            bulider.append(",");
        }
        //定义一个存储图片地址拼接的字符串变量
        //查询user的id.
//        String userId= (String) session.getAttribute("userId");
        //将得到的作品数据放到Goods对象中
        Goods goods =new Goods();
        String num =RandomNumber.getNum();
        goods.setGoodsId(num);
        goods.setGoodsTags(tags);
        goods.setGoodsDesc(description);
        goods.setGoodsPicture(bulider.toString());
        goods.setGoodsNum(0);
        goods.setGoodsCreatetime(TransfromDate.toDateString(new Date()));
        String comsid = RandomNumber.getNum();
        goods.setComsId(comsid);
        goods.setUserId(userId);
        //定义一个集合给返回数据
        Map json =new HashMap();
        //将作品传到数据库中
        int i=0;
        //定义一个集合向评论区表里面添加数据
        Map coms = new HashMap();
        coms.put("goodsId",num);
        coms.put("comsId",comsid);
        try{
             i = goodsService.insert(goods);
            int i1 = goodsService.insertComsId(coms);
            if(i>0){
                json.put("status",200);
                json.put("createAt",goods.getGoodsCreatetime());
                json.put("id",userId);
                json.put("preview",description);
                json.put("tag",result);
                json.put("info", Contains.EXECUTE_SUCCESS);
            }else{
                json.put("status",403);
                json.put("info",Contains.EXECUTE_FINAL);
            }
        }catch(Exception e){
            json.put("status","403");
            json.put("info","该商品id已经存在");
           e.printStackTrace();
        }

        return json;
    }
}
