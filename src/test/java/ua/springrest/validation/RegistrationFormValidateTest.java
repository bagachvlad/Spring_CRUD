package ua.springrest.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.springrest.entity.User;
import ua.springrest.entity.form.RegistrationForm;
import ua.springrest.exceptions.ValidateException;
import ua.springrest.repository.UserRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegistrationFormValidateTest {
    private RegistrationForm form;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RegistrationFormValidate registrationFormValidate;

    private final static String EMAIL = "email";
    private final static String EMAIL2 = "email2";
    private final static String USERNAME = "username";
    private final static String USERNAME2 = "username2";
    private final static String PHONE_NUMBER = "phoneNumber";
    private final static String PHONE_NUMBER2 = "phoneNumber2";

    private List<User> users;

    @BeforeEach
    void setUp(){
        User user = new User();
        user.setEmail(EMAIL);
        user.setUsername(USERNAME);
        user.setPhoneNumber(PHONE_NUMBER);

        users = Collections.singletonList(user);

        form = new RegistrationForm();
        form.setEmail(EMAIL2);
        form.setUsername(USERNAME2);
        form.setPhoneNumber(PHONE_NUMBER2);
    }

    @Test
    void shouldValidate() throws ValidateException {
        when(userRepository.findAll()).thenReturn(users);
        registrationFormValidate.validate(form);
    }

    @Test
    void shouldThrowExceptionOnSameUsername() {
        form.setUsername(USERNAME);
        when(userRepository.findAll()).thenReturn(users);
        assertThrows(ValidateException.class , () -> registrationFormValidate.validate(form));
    }

    @Test
    void shouldThrowExceptionOnSameEmail() {
        form.setEmail(EMAIL);
        when(userRepository.findAll()).thenReturn(users);
        assertThrows(ValidateException.class , () -> registrationFormValidate.validate(form));
    }

    @Test
    void shouldThrowExceptionOnSamePhoneNumber() {
        form.setPhoneNumber(PHONE_NUMBER);
        when(userRepository.findAll()).thenReturn(users);
        assertThrows(ValidateException.class , () -> registrationFormValidate.validate(form));
    }



}
