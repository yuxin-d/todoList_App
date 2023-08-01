package test;

import model.RegularItem;
import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;

import java.text.ParseException;

public class TestUrgentItem extends TestItem{
    @BeforeEach
    void setUp() throws ParseException {
        testDate = "JUNE 8, 2017";
        testName = "CPSC210 Lecture ticket";
        testItem = new UrgentItem(testName, testDate);
    }
}
