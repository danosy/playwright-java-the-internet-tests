package tests.api;

import api.client.TodosClient;
import api.model.Todo;
import api.utils.PrintUtils;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APITest {
    private TodosClient todosClient;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector();
        todosClient = injector.getInstance(TodosClient.class);
    }

    @Test
    void testGetRequest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    void getAllTodos() {
        List<Todo> allTodos = todosClient.getAll();
        PrintUtils.printList(allTodos
        );
    }

    @Test
    void getAllTodoById() {
        Todo todoById = todosClient.getById(1);
        System.out.println(todoById);
    }
}