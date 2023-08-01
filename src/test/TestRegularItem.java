package test;

import model.RegularItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class TestRegularItem extends TestItem {
    @BeforeEach
    void setUp() throws ParseException {
        testDate = "JUNE 8, 2017";
        testName = "CPSC210 Lecture ticket";
        testItem = new RegularItem(testName, testDate);
    }


}
