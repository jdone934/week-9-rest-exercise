package edu.matc.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.entity.Author;
import edu.matc.entity.Book;
import edu.matc.persistence.AuthorDao;
import edu.matc.persistence.BookDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class RestService {
    BookDao bookDao = new BookDao();
    AuthorDao authorDao = new AuthorDao();
    ObjectMapper mapper = new ObjectMapper();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/books")
    public Response getAllBooks() throws JsonProcessingException {
        List<Book> books = bookDao.getAll();
        String output = mapper.writeValueAsString(books);

        return Response.status(200).entity(output).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/books/{id}")
    public Response getBookById(@PathParam("id") int id) throws JsonProcessingException {
        Book book = bookDao.getById(id);
        String output = mapper.writeValueAsString(book);

        return Response.status(200).entity(output).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/authors")
    public Response getAllAuthors() throws JsonProcessingException {
        List<Author> authors = authorDao.getAll();
        String output = mapper.writeValueAsString(authors);

        return Response.status(200).entity(output).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/authors/{id}")
    public Response getAuthorById(@PathParam("id") int id) throws JsonProcessingException {
        Author author = authorDao.getById(id);
        String output = mapper.writeValueAsString(author);

        return Response.status(200).entity(output).build();
    }
}