package service;

import dao.SupplierDao;
import model.Supplier;

import java.util.List;

public class SupplierService {

    private final SupplierDao supplierDao;

    public SupplierService() {
        supplierDao = new SupplierDao();
    }

    public void persist(Supplier entity) {
        supplierDao.persist(entity);
    }

    public void update(Supplier entity) {
        supplierDao.update(entity);
    }

    public Supplier findById(Integer id) {
        return supplierDao.findById(id);
    }

    public List<Supplier> findAll() {
        return supplierDao.findAll();
    }

    public void delete(Integer id) {
        Supplier supplier = supplierDao.findById(id);
        supplierDao.delete(supplier);
    }

    public void deleteAll() {
        supplierDao.deleteAll();
    }

    public SupplierDao getSupplierDao() {
        return supplierDao;
    }
}
