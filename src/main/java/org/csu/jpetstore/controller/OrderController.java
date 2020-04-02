package org.csu.jpetstore.controller;

import com.alipay.api.domain.Car;
import org.csu.jpetstore.domain.Account;
import org.csu.jpetstore.domain.Cart;
import org.csu.jpetstore.domain.Order;
import org.csu.jpetstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/order/")
@SessionAttributes({"account","order","cart"})
public class OrderController {
    @Autowired
    OrderService orderService;
    //MyOders的跳转,需要传orderList,由于这部分在Account中，暂时无法实现
    @GetMapping("listOrder")
    public String viewOrder(Model model)
    {
        Account account=(Account) model.getAttribute("account");
        model.addAttribute("orderList",orderService.getListOrder(account.getUsername()));
        return "order/ListOrders";
    }
    //付款
    //关于ship的部分还未实装
    //跳转,创建order，以order传递
    @GetMapping("newOrderForm")
    public String newOrderForm(Model model){
        Order order = new Order();
        Account account = (Account) model.getAttribute("account");
        Cart cart = (Cart)model.getAttribute("cart");
        order.initOrder(account,cart);
        model.addAttribute("order",order);    //每次提交创建新的order
        return "order/newOrderForm";
    }
    //点击continue后,order的值被设置完,然后再在confirmOrder中展示，不需要中间操作。跳转
    @PostMapping("continueOrder")
    public String confirmOrder(Order order,Model model){
        //TODO 需要根据Ship to different address...是否勾选 来判断跳转的页面
        model.addAttribute("order",order);
        System.out.print(order.getOrderDate());
        return "order/confirmOrder";
    }
    //点击confirm后，order需提交至数据库，并且重置cart

    @PostMapping("confirmOrder")
    public String viewOrder(Order order,Model model){
        orderService.insertOrder(order);
        model.addAttribute("cart",new Cart());
        model.addAttribute("order",order);
        return "order/viewOrder";
    }
}
