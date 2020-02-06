package dao;

import model.Supplier;

import java.util.List;

public class SupplierDao extends AbstractDao implements BaseDao<Supplier, Integer> {

    @Override
    public void persist(Supplier entity) {
        abstractPersist(entity);
    }

    @Override
    public void update(Supplier entity) {
        abstractUpdate(entity);
    }

    @Override
    public Supplier findById(Integer id) {
        openCurrentSessionWithTransaction();
        Supplier supplier = currentSession.get(Supplier.class, id);
        closeCurrentSessionWithTransaction();
        return supplier;
    }

    @Override
    public void delete(Supplier entity) {
        abstractDelete(entity);
    }

    @Override
    public List<Supplier> findAll() {
        try {
            openCurrentSessionWithTransaction();
            List<Supplier> suppliers = currentSession.createQuery("FROM Supplier", Supplier.class).list();
            closeCurrentSessionWithTransaction();
            return suppliers;
        } catch (RuntimeException e) {
            currentTransaction.rollback();
            throw e;
        } finally {
            currentSession.close();
        }
    }

    @Override
    public void deleteAll() {
        try {
            openCurrentSessionWithTransaction();
            List<Supplier> suppliers = currentSession.createQuery("FROM Supplier", Supplier.class).list();
            suppliers.forEach(this::delete);
            closeCurrentSessionWithTransaction();
        } catch (RuntimeException e) {
            currentTransaction.rollback();
            throw e;
        } finally {
            currentSession.close();
        }
    }
}
