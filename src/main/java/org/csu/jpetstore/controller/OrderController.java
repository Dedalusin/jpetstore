package org.csu.jpetstore.controller;

import org.csu.jpetstore.domain.Account;
import org.csu.jpetstore.domain.Cart;
import org.csu.jpetstore.domain.CartItem;
import org.csu.jpetstore.domain.Order;
import org.csu.jpetstore.service.CatalogService;
import org.csu.jpetstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

@Controller
@RequestMapping("/order/")
@SessionAttributes({"account","order","cart","authenticated"})
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    CatalogService catalogService;
    //MyOders的跳转
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
    @PostMapping("newOrderForm")
    public String newOrderForm(HttpServletRequest request,Model model){
        Order order = new Order();
        Account account = (Account) model.getAttribute("account");

        if (account == null || !(boolean)model.getAttribute("authenticated"))
            return "account/signon";
        Cart cart = (Cart)model.getAttribute("cart");
        Iterator<CartItem> cartItems = cart.getAllCartItems();
        while (cartItems.hasNext()) {
            //若库存不够则跳到错误页面
            CartItem cartItem = cartItems.next();
            String itemId = cartItem.getItem().getItemId();
            int cartItemQuantity = Integer.parseInt(request.getParameter(itemId)); //购物车中显示的quantity,其name属性是itemId的值
            cart.setQuantityByItemId(itemId,cartItemQuantity);//将购物车中的qty更新至购物车对象中
            int quantity = cartItem.getItem().getQuantity();//该item的库存
            int remain = quantity - cartItemQuantity;
            if (remain < 0) {
                model.addAttribute("msg", "There is not enough stock for this item:" + itemId);
                return "common/error";
            }
        }
        order.initOrder(account,cart);
        model.addAttribute("order",order);    //每次提交创建新的order
        return "order/newOrderForm";
    }
    //点击continue后,order的值被设置完,然后再在confirmOrder中展示，不需要中间操作。跳转
    @PostMapping("continueOrder")
    public String confirmOrder(String shippingAddressRequired,Order order,Model model){
//        System.out.print(shippingAddressRequired);
        if(shippingAddressRequired!=null){ return "order/shippingForm"; }
        model.addAttribute("order",order);
        return "order/confirmOrder";
    }

    //点击confirm后，order需提交至数据库，并且重置cart
    @GetMapping("confirmOrder")
    public String viewOrder(Order order,Model model){
        orderService.insertOrder(order);
        model.addAttribute("cart",new Cart());
        model.addAttribute("order",order);
        return "order/viewOrder";
    }
    @GetMapping("/viewOrder")
    public String viewOrder(String orderId,Model model){
        if (orderId!=null){
            int orderid=Integer.parseInt(orderId);
            Order order = orderService.getOrder(orderid);
            model.addAttribute("order",order);
            return "order/viewOrder";
        }
        else {
            String msg="orderID为null";
            model.addAttribute("msg",msg);
            return "common/error";
        }

    }
}
