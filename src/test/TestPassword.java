package test;

import model.Password;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPassword {
    Password testPassword;

    @BeforeEach
    void setUp(){
        testPassword = new Password(1111);
    }

    @Test
    void testSetUser(){
        User user = new User("testUser");
        testPassword.setUser(user);
        assertEquals(user,testPassword.getUser());
        assertEquals(testPassword, user.getPasswords());
    }

    @Test
    void testGetPW(){
        assertEquals(1111, testPassword.getPw());
    }
}
