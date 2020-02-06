package dao;

import model.Order;

import java.util.List;

public class OrderDao extends AbstractDao implements BaseDao<Order, Integer> {

    @Override
    public void persist(Order entity) {
        abstractPersist(entity);
    }

    @Override
    public void update(Order entity) {
        abstractUpdate(entity);
    }

    @Override
    public Order findById(Integer id) {
        openCurrentSessionWithTransaction();
        Order order = currentSession.get(Order.class, id);
        closeCurrentSessionWithTransaction();
        return order;
    }

    @Override
    public void delete(Order entity) {
        abstractDelete(entity);
    }

    @Override
    public List<Order> findAll() {
        openCurrentSessionWithTransaction();
        List<Order> orders = currentSession.createQuery("FROM Order", Order.class).list();
        closeCurrentSessionWithTransaction();
        return orders;
    }

    @Override
    public void deleteAll() {
        try {
            openCurrentSessionWithTransaction();
            List<Order> entityList = currentSession.createQuery("FROM Order", Order.class).list();
            entityList.forEach(this::delete);
            closeCurrentSessionWithTransaction();
        } catch (RuntimeException e) {
            currentTransaction.rollback();
            throw e;
        } finally {
            currentSession.close();
        }
    }
}
