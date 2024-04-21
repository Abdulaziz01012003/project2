package web.internet.magazin.project2mini.repo;

import web.internet.magazin.project2mini.entity.Order;
import web.internet.magazin.project2mini.entity.enums.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderRepo extends BaseRepo<Order, UUID> {


    public List<Order> findByStatus(Status status) {
        List<Order> orders = new ArrayList<>();
        for (Order order : findAll()) {
            orders.add(order);
        }
        return orders;
    }
}
