package top.horizonask.hoawiki.common.request;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RegisterRequestTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testRegisterRequest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("UserName");
        registerRequest.setPassword("11111111");
        registerRequest.setUserEmail("User@test.com");
        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testRegisterRequestUserNameWrong(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName(";");
        registerRequest.setPassword("11111111");
        registerRequest.setUserEmail("User@test.com");
        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        for(ConstraintViolation<RegisterRequest> violation:violations){
            assertEquals(violation.getPropertyPath().toString(),"userName");
        }
        registerRequest.setUserName("");
        violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        for(ConstraintViolation<RegisterRequest> violation:violations){
            assertEquals(violation.getPropertyPath().toString(),"userName");
        }

        registerRequest.setUserName("\n");
        violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        for(ConstraintViolation<RegisterRequest> violation:violations){
            assertEquals(violation.getPropertyPath().toString(),"userName");
        }

        registerRequest.setUserName("*");
        violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        for(ConstraintViolation<RegisterRequest> violation:violations){
            assertEquals(violation.getPropertyPath().toString(),"userName");
        }

        registerRequest.setUserName("#");
        violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        for(ConstraintViolation<RegisterRequest> violation:violations){
            assertEquals(violation.getPropertyPath().toString(),"userName");
        }

        registerRequest.setUserName("()");
        violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        for(ConstraintViolation<RegisterRequest> violation:violations){
            assertEquals(violation.getPropertyPath().toString(),"userName");
        }

        registerRequest.setUserName("\\");
        violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        for(ConstraintViolation<RegisterRequest> violation:violations){
            assertEquals(violation.getPropertyPath().toString(),"userName");
        }
    }

    @Test
    public void testRegisterRequestUserEmailWrong(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("UserName");
        registerRequest.setPassword("11111111");
        registerRequest.setUserEmail("Usertest.com");
        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        for(ConstraintViolation<RegisterRequest> violation:violations){
            assertEquals(violation.getPropertyPath().toString(),"userEmail");
        }

        registerRequest.setUserEmail("User@test@com.com");
        violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        for(ConstraintViolation<RegisterRequest> violation:violations){
            assertEquals(violation.getPropertyPath().toString(),"userEmail");
        }

        registerRequest.setUserEmail("");
        violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        for(ConstraintViolation<RegisterRequest> violation:violations){
            assertEquals(violation.getPropertyPath().toString(),"userEmail");
        }

        registerRequest.setUserEmail("User@test.com\n");
        violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        for(ConstraintViolation<RegisterRequest> violation:violations){
            assertEquals(violation.getPropertyPath().toString(),"userEmail");
        }

        registerRequest.setUserEmail("*");
        violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        for(ConstraintViolation<RegisterRequest> violation:violations){
            assertEquals(violation.getPropertyPath().toString(),"userEmail");
        }

        registerRequest.setUserEmail("$");
        violations = validator.validate(registerRequest);
        assertFalse(violations.isEmpty());
        for(ConstraintViolation<RegisterRequest> violation:violations){
            assertEquals(violation.getPropertyPath().toString(),"userEmail");
        }
    }
}