package com.mayab.quality.loginunittest.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.invocation.InvocationOnMock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mayab.quality.loginunittest.dao.IDAOUser;
import com.mayab.quality.loginunittest.model.User;
import org.mockito.stubbing.Answer;

import java.util.HashMap;

public class UserServiceTest {
    private UserService service;
    private IDAOUser dao;
    private HashMap<Integer, User> db;

    @BeforeEach
    void setUp() throws Exception {
        dao = mock(IDAOUser.class);
        service = new UserService(dao);
        db = new HashMap<Integer, User>();
    }
    @Test
    public void whenPasswordShort(){
        // Initialize
        String shortPass = "123";
        String name = "user1";
        String email = "user1@email.com";
        User user = null;

        // Fake code for findUserByEmail & save methods
        when(dao.findByUserName(anyString())).thenReturn(user);
        when(dao.save(any(User.class))).thenReturn(0);


        // Exercise
        User result = service.createUser(name,email,shortPass);
        User expected = null;

        // Verify
        assertThat(result, is(expected));
    }
    @Test
    public void whenAllDataCorrect() {
        int sizeBefore = db.size();

        when(dao.findByUserName(anyString())).thenReturn(null);

    }
    @Test
    public void testUpdateUser() {

        User oldUser = new User("Old User","oldemail","oldPassword");
        oldUser.setId(1);
        db.put(1, oldUser);
        User newUser = new User("New User", "oldemail", "newpassword");
        newUser.setId(1);

        when(dao.findById(1)).thenReturn(oldUser);

        when(dao.updateUser(any(User.class))).thenAnswer(new Answer<User>() {
            public User answer(InvocationOnMock invocation) throws Throwable {
                User arg = (User) invocation.getArguments()[0];
                db.replace(arg.getId(), arg);
                return db.get(arg.getId());
            }
        });


        User result = service.updateuser(newUser);
        assertThat(result.getName(), is("New User"));
        assertThat(result.getPassword(), is("newpassword"));
    }

}
