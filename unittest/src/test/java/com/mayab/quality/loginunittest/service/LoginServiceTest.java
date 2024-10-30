package com.mayab.quality.loginunittest.service;

import com.mayab.quality.loginunittest.dao.IDAOUser;
import com.mayab.quality.loginunittest.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.mockito.Mockito.*;

class LoginServiceTest {

    private LoginService loginService;
    private IDAOUser daoMock;
    private User mockUser;

    @BeforeEach
    void setUp() {
        daoMock = Mockito.mock(IDAOUser.class);
        loginService = new LoginService(daoMock);
        mockUser = Mockito.mock(User.class);
    }

    @Test
    void testLoginSuccess() {
        when(daoMock.findByUserName("User1")).thenReturn(mockUser);
        when(mockUser.getPassword()).thenReturn("password123");
        boolean result = loginService.login("User1", "password123");
        assertThat(result, is(true));
        if (result) {
            System.out.println("Login exitoso para el email: test@example.com");
        }
    }
}