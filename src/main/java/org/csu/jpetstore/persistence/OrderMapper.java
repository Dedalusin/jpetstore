package org.csu.jpetstore.persistence;

import org.csu.jpetstore.domain.LineItem;
import org.csu.jpetstore.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    List<Order> getOrdersByUsername(String username);

    List<Order> getAllOrder();

    Order getOrder(int orderId);

    void insertOrder(Order order);

    void insertOrderStatus(Order order);

    List<LineItem> getOrderInfo(int orderId);

    void changeState(Order order);

    void sendstatus(Order order);
}
