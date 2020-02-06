package dao;

import model.Category;

import java.util.List;

public class CategoryDao extends AbstractDao implements BaseDao<Category, Integer> {

    @Override
    public void persist(Category entity) {
        abstractPersist(entity);
    }

    @Override
    public void update(Category entity) {
        abstractUpdate(entity);
    }

    @Override
    public Category findById(Integer id) {
        openCurrentSessionWithTransaction();
        Category category = currentSession.get(Category.class, id);
        closeCurrentSessionWithTransaction();
        return category;
    }

    @Override
    public void delete(Category entity) {
        abstractDelete(entity);
    }

    @Override
    public List<Category> findAll() {
        openCurrentSessionWithTransaction();
        List<Category> categories = currentSession.createQuery("FROM Category", Category.class).list();
        closeCurrentSessionWithTransaction();
        return categories;
    }

    @Override
    public void deleteAll() {
        try {
            openCurrentSessionWithTransaction();
            List<Category> categories = currentSession.createQuery("FROM Category", Category.class).list();
            categories.forEach(this::delete);
            closeCurrentSessionWithTransaction();
        } catch (RuntimeException e) {
            currentTransaction.rollback();
            throw e;
        } finally {
            currentSession.close();
        }
    }
}
