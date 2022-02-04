package top.horizonask.hoawiki.authorization.controller;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindingResult;
import top.horizonask.hoawiki.authorization.security.services.UserDetailsImpl;
import top.horizonask.hoawiki.authorization.security.services.UserDetailsServiceImpl;
import top.horizonask.hoawiki.common.ApiStatus;
import top.horizonask.hoawiki.authorization.request.LoginRequest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class UserAuthControllerTest {

    @Mock
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UserAuthController userAuthController;

    @Mock
    private BindingResult bindingResultNoError;

    @Mock
    private BindingResult bindingResultHasError;

    @BeforeEach
    public void setup() {
        UserDetailsImpl user = new UserDetailsImpl();
        user.setUserId(1L);
        user.setUsername("user_nick_name");
        user.setEmail("user@user.com");
        user.setIsSuperAdmin(false);
        when(userDetailsServiceImpl.loadUserByUsername("user@user.com")).thenReturn(user);
        when(bindingResultNoError.hasErrors()).thenReturn(false);
        when(bindingResultHasError.hasErrors()).thenReturn(true);
    }

    @Test
    public void testAuthenticateUserWhenLogin() {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("user@user.com");
        loginRequest.setPassword("password");

        UserDetailsImpl user = new UserDetailsImpl();
        user.setUserId(1L);
        user.setUsername("user_nick_name");
        user.setEmail("user@user.com");
        user.setIsSuperAdmin(false);

        when(authenticationManager.authenticate(any()))
                .thenReturn(new UsernamePasswordAuthenticationToken(user, null));

        ResponseEntity<JSONObject> responseUtils = userAuthController.authenticateUser(loginRequest, bindingResultNoError);
        assertEquals(Objects.requireNonNull(responseUtils.getBody()).get("apiCode"), ApiStatus.API_RESPONSE_SUCCESS.getCode());
    }

    @Test
    public void testAuthenticateUserWhenLoginWithWrongParam() {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("user@user.com");
        loginRequest.setPassword("password");

        UserDetailsImpl user = new UserDetailsImpl();
        user.setUserId(1L);
        user.setUsername("user_nick_name");
        user.setEmail("user@user.com");
        user.setIsSuperAdmin(false);

        when(authenticationManager.authenticate(any()))
                .thenReturn(new UsernamePasswordAuthenticationToken(user, null));

        ResponseEntity<JSONObject> responseUtils = userAuthController.authenticateUser(loginRequest, bindingResultHasError);
        assertEquals(Objects.requireNonNull(responseUtils.getBody()).get("apiCode"), ApiStatus.API_RESPONSE_PARAM_BAD.getCode());
    }

    @Test
    public void testAuthenticateUserWithWrongPassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("user@user.com");
        loginRequest.setPassword("password");

        UserDetailsImpl user = new UserDetailsImpl();
        user.setUserId(1L);
        user.setUsername("user_nick_name");
        user.setEmail("user@user.com");
        user.setIsSuperAdmin(false);

        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("Wrong!"));
        loginRequest.setPassword("password1");
        ResponseEntity<JSONObject> responseUtils = userAuthController.authenticateUser(loginRequest, bindingResultNoError);
        assertEquals(Objects.requireNonNull(responseUtils.getBody()).get("apiCode"), ApiStatus.API_RESPONSE_USERNAME_PASSWORD_ERROR.getCode());
    }

}