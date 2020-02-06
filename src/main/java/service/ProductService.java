package service;

import dao.ProductDao;
import model.Product;

import java.util.List;

public class ProductService {

    private final ProductDao productDao;

    public ProductService() {
        productDao = new ProductDao();
    }

    public void persist(Product entity) {
        productDao.persist(entity);
    }

    public void update(Product entity) {
        productDao.update(entity);
    }

    public Product findById(Integer id) {
        return productDao.findById(id);
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public void delete(Integer id) {
        Product Product = productDao.findById(id);
        productDao.delete(Product);
    }

    public List<Product> findByCompanyNameAndCategoryNameUsingHqlNamedParams(String companyName, String categoryName) {
        return productDao.findByCompanyNameAndCategoryNameUsingHqlNamedParams(companyName, categoryName);
    }

    public List<Product> findByCompanyNameAndCategoryNameUsingHqlNoNamedParams(String companyName, String categoryName) {
        return productDao.findByCompanyNameAndCategoryNameUsingHqlNoNamedParams(companyName, categoryName);
    }

    public List<Product> findByCompanyNameAndCategoryNameUsingCriteria(String companyName, String categoryName) {
        return productDao.findByCompanyNameAndCategoryNameUsingCriteria(companyName, categoryName);
    }

    public List<Product> findByCompanyNameAndCategoryNameUsingCriteriaQuery(String companyName, String categoryName) {
        return productDao.findByCompanyNameAndCategoryNameUsingCriteriaQuery(companyName, categoryName);
    }

    public void deleteAll() {
        productDao.deleteAll();
    }

    public ProductDao getProductDao() {
        return productDao;
    }
}
