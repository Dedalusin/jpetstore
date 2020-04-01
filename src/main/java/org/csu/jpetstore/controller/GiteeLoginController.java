package org.csu.jpetstore.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthRequest;
import org.csu.jpetstore.domain.Account;
import org.csu.jpetstore.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes({"account","authenticated"})
public class GiteeLoginController {
    private static final long serialVersionUID = 1L;

    //ac85a173bb89ee
    private final String CLIENT_ID = "d99193c02ece6db364d82dbfc362e647b5f3d8f0ee46937c79c6f8518282dc3f";
    private final String CLIENT_SECRET= "e771061b78cb66781517a6b0e350140a5cfbd9a8bef15781e0b4b46ebbcfd4b7";
    private final String REDIRECTURI = "http://127.0.0.1:80/giteeCode";
    @GetMapping("/login")
    public String login(){
        AuthRequest authRequest = new AuthGiteeRequest(AuthConfig.builder()
                .clientId(CLIENT_ID) //Client ID
                .clientSecret(CLIENT_SECRET) //Client Secret
                .redirectUri(REDIRECTURI)   //回调地址
                .build());
        String authorizeUrl = authRequest.authorize();
        //跳转到授权页面
        return "redirect:"+ authorizeUrl;
    }
    @GetMapping("/giteeCode")
    public String giteeCode(String code, Model model){
        String url = "https://gitee.com/oauth/token?grant_type=authorization_code&code="+code+"&client_id="+CLIENT_ID+"&redirect_uri="+REDIRECTURI+"&client_secret="+CLIENT_SECRET;
        Map<String,String> map = new HashMap<>();
        map.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36)");
        JSONObject s = HttpUtils.post(url,map);
        System.out.println(s);

        //https://gitee.com/api/v5/user?access_token=*******
        String access_token = s.getString("access_token");
        String url2 = "https://gitee.com/api/v5/user?access_token="+access_token;
        JSONObject user = HttpUtils.get(url2,map);
        System.out.println(user.toJSONString());
        //1、设置响应类型输出流
//        response.setContentType("application/json;charset=UTF-8");
        //2、将json转为字符串
//        return JSON.toJSONString(user);
        Account giteeAccount = new Account();
        giteeAccount.setFirstName(user.getString("name"));
        System.out.print(giteeAccount.getFirstName());
        boolean authenticated = true;
        model.addAttribute("account", giteeAccount);
//        model.addAttribute("myList",myList);
        model.addAttribute("authenticated",authenticated);
        return "catalog/main";
        //3、得到字符输出流

//        response.getWriter().write(str);
    }
}
