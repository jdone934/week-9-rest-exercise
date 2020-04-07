package edu.matc.api;

import edu.matc.entity.Author;
import edu.matc.persistence.AuthorDao;
import edu.matc.testUtils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static org.junit.jupiter.api.Assertions.*;

class RestServiceTest {
    AuthorDao authorDao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        authorDao = new AuthorDao();
    }

    @Test
    void getAllAuthors() {
//        Client client = ClientBuilder.newClient();
//        WebTarget target =
//                client.target("http://localhost:8080/week_9_rest_exercise_war/api/author");
//        String response = target.request("text/plain").get(String.class);
//
//        Author author = authorDao.getById(0);
//        String expectedLastName = author.getLastName();
//        assertEquals(expectedLastName, response);
    }
}