package edu.matc.persistence;

import edu.matc.entity.Author;
import edu.matc.entity.Book;
import edu.matc.testUtils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

// TODO add tests for each method on the AuthorDao
// TODO remember to test that books can be added when adding an author
// TODO test that when an author is deleted, all associated books are deleted
public class AuthorDaoTest {
    AuthorDao dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new AuthorDao();
    }

    /**
     * Verify successful retrieval of a Author
     */
    @Test
    void getByIdSuccess() {
        Author retrievedAuthor = dao.getById(1);
        Author expectedAuhtor = new Author(1, "Kathy", "Sierra");
    }

    /**
     * Verify successful insert of a Author
     */
    @Test
    void insertSuccess() {
        Author newAuthor = new Author(4, "Jacob", "Doney");
        int id = dao.insert(newAuthor);
        assertNotEquals(0,id);
        Author insertedAuthor = dao.getById(id);
        assertEquals(newAuthor, insertedAuthor);
    }

    /**
     * Verify successful insert of a Author with a Book
     */
    @Test
    void insertWithBookSuccess() {
        Author newAuthor = new Author(4, "Jacob", "Doney");
        Book bookForAuthor = new Book("Test Book", newAuthor, "test my isbn", 2020);

        newAuthor.addBook(bookForAuthor);

        int id = dao.insert(newAuthor);

        assertNotEquals(0,id);
        Author insertedAuthor = dao.getById(id);
        assertEquals(newAuthor, insertedAuthor);
        assertEquals(1, insertedAuthor.getBooks().size());
    }

    /**
     * Verify successful update of a Author
     */
    @Test
    void updateSuccess() {
        Author expectedAuthor = new Author(3, "Joseph", "Doney");
        Author authorToUpdate = dao.getById(3);
        authorToUpdate.setLastName(expectedAuthor.getLastName());
        dao.saveOrUpdate(authorToUpdate);
        Author authorAfterUpdate = dao.getById(3);
        assertEquals(expectedAuthor, authorAfterUpdate);
    }

    /**
     * Verify successful delete of Author
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(3));
        assertNull(dao.getById(3));
    }

    /**
     * Verify successful retrieval of all Authors
     */
    @Test
    void getAllSuccess() {
        List<Author> authors = dao.getAll();
        assertEquals(3, authors.size());
    }
}
