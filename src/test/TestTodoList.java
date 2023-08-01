package test;

import model.Exception.DateIncorrectFormatException;
import model.Exception.TooManyThingsException;
import model.Exception.TooManyUrgentItemException;
import model.Password;
import model.RegularItem;
import model.TodoList;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TestTodoList {
    TodoList testTodoList;
    private RegularItem testRegularItem1;
    private String testDate1;
    private String testName1;
    private RegularItem testRegularItem2;
    private String testDate2;
    private String testName2;

    @BeforeEach
    void setUp() throws ParseException {
        testTodoList = new TodoList();
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        testDate1 = "JUNE 8, 2017";
        testName1 = "CPSC210 Lecture ticket";
        testRegularItem1 = new RegularItem(testName1, testDate1);
        testDate2 = "JULY 30, 2019";
        testName2 = "CPSC121 Lecture ticket";
        testRegularItem2 = new RegularItem(testName2, testDate2);
    }

    @Test
    void testAddItemTodoList() throws TooManyThingsException, TooManyUrgentItemException {
        assertEquals(0, testTodoList.size());
        testTodoList.addItem(testRegularItem1);
        assertEquals(1,testTodoList.size());
    }

    @Test
    void testCrossOff() throws TooManyThingsException, TooManyUrgentItemException {
        testTodoList.addItem(testRegularItem1);
        assertEquals("In-progress",testTodoList.getItem(0).getStatus());
        testTodoList.crossedOffItem(0);
        assertEquals("Done",testTodoList.getItem(0).getStatus());
    }

    @Test
    void tesCrossOffInvalidIndex() throws TooManyThingsException, TooManyUrgentItemException {
        testTodoList.addItem(testRegularItem1);
        assertEquals("In-progress",testTodoList.getItem(0).getStatus());
        testTodoList.crossedOffItem(8);
        assertEquals("In-progress",testTodoList.getItem(0).getStatus());
    }

    @Test
    void testCheckOverDueOneItemOverDue() throws DateIncorrectFormatException, TooManyThingsException, TooManyUrgentItemException {
        testTodoList.addItem(testRegularItem1);
        testTodoList.addItem(testRegularItem2);
        assertEquals("In-progress",testTodoList.getItem(0).getStatus());
        assertEquals("In-progress",testTodoList.getItem(1).getStatus());
        testTodoList.checkOverDue();
        assertEquals("Overdue",testTodoList.getItem(0).getStatus());
        assertEquals("In-progress",testTodoList.getItem(1).getStatus());
    }

    @Test
    void testCheckOverDueNoItemOverDue() throws TooManyThingsException, TooManyUrgentItemException, DateIncorrectFormatException {
        testTodoList.addItem(testRegularItem1);
        testTodoList.addItem(testRegularItem2);
        testTodoList.crossedOffItem(0);
        assertEquals("Done",testTodoList.getItem(0).getStatus());
        assertEquals("In-progress",testTodoList.getItem(1).getStatus());
        testTodoList.checkOverDue();
        assertEquals("Done",testTodoList.getItem(0).getStatus());
        assertEquals("In-progress",testTodoList.getItem(1).getStatus());
    }

//    @Test
//    void testAccessVerification() {
//        assertTrue(testTodoList.accessVerification(0000, "admin"));
//        assertFalse(testTodoList.accessVerification(0010, "admin"));
//        User u1 = new User("a", new Password(0001));
//        testTodoList.addUser(u1);
//        assertTrue(testTodoList.accessVerification(0001, "a"));
//        testTodoList.resetPasswords(u1,1000);
//        assertFalse(testTodoList.accessVerification(0001, "a"));
//        assertTrue(testTodoList.accessVerification(1000, "a"));
//    }
//
//    @Test
//    void testAddUser(){
//        assertEquals(1, testTodoList.getUserSystem().size());
//        testTodoList.addUser("cq", 0000);
//        assertEquals(2, testTodoList.getUserSystem().size());
//        User u1 = new User("a");
//        testTodoList.addUser(u1);
//        assertEquals(3, testTodoList.getUserSystem().size());
//    }


    }
