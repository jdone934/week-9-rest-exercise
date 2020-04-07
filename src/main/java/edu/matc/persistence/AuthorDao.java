package edu.matc.persistence;

import edu.matc.entity.Author;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AuthorDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Get Author by id
     */
    public Author getById(int id) {
        Session session = sessionFactory.openSession();
        Author Author = session.get( Author.class, id );
        session.close();
        return Author;
    }

    /**
     * update Author
     * @param author  Author to be inserted or updated
     */
    public void saveOrUpdate(Author author) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(author);
        transaction.commit();
        session.close();
    }

    /**
     * insert Author
     * @param author  Author to be inserted
     */
    public int insert(Author author) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(author);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete a Author
     * @param author Author to be deleted
     */
    public void delete(Author author) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(author);
        transaction.commit();
        session.close();
    }


    /** Return a list of all Authors
     *
     * @return All Authors
     */
    public List<Author> getAll() {

        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Author> query = builder.createQuery( Author.class );
        Root<Author> root = query.from( Author.class );
        List<Author> Authors = session.createQuery( query ).getResultList();

        logger.debug("The list of Authors " + Authors);
        session.close();

        return Authors;
    }
}
