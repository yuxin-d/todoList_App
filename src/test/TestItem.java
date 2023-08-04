package test;

import model.Item;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class TestItem {
    protected Item testItem;
    protected String testDate;
    protected String testName;

    @Test
    void testConstructor(){
        assertEquals(testName, testItem.getName());
        assertEquals(testDate, testItem.getDueDate());
        assertEquals("In-progress", testItem.getStatus());
    }

    @Test
    void testsetName(){
        assertEquals(testName, testItem.getName());
        testItem.setName("CPSC 210 P3");
        assertEquals("CPSC 210 P3", testItem.getName());
    }

    @Test
    void testsetDate() throws ParseException {
        assertEquals(testDate, testItem.getDueDate());
        String newDate = "JUNE 10, 2017";
        testItem.setDueDate(newDate);

        assertEquals(newDate, testItem.getDueDate());
    }

    @Test
    void testsetStatus(){
        assertEquals("In-progress", testItem.getStatus());
        testItem.setStatus("done");
        assertEquals("done", testItem.getStatus());
    }

    @Test
    void testToString(){
        System.out.println(testItem.toString());
    }
}
