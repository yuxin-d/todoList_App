package test;

import model.Password;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestUser {
    User testUser;


    @BeforeEach
    void setUp(){
        testUser = new User("testUser");
    }

    @Test
    void testGetPasswords(){
        assertEquals(0000, testUser.getPasswords().getPw());
    }

    @Test
    void testSetPasswords(){
        assertEquals(0000, testUser.getPasswords().getPw());
        Password oneTwoThreeFour = new Password(1234);
        testUser.setPasswords(oneTwoThreeFour);
        assertEquals(1234, testUser.getPasswords().getPw());
        assertEquals(testUser, oneTwoThreeFour.getUser());
    }

    @Test
    void testGetUserName(){
        assertEquals("testUser", testUser.getUserName());
    }
}
