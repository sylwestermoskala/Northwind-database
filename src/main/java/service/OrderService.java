package service;

import dao.OrderDao;
import model.Order;

import java.util.List;

public class OrderService {

    private final OrderDao orderDao;

    public OrderService() {
        orderDao = new OrderDao();
    }

    public void persist(Order entity) {
        orderDao.persist(entity);
    }

    public void update(Order entity) {
        orderDao.update(entity);
    }

    public Order findById(Integer id) {
        return orderDao.findById(id);
    }

    public List<Order> findAll() {
        return orderDao.findAll();
    }

    public void delete(Integer id) {
        Order order = orderDao.findById(id);
        orderDao.delete(order);
    }

    public void deleteAll() {
        orderDao.deleteAll();
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

}
