package test;

import model.BusinessItem;
import model.Exception.TooManyThingsException;
import model.Exception.TooManyUrgentItemException;
import model.RegularItem;
import model.TodoList;
import model.UserSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSaveable {
    private TodoList todo;
    private UserSystem user;

    @BeforeEach
    void setup() throws IOException {
        todo = new TodoList();
        todo.emptyFile("src/testSave");

    }

    @Test
    void testSaveItem() throws IOException, ParseException, TooManyThingsException, TooManyUrgentItemException {
        todo.load("src/testSave");
        assertEquals(0, todo.size());
        todo.addItem(new RegularItem("BBBBB", "OCTOBER 1, 2018"));
        todo.addItem(new BusinessItem("CCCCC", "AUGUST 29, 2018"));
        todo.save("src/testSave");
        TodoList newTodo = new TodoList();
        newTodo.load("src/testSave");
        assertEquals(2,todo.size());
    }

    @Test
    void testEmptyFile() throws IOException, ParseException, TooManyThingsException, TooManyUrgentItemException {
        todo.addItem(new RegularItem("BBBBB", "OCTOBER 1, 2018"));
        todo.addItem(new BusinessItem("CCCCC", "AUGUST 29, 2018"));
        todo.save("src/testSave");

        TodoList newTodo = new TodoList();
        newTodo.load("src/testSave");
        assertEquals(2,todo.size());

        todo.emptyFile("src/testSave");

        newTodo = new TodoList();
        newTodo.load("src/testSave");
        assertEquals(2,todo.size());
    }

    @Test
    void testSaveUser() throws IOException {
        user.load("src/testSave");
        assertEquals(1, user.getUserInfo().size());
        user.addUser("AAAA", 0000);
        user.addUser("BBBB", 1234);
        assertEquals(3,user.getUserInfo().size());
        user.save("src/testSave");
        UserSystem newUser = new UserSystem();
        newUser.load("src/testSave");
        assertEquals(3,newUser.getUserInfo().size());
    }

}
