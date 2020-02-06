package dao;

import model.Category;
import model.Product;
import model.Supplier;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.*;
import java.util.List;

public class ProductDao extends AbstractDao implements BaseDao<Product, Integer> {

    @Override
    public void persist(Product entity) {
        abstractPersist(entity);
    }

    @Override
    public void update(Product entity) {
        abstractUpdate(entity);
    }

    @Override
    public Product findById(Integer id) {
        openCurrentSessionWithTransaction();
        Product product = currentSession.get(Product.class, id);
        closeCurrentSessionWithTransaction();
        return product;
    }

    @Override
    public void delete(Product entity) {
        abstractDelete(entity);
    }

    @Override
    public List<Product> findAll() {
        try {
            openCurrentSessionWithTransaction();
            List<Product> products = currentSession.createQuery("FROM Product", Product.class).list();
            closeCurrentSessionWithTransaction();
            return products;
        } catch (RuntimeException e) {
            currentTransaction.rollback();
            throw e;
        } finally {
            currentSession.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Product> findByCompanyNameAndCategoryNameUsingHqlNamedParams(String companyName, String categoryName) {
        try {
            openCurrentSessionWithTransaction();

            String hql = "from Product as prod inner join prod.supplier as sup inner join prod.category as cat where sup.companyName = :companyName and cat.categoryName = :categoryName";
            List<Product> products = currentSession.createQuery(hql)
                    .setParameter("companyName", companyName)
                    .setParameter("categoryName", categoryName)
                    .list();

            closeCurrentSessionWithTransaction();
            return products;
        } catch (RuntimeException e) {
            currentTransaction.rollback();
            throw e;
        } finally {
            currentSession.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Product> findByCompanyNameAndCategoryNameUsingHqlNoNamedParams(String companyName, String categoryName) {
        try {
            openCurrentSessionWithTransaction();

            String hql = "from Product as prod inner join prod.supplier as sup inner join prod.category as cat where sup.companyName ='" + companyName + "' and cat.categoryName = '" + categoryName + "'";
            List<Product> products = currentSession.createQuery(hql)
                    .list();

            closeCurrentSessionWithTransaction();
            return products;
        } catch (RuntimeException e) {
            currentTransaction.rollback();
            throw e;
        } finally {
            currentSession.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Product> findByCompanyNameAndCategoryNameUsingCriteria(String companyName, String categoryName) {
        try {
            openCurrentSessionWithTransaction();

            Criteria criteria = currentSession.createCriteria(Product.class, "prod");
            criteria.createAlias("prod.category", "cat");
            criteria.createAlias("prod.supplier", "sup");
            criteria.add(Restrictions.eq("sup.companyName", companyName));
            criteria.add(Restrictions.eq("cat.categoryName", categoryName));

            List<Product> products = criteria.list();
            closeCurrentSessionWithTransaction();
            return products;
        } catch (RuntimeException e) {
            currentTransaction.rollback();
            throw e;
        } finally {
            currentSession.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Product> findByCompanyNameAndCategoryNameUsingCriteriaQuery(String companyName, String categoryName) {
        try {
            openCurrentSessionWithTransaction();

            CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
            CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
            Root<Product> product = query.from(Product.class);
            Join<Product, Supplier> category = product.join("category", JoinType.INNER);
            Join<Supplier, Category> supplier = product.join("supplier", JoinType.INNER);
            query.where(criteriaBuilder.equal(category.get("categoryName"), categoryName),
                    criteriaBuilder.equal(supplier.get("companyName"), companyName));

            List<Product> products = currentSession.createQuery(query).getResultList();
            closeCurrentSessionWithTransaction();
            return products;

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
            List<Product> products = currentSession.createQuery("FROM Product", Product.class).list();
            products.forEach(this::delete);
            closeCurrentSessionWithTransaction();

        } catch (RuntimeException e) {
            currentTransaction.rollback();
            throw e;

        } finally {
            currentSession.close();
        }
    }
}
