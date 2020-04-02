package org.csu.jpetstore.controller;

import org.csu.jpetstore.domain.Account;
import org.csu.jpetstore.domain.Cart;
import org.csu.jpetstore.domain.Product;
import org.csu.jpetstore.service.AccountService;
import org.csu.jpetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            model.addAttribute("authenticated",authenticated);
            return "catalog/main";
        }
    }
    //注销
    @GetMapping("signoff")
    public String signoff(Model model) {
        Account loginAccount = new Account();
        List<Product> myList = null;
        boolean authenticated = false;
        model.addAttribute("account", null);
        model.addAttribute("myList", myList);
        model.addAttribute("authenticated", authenticated);
        model.addAttribute("cart",new Cart());
        return "catalog/main";
    }

    //跳转至注册界面 这里我用的是registerForm
    @GetMapping("registerForm")
    public String registerForm(Model model){
        model.addAttribute("newAccount",new Account());
        return "account/newAccountForm";
    }
    //看账号名称是否存在
    @GetMapping("usernameIsExist")
    @ResponseBody
    public String usernameIsExist(String username){
        boolean result=accountService.usernameIsExist(username);
        if(result){
            return "Exist";
        }else{
            System.out.println("noExist");
            return "Not Exist";
        }
    }
    //注册
    @PostMapping("newAccount")
    public String newAccount(Account registerAccount,Model model){
        accountService.insertAccount(registerAccount);
        model.addAttribute("authenticated",true);
        model.addAttribute("account",registerAccount);
        return "catalog/main";
    }
    //更新账户
    @PostMapping("editAccount")     //关于两次的密码验证是否正确可以前端写在JS里面，或者前端取msg也可以
    public String editAccount(Account account, String repeatedPassword, Model model) {
        if (account.getPassword() == null || account.getPassword().length() == 0 || repeatedPassword == null || repeatedPassword.length() == 0) {
            String msg = "密码不能为空";
            model.addAttribute("msg", msg);
            return "account/editAccountForm";
        } else if (!account.getPassword().equals(repeatedPassword)) {
            String msg = "两次密码不一致";
            model.addAttribute("msg", msg);
            return "account/editAccountForm";
        } else {
            accountService.updateAccount(account);
            account = accountService.getAccount(account.getUsername());
            List<Product> myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
            boolean authenticated = true;
            model.addAttribute("account", account);
            model.addAttribute("myList", myList);
            model.addAttribute("authenticated", authenticated);
            return "redirect:/catalog/index";
        }
    }
    //点击MyAccount
    @GetMapping("gotomyAccount")
    public String gotomyAccount(){
        return "account/editAccountForm";
    }

}
