package com.power.setting.Controller;

import com.power.setting.Contains.Contains;
import com.power.setting.Contains.ReturnObject;
import com.power.setting.Service.UserService;
import com.power.setting.domain.GetCare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FollwerController {

    @Autowired
    UserService userService;
    @ResponseBody
    @GetMapping("getCare")
    public Object getCare(){
        //建立一个集合存放数据
        Map map = new HashMap();
        //从关注表中查出关注人的id
        List<Map<String, String>> Follower = userService.selectFollower();
        //再定义一个集合
        List follower= new ArrayList();
        //获取集合中所有map的key放入集合中
        for(Map s:Follower){
            follower.add(s.get("followerId"));
        }
        if(Follower==null||Follower.size()==0){
            map.put("status",200);
            map.put("data",null);
            map.put("info",Contains.EXECUTE_SUCCESS);
        }else {
            try {
                //在用户表中查出关注人的详细信息
                List<GetCare> getCares = userService.selectCare(follower);
                if (getCares != null) {
                    map.put("status", 200);
                    map.put("data", getCares);
                    map.put("info", Contains.EXECUTE_SUCCESS);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
    @ResponseBody
    @PostMapping("/care")
    public Object care(String uid,String target){
        Map<String,String> map = new HashMap<String,String>();
        map.put("uid",target);
        map.put("target",target);
        ReturnObject returnObject =new ReturnObject();
        try {
            int i = userService.insertCare(map);
            if(i>0){
                returnObject.setStatus(200);
                returnObject.setData("null");
                returnObject.setInfo(Contains.EXECUTE_SUCCESS);
            }else{
                returnObject.setStatus(403);
                returnObject.setData("关注失败");
                returnObject.setInfo(Contains.EXECUTE_FINAL);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnObject;
    }
}
