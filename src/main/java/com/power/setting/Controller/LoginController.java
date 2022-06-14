package com.power.setting.Controller;

import com.power.setting.Contains.Contains;
import com.power.setting.Service.UserService;
import com.power.setting.domain.User;
import com.power.setting.utills.RandomNumber;
import com.power.setting.utills.TransfromDate;
import com.power.setting.utills.TwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    /**
     * 注册
     *
     * @param param
     * @return
     */
    @ResponseBody
    @PostMapping("/registered")
    public Object registered(@RequestParam Map param,HttpSession session) {
        Map map = new HashMap();
        map.put("userId", RandomNumber.getNum());
        map.put("userAvatar", param.get("avatar"));
        map.put("userName", param.get("name"));
        map.put("createtime", TransfromDate.toDateString(new Date()));
        map.put("logintime", "0");
        map.put("nameauth", "false");
        map.put("userAge", param.get("age"));
        map.put("userNum", 0);
        map.put("userPassword", param.get("password"));
        map.put("userTele", param.get("phone"));
        map.put("useDesc", param.get("decration"));
        map.put("userSex", param.get("sex"));
        Map map1 =new HashMap();
        //还要判断账号和密码是否已经存在，如果存在就失败，没有就继续运行。
           List<User> user= userService.selectPhone((String) param.get("phone"));
           if(user!=null && user.size()>0){
               map1.put("info","账号已存在");
               return map1;
           }else {
               //将userId和password连接到一起
               String connect = (String) map.get("userTele") + "," + map.get("userPassword");
               //将userId保存到session中
               session.setAttribute("userId", map.get("userId"));
               int sucess = 0;
               try {
                   sucess = userService.insert(map);
                   //新建用户成功
                   if (sucess > 0) {
                       map1.put("info", Contains.EXECUTE_SUCCESS);
                       map1.put("data",map);
                       map1.put("status", 200);
                       //生成token
                       String token = TwtUtil.createToken(connect);
                       //将token封装到map中
                       map.put("token", token);
                   } else {
                       map.clear();
                       map1.put("status","403");
                       map1.put("info", Contains.EXECUTE_FINAL);
                   }
               } catch (Exception e) {
                map1.put("info",Contains.EXECUTE_FINAL);
                map1.put("status","403");
                   e.printStackTrace();
               }
           }
        return map1;
    }

    /**
     * 登录
     *
     * @param phone
     * @param password
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public Object login(@RequestParam("phone") String phone,HttpSession session,
                        @RequestParam("password") String password) {
        //使用map对象向查询语句传值
        Map map = new HashMap();
        Map map1 =new HashMap();
        map1.put("phone", phone);
        map1.put("password", password);
        User user = userService.selectUser(map1);
        if (user != null) {
            //将用户的id存储到session中
            session.setAttribute("userId",user.getUserId());
            User user1 = new User();
            user1.setUserPassword(password);
            user1.setUserTele(phone);
            user1.setLogintime(TransfromDate.toDateString(new Date()));
            //生成token
            String connect=phone+","+password;
            String token = TwtUtil.createToken(connect);
            //将token封装到map中
            map.put("token", token);
            try {
                //更新数据库中的数据
                int result = userService.updateLoginTime(user1);
                //修改成功
                if (result > 0) {
                    user.setLogintime(TransfromDate.toDateString(new Date()));
                    map.put("data", user);
                    map.put("info", Contains.EXECUTE_SUCCESS);
                    map.put("status", 200);

                } else {
                    map.put("info", Contains.EXECUTE_FINAL);
                    map.put("status", 403);
                    map.put("data", Contains.EXECUTE_MASSAGE);
                }
            } catch (Exception e) {
                    map.put("info", Contains.EXECUTE_FINAL);
                    map.put("status", 403);
                    map.put("data", Contains.EXECUTE_MASSAGE);
                e.printStackTrace();
            }
        }else{
            map.put("info", Contains.EXECUTE_FINAL);
            map.put("status", 403);
            map.put("data", Contains.EXECUTE_MASSAGE);
        }
        return map;
    }

    /**
     * 有token时候的登录
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/logins")
    public Object logins(HttpServletRequest request,HttpSession session) {
        //建立一个返回参数的集合
        Map map = new HashMap();
        //获取token
        String tokens = request.getHeader("token");
        //将token中的电话号码和密码提取出来
        String connect = TwtUtil.verifyToken(tokens);
        if (connect != null) {
            String phone = connect.substring(0, 11);
//        map.put("phone",phone);
            String password = connect.substring(12);
            //可以给数据库中更新数据
            User user1 = new User();
            user1.setUserTele(phone);
            user1.setUserPassword(password);
            user1.setLogintime(TransfromDate.toDateString(new Date()));
            int result = 0;
            try {
                result = userService.updateLoginTime(user1);
                //修改成功
                if (result > 0) {
                    //将数据封装到map集合中
                    Map map1 = new HashMap();
                    map1.put("phone", phone);
                    map1.put("password", password);
                    //从数据库中查询其他的数据
                    User user = userService.selectUser(map1);
                    //将userId放到session中
                    session.setAttribute("userId", user.getUserId());
                    map.put("data", user);
                    map.put("info", Contains.EXECUTE_SUCCESS);
                    map.put("status", 200);

                } else {
                    map.put("info", Contains.EXECUTE_FINAL);
                    map.put("status", 403);
                    map.put("data", Contains.EXECUTE_MASSAGE);
                }
            } catch (Exception e) {
               map.put("data", Contains.EXECUTE_MASSAGE);
               map.put("info",Contains.EXECUTE_FINAL);
                e.printStackTrace();
            }
        }else{
            map.put("info", Contains.EXECUTE_FINAL);
            map.put("status", 403);
            map.put("data", Contains.EXECUTE_MASSAGE);
        }
        return map;
    }
}