package org.csu.jpetstore.controller;

import org.csu.jpetstore.domain.Account;
import org.csu.jpetstore.domain.Product;
import org.csu.jpetstore.service.AccountService;
import org.csu.jpetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/account/")
@SessionAttributes({"account","authenticated"})
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CatalogService catalogService;
    //跳转至登录
    @GetMapping("signonForm")
    public String signonForm(){
        return "account/signon";
    }

    //登录，如果需要验证码之类的则需要添加参数
    @PostMapping("signon")
    public String signon(String username, String password, Model model)
    {
        Account loginAccount = accountService.getAccount(username, password);
        if(loginAccount == null){
            String msg = "Invalid username or password.  Signon failed.";
            model.addAttribute("msg",msg);
            return "account/signon";
        }else {
            loginAccount.setPassword(null);
            List<Product> myList =catalogService.getProductListByCategory(loginAccount.getFavouriteCategoryId());
            boolean authenticated = true;
            model.addAttribute("account", loginAccount);
            model.addAttribute("myList",myList);
            model.addAttribute("authenticated",authenticated);
            return "catalog/main";
        }
    }

    //跳转至注册界面 这里我用的是registerForm
    @GetMapping("registerForm")
    public String registerForm(){return "account/newAccountForm";}
    //注册

    //更新账户

}
