package edu.matc.persistence;

import edu.matc.entity.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Get Book by id
     */
    public Book getById(int id) {
        Session session = sessionFactory.openSession();
        Book Book = session.get( Book.class, id );
        session.close();
        return Book;
    }

    /**
     * update Book
     * @param book  Book to be inserted or updated
     */
    public void saveOrUpdate(Book book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(book);
        transaction.commit();
        session.close();
    }

    /**
     * insert Book
     * @param book  Book to be inserted
     */
    public int insert(Book book) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(book);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete a Book
     * @param book Book to be deleted
     */
    public void delete(Book book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(book);
        transaction.commit();
        session.close();
    }


    /** Return a list of all Books
     *
     * @return All Books
     */
    public List<Book> getAll() {

        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery( Book.class );
        Root<Book> root = query.from( Book.class );
        List<Book> Books = session.createQuery( query ).getResultList();

        logger.debug("The list of Books " + Books);
        session.close();

        return Books;
    }

}
