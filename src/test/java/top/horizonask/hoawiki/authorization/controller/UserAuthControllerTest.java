package top.horizonask.hoawiki.authorization.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;
import top.horizonask.hoawiki.authorization.security.services.UserDetailsImpl;
import top.horizonask.hoawiki.authorization.security.services.UserDetailsServiceImpl;
import top.horizonask.hoawiki.common.ResponseUtils;
import top.horizonask.hoawiki.common.request.LoginRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserAuthControllerTest {

    @Mock
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Spy
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UserAuthController userAuthController;

    @Before
    public void setup() {
        UserDetailsImpl user = new UserDetailsImpl();
        user.setUserId(1L);
        user.setUsername("user_nick_name");
        user.setEmail("user@user.com");
        user.setIsSuperAdmin(false);
        when(userDetailsServiceImpl.loadUserByUsername("user@user.com")).thenReturn(user);
    }

    @Test
    public void testAuthenticateUserWhenLogin() {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("user@user.com");
        loginRequest.setPassword("password");

        ResponseUtils responseUtils = userAuthController.authenticateUser(loginRequest);
        assertEquals(responseUtils.getApiCode(), 10200);
    }
}