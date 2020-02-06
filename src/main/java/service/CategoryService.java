package service;

import dao.CategoryDao;
import model.Category;

import java.util.List;

public class CategoryService {

    private final CategoryDao categoryDao;

    public CategoryService() {
        categoryDao = new CategoryDao();
    }

    public void persist(Category entity) {
        categoryDao.persist(entity);
    }

    public void update(Category entity) {
        categoryDao.update(entity);
    }

    public Category findById(Integer id) {
        return categoryDao.findById(id);
    }

    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    public void delete(Integer id) {
        Category category = categoryDao.findById(id);
        categoryDao.delete(category);
    }

    public void deleteAll() {
        categoryDao.deleteAll();
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }
}
