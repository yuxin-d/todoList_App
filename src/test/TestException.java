package test;

import model.*;
import model.Exception.TooManyThingsException;
import model.Exception.TooManyUrgentItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.fail;


public class TestException {
    Item regularItem;
    Item businessItem;
    Item urgentItem;
    TodoList testTodoList;

    @BeforeEach
    void setUp() throws ParseException {
       regularItem = new RegularItem("regularItem", "OCOTOBER 20, 2018");
       businessItem = new BusinessItem("businessItem", "OCTOBER 20, 2018");
       urgentItem = new UrgentItem("urgentItem", "OCTOBER 20, 2018");
       testTodoList = new TodoList();
    }

    @Test
    void testNotTooManyUrgentItem(){
        for (int i=0 ; i<5 ; i++){
            try {
                testTodoList.addItem(urgentItem);
            } catch (TooManyUrgentItemException e) {
                fail("Unexpected exception");
            } catch (TooManyThingsException e) {
                fail("Unexpected exception");
            }
        }
    }

    @Test
    void testTooManyUrgentItem(){
        for (int i=0 ; i<5 ; i++) {
            try {
                testTodoList.addItem(urgentItem);
            } catch (TooManyUrgentItemException e) {
                fail("Unexpected exception");
            } catch (TooManyThingsException e) {
                fail("Unexpected exception");
            }
        }
        try {
            testTodoList.addItem(urgentItem);
            fail("TooManyUrgentItemException should be thrown");
        } catch (TooManyUrgentItemException e) {
            System.out.println("Expected");
        } catch (TooManyThingsException e) {
            e.printStackTrace();
            fail("Unexpected");
        }
    }

    @Test
    void testNotTooManyThings() {
        for (int i = 0; i < 10; i++) {
            try {
                testTodoList.addItem(regularItem);
            } catch (TooManyUrgentItemException e) {
                fail("Unexpected exception");
            } catch (TooManyThingsException e) {
                fail("Unexpected exception");
            }
        }
    }

    @Test
    void testTooManyThings(){
        for (int i = 0; i < 25; i++) {
            try {
                testTodoList.addItem(regularItem);
            } catch (TooManyUrgentItemException e) {
                fail("Unexpected exception");
            } catch (TooManyThingsException e) {
                fail("Unexpected exception");
            }
        }
        try {
            testTodoList.addItem(businessItem);
            fail("TooManyThingsException should be thrown");
        } catch (TooManyUrgentItemException e) {
            fail("Unexpected exception");
        } catch (TooManyThingsException e) {
            System.out.println("expected");
        }
    }

    @Test
    void testTooManyUrgentItemTooManyThings(){
        for (int i = 0; i < 5; i++) {
            try {
                testTodoList.addItem(urgentItem);
            } catch (TooManyUrgentItemException e) {
                fail("Unexpected exception");
            } catch (TooManyThingsException e) {
                fail("Unexpected exception");
            }
        }
        for (int i = 0; i < 20; i++) {
            try {
                testTodoList.addItem(regularItem);
            } catch (TooManyUrgentItemException e) {
                fail("Unexpected exception");
            } catch (TooManyThingsException e) {
                fail("Unexpected exception");
            }
        }
        try {
            testTodoList.addItem(urgentItem);
            fail("TooManyUrgentItem should be thrown");
        } catch (TooManyUrgentItemException e) {
            System.out.println("exception expected");
        } catch (TooManyThingsException e) {
            fail("Unexpected exception");
        }

    }



}
