package test;

import model.BusinessItem;
import org.junit.jupiter.api.BeforeEach;

import java.text.ParseException;

public class TestBusinessItem extends TestItem{
    @BeforeEach
    void setUp() throws ParseException {
        testDate = "JUNE 8, 2017";
        testName = "CPSC210 Lecture ticket";
        testItem = new BusinessItem(testName, testDate);
    }
}
