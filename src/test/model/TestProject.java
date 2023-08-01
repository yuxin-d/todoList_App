package model;

import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


public class TestProject {
    private Project p1;
    private Project p2;

    @BeforeEach
    public void SetUp() {
        p1 = new Project("eat an apple");
        p2 = new Project("have you eat?");
        Todo t1 = new Task("Buy milk##today; important");
        Todo t2 = new Task("Prepare the exam## important;");
        p1.add(t1);
        p1.add(t2);
        ((Task) t1).setStatus(Status.DONE);
        ((Task) t2).setStatus(Status.TODO);
        ((Task) t1).setEstimatedTimeToComplete(20);
        ((Task) t2).setEstimatedTimeToComplete(20);
        ((Task) t1).setProgress(50);
        ((Task) t2).setProgress(10);

    }

    @Test
    void testAddInValidTask() {
        try {
            p1.add(null);
            fail();
        } catch (NullArgumentException e) {
            //
        }
    }

    @Test
    void testAddValidTask() {
        Todo todo = new Task("prepare for the exam## important;today;");
        p1.add(todo);
        assertEquals(3, p1.getNumberOfTasks());
        assertTrue(p1.contains(todo));
        p1.add(todo);
        assertEquals(3, p1.getNumberOfTasks());
    }

    @Test
    void testRemoveTask() {
        int kk = p1.getNumberOfTasks();
        Todo tt = new Task("666 ## not important;");
        p1.add(tt);
        assertEquals(3, p1.getNumberOfTasks());
        p1.remove(tt);
        int hh = p1.getNumberOfTasks();
        assertEquals(hh, kk);
    }

    @Test
    void testCannotRemoveTask() {
        Todo t3 = new Task("eat you food## not urgent;");
        p1.remove(t3);
        assertEquals(2, p1.getNumberOfTasks());
        try {
            p1.remove(null);
            fail();
        } catch (NullArgumentException e) {
            //expected
        }
    }

    @Test
    void testGetETimeToComplete() {
        assertEquals(40, p1.getEstimatedTimeToComplete());
    }

    @Test
    void testGetTasks() {
        try {
            p1.getTasks();
            fail();
        } catch (UnsupportedOperationException e) {
            //expected
        }
    }

    @Test
    void testGetNoContentProgress() {
        assertEquals(0,p2.getProgress());
        assertEquals(30,p1.getProgress());
    }

    @Test
    void testIsSureCompleted() {
        assertFalse(p2.isCompleted());
        Todo t1 = new Task("go eat##today;Done;");
        ((Task) t1).setProgress(50);
        assertFalse(p2.isCompleted());
        ((Task) t1).setProgress(100);
        p2.add(t1);
        assertTrue(p2.isCompleted());
        assertTrue(p2.contains(t1));
        assertFalse(p1.contains(t1));
        try {
            p1.contains(null);
            fail();
        } catch (NullArgumentException e) {
            //expected
        }
        Todo t2 = new Task("go dinner## not urgent");
        ((Task) t2).setProgress(50);
        p2.add(t2);
        assertFalse(p2.isCompleted());

    }

    @Test
    void testEquals() {
        assertFalse(p1.equals(p2));
        Project p3 = p1;
        assertTrue(p1.equals(p3));
    }

    @Test
    void testInstanceOf() {
        Task tt = new Task("do what");
        assertFalse(p1.equals(tt));
    }

    @Test
    void testGetDescription() {
        System.out.println(p1.getDescription());
        System.out.println(p1.hashCode());
    }

   @Test
    void testIterator() {
        System.out.println(p1.iterator().hasNext());
       Iterator<Todo> ppp = p1.iterator();
        Todo t1 = new Task("Buy bread##important;urgent;tomorrow;");
        Todo t2 = new Task("drink some milk##tomorrow;haha");
        Todo t3 = new Task("Do you like me##urgent;today;");
        p1.add(t1);
        p1.add(t2);
        p1.add(t3);
//        System.out.println(p1.iterator().next().description);
//       System.out.println(p1.iterator().next().etcHours);
//       System.out.println(p1.iterator().next().priority);
//       System.out.println(p1.iterator().next().progress);
//       assertTrue(p1.iterator().hasNext());
       while(ppp.hasNext()){
           System.out.println(ppp.next());
       }
       System.out.println(t1.getProgress());
       Project pp = new Project("hhh juewang");
       pp.add(t2);
       p1.add(pp);
       assertEquals(ppp.next(),pp);
       //assertEquals(ppp.next(),pp);
       try{
           ppp.next();
           fail();
       } catch (NoSuchElementException aidsaop){
           //expected
       }

   }

}