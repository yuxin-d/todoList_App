package test;

import model.Password;
import model.User;
import model.UserSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserSystem {
    private UserSystem userSystem;

    @BeforeEach
    void setUp(){
        userSystem = new UserSystem();
    }

    @Test
    void testAccessVerification() {
        assertTrue(userSystem.accessVerification(0000, "admin"));
        assertFalse(userSystem.accessVerification(0010, "admin"));
        User u1 = new User("a", new Password(0001));
        userSystem.addUser(u1);
        assertTrue(userSystem.accessVerification(0001, "a"));
        userSystem.resetPasswords(u1,1000);
        assertFalse(userSystem.accessVerification(0001, "a"));
        assertTrue(userSystem.accessVerification(1000, "a"));
    }

    @Test
    void testAddUser(){
        assertEquals(1, userSystem.getUserInfo().size());
        userSystem.addUser("cq", 0000);
        assertEquals(2, userSystem.getUserInfo().size());
        User u1 = new User("a");
        userSystem.addUser(u1);
        assertEquals(3, userSystem.getUserInfo().size());
    }
}
