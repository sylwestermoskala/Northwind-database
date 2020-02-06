package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;


abstract class AbstractDao {

    protected Session currentSession;
    protected Transaction currentTransaction;


    protected Session openCurrentSession() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        return currentSession;
    }

    protected Session openCurrentSessionWithTransaction() {
        currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    protected void closeCurrentSession() {
        currentSession.close();
    }

    protected void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    protected void abstractPersist(Object entity) {
        try {
            openCurrentSessionWithTransaction();
            currentSession.save(entity);
            closeCurrentSessionWithTransaction();
        } catch (RuntimeException e) {
            currentTransaction.rollback();
            throw e;
        } finally {
            currentSession.close();
        }
    }

    protected void abstractUpdate(Object entity) {
        try {
            openCurrentSessionWithTransaction();
            currentSession.update(entity);
            closeCurrentSessionWithTransaction();
        } catch (RuntimeException e) {
            currentTransaction.rollback();
            throw e;
        } finally {
            currentSession.close();
        }
    }

    protected void abstractDelete(Object entity) {
        try {
            openCurrentSessionWithTransaction();
            currentSession.delete(entity);
            closeCurrentSessionWithTransaction();
        } catch (RuntimeException e) {
            currentTransaction.rollback();
            throw e;
        } finally {
            currentSession.close();
        }
    }
}
