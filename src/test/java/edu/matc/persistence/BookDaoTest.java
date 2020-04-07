package edu.matc.persistence;

import edu.matc.entity.Author;
import edu.matc.entity.Book;
import edu.matc.testUtils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** Unit test for BookDao
 *
 * @author Paula Waite
 */
class BookDaoTest {
    

    BookDao dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new BookDao();
    }

    /**
     * Verify successful retrieval of a Book
     */
    @Test
    void getByIdSuccess() {
        Book retrievedBook = dao.getById(1);
        assertEquals("Head First Java, 2nd Edition", retrievedBook.getTitle());
        assertEquals("978-0596009205", retrievedBook.getIsbn());
        assertEquals(2005, retrievedBook.getPublicationYear());
    }

    /**
     * Verify successful insert of a Book
     */
    @Test
    void insertSuccess() {
        Author author = new Author(1, "Kathy", "Sierra");
        Book newBook = new Book("My Test Book", author, "test isbn", 2000);
        int id = dao.insert(newBook);
        assertNotEquals(0,id);
        Book insertedBook = dao.getById(id);
        assertEquals("My Test Book", insertedBook.getTitle());
        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // TODO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/Bookguide/html_single/Hibernate_Book_Guide.html#mapping-model-pojo-equalshashcode
    }

    /**
     * Verify successful update of a Book
     */
    @Test
    void updateSuccess() {
        Author author = new Author(3, "Joseph", "Ottinger");
        Book bookToUpdate = dao.getById(2);
        bookToUpdate.setAuthor(author);
        dao.saveOrUpdate(bookToUpdate);
        Book bookAfterUpdate = dao.getById(2);
        assertEquals(author, bookAfterUpdate.getAuthor());
    }

    /**
     * Verify successful delete of Book
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(3));
        assertNull(dao.getById(3));
    }

    /**
     * Verify successful retrieval of all Books
     */
    @Test
    void getAllSuccess() {
        List<Book> Books = dao.getAll();
        assertEquals(3, Books.size());
    }
}