package org.csu.jpetstore.BMSController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.csu.jpetstore.annotation.JwtToken;
import org.csu.jpetstore.domain.Account;
import org.csu.jpetstore.domain.SimpleAccount;
import org.csu.jpetstore.domain.Supplier;
import org.csu.jpetstore.service.AccountService;
import org.csu.jpetstore.utils.JwtUtil;
import org.csu.jpetstore.utils.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bms/account")
public class BMSAccountController {
    @Autowired
    public AccountService accountService;
    @PostMapping("/login")
    @JwtToken(required = false) //这个require其实没意义
    public ResultFactory login(@RequestParam("username") String username,@RequestParam("password") String password){
        Supplier loginAccount=accountService.getSupplier(username, password);
        if(loginAccount!=null){
            String token= JwtUtil.sign(username);
            loginAccount.setToken(token);
            return ResultFactory.successResult(loginAccount,"登录成功");
        }
        else{
            return ResultFactory.failedResult(50010,"登录失败");
        }
    }
    @PostMapping("/register")
    @JwtToken(required = false)
    public ResultFactory register(@RequestParam("username")String username,@RequestParam("password") String password){
        Supplier registerAccount=accountService.getSupplier(username,password);
        if(registerAccount==null){
            Supplier supplier=new Supplier();
            supplier.setUsrname(username);
            supplier.setPassword(password);
            accountService.insertSupplier(supplier);
            return ResultFactory.successResult(accountService.getSupplier(username),"注册成功");
        }
        else{
            return ResultFactory.failedResult(50010,"账号已存在，注册失败");
        }
    }
    @GetMapping("/usernameIsExist")
    public ResultFactory check(@RequestParam("username")String username){
        boolean result=accountService.supplierUsernameIsExist(username);
        if(result){
            return ResultFactory.failedResult(422,"账号已存在");
        }else{
            return ResultFactory.successResult(null,"账号可行");
        }
    }
    @PutMapping("/editSupplier")    //重复密码用js验证吧
    public ResultFactory editSupplier(@RequestBody Supplier supplier){
        accountService.updateSupplier(supplier);
        return ResultFactory.successResult(supplier,"成功");
    }
    @GetMapping("/viewAccount")
    public ResultFactory viewAccount(@RequestParam(value = "pagenum")int pagenum,@RequestParam(value = "pagesize")int pageSize){
        PageHelper.startPage(pagenum, pageSize);
        List<Account> accountList=accountService.getAllAccount();
        PageInfo<Account> pageInfo = new PageInfo<>(accountList);
        return ResultFactory.successResult(pageInfo,"查询成功");
    }
    @PutMapping("/editAccount")
    public ResultFactory editAccount(@RequestBody SimpleAccount simpleAccount){
        //不知道json转化为对象需不需要所有的属性赋值，所以用了一个简单的account对象
        Account account=Account.parse(simpleAccount);
        accountService.editAccount(account);
        return ResultFactory.successResult(null,"修改成功");
    }
}
