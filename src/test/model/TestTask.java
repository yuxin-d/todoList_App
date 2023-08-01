package model;

import model.exceptions.EmptyStringException;
import model.exceptions.InvalidProgressException;
import model.exceptions.NegativeInputException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parsers.exceptions.ParsingException;

import java.util.Calendar;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestTask {

    Task task;


    @BeforeEach
    void SetUp() {

        task = new Task("Course");

    }


    @Test
    void testTaskWithoutDescription() {
        try {
            Task t = new Task("");
            fail();
        } catch (EmptyStringException e) {
            //expected
        }
    }

    @Test
    void testTaskWithNullDescription() {
        try {
            Task t = new Task(null);
            fail();
        } catch (EmptyStringException e) {
            //expected
        }
    }

    @Test
    void testAddTagWithoutName() {
        try {
            task.addTag("");
            fail();
        } catch (EmptyStringException e) {
            //expected
        }
    }

//    @Test
//    void testAddTagWithNull() {
//        try {
//            task.addTag(null);
//            fail();
//        } catch (EmptyStringException e) {
//            //expected
//        }
//    }

//    @Test
//    void testRemoveNullTag() {
//        try {
//            Task t = new Task("jiji");
//            t.addTag("Go");
//            t.removeTag(null);
//            fail();
//        } catch (EmptyStringException e) {
//            //expected
//        }
//    }

    @Test
    void testRemoveNo_nameTag() {
        try {
            Task t = new Task("hehe");
            t.addTag("Away");
            t.removeTag("");
            fail();
        } catch (EmptyStringException e) {
            //expected
        }
    }

    @Test
    void testAddAndRemove() {
        task.addTag("Math");
        Set<Tag> p = task.getTags();
        assertEquals(1, p.size());
        assertTrue(task.containsTag("Math"));
        assertFalse(task.containsTag("CS"));

        task.removeTag("Math");
        p = task.getTags();
        assertEquals(0, p.size());
        assertFalse(task.containsTag("Math"));
        try {
            task.containsTag((Tag)null);
            fail();
        } catch (NullArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            task.containsTag("");
            fail();
        } catch (EmptyStringException e) {
            System.out.println(e.getMessage());
        }

        try {
            task.containsTag((String) null);
            fail();
        } catch (EmptyStringException e) {
            System.out.println(e.getMessage());
        }

        Priority o = new Priority(1);
        task.setPriority(o);
        assertTrue(task.getPriority().isImportant());
        assertTrue(task.getPriority().isUrgent());

        task.setDescription("CS");
        assertEquals("CS", task.getDescription());

        assertEquals(task.getDueDate(), null);
        DueDate dueDate2 = new DueDate();
        task.setDueDate(dueDate2);
        assertEquals(task.getDueDate(), dueDate2);

        task.addTag("CS");
        System.out.println(task.toString());


        task.setDueDate(null);
        System.out.println(task.toString());
    }

    @Test
    void testSetNo_NameDescriptionForTask() {
        try {
            Task t = new Task("jaja");
            t.setDescription("");
            fail();
        } catch (EmptyStringException e) {
            //expected
        }
    }

    @Test
    void testSetNullDescriptionForTask() {
        try {
            Task t = new Task("hehe");
            t.setDescription(null);
            fail();
        } catch (EmptyStringException e) {
            //expected
        }
    }

    @Test
    void testSetNullPriority() {
        try {
            task.setPriority(null);
            fail();
        } catch (NullArgumentException e) {
            //expected
        }
    }

    @Test
    void testSetNullStatus() {
        try {
            task.setStatus(null);
            fail();
        } catch (NullArgumentException e) {
            //expected
        }

    }

    @Test
    void testGetProgress() {
        task.setProgress(80);
        assertEquals(80,task.getProgress());
    }

    @Test
    void testProgressInvalid() {
        try {
            task.setProgress(-1);
            fail();
        } catch (InvalidProgressException e) {
            //expected
        }
    }

    @Test
    void testProgressTooLargeIsInvalid() {
        try {
            task.setProgress(102);
            fail();
        } catch (InvalidProgressException e) {
            //expected
        }
    }

    @Test
    void testETCHoursValid() {
        task.setEstimatedTimeToComplete(30);
        assertEquals(30,task.getEstimatedTimeToComplete());
    }

    @Test
    void testETCHoursInvalid() {
        try {
            task.setEstimatedTimeToComplete(-20);
            fail();
        } catch(NegativeInputException e) {
            //expected
        }
    }

    @Test
    void testTwoNotEqualObjectsWithOnlySameDescription() {
        Task task1 = new Task("Course");
        DueDate dd = new DueDate();
        task1.setDueDate(dd);
        assertFalse(task.equals(task1));
        task1.setDueDate(null);
        assertTrue(task.equals(task1));
        task1.setDescription("eat");
        assertFalse(task.equals(task1));
        task1.setDescription("Course");
        assertTrue(task.equals(task1));
        Task t1 = new Task("eat");
        Task t2 = new Task("eat");
        t1.setPriority(new Priority(1));
        t2.setPriority(new Priority(2));
        assertTrue(t1.equals(t2));
    }




}
