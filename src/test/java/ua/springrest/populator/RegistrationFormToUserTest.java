package ua.springrest.populator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.springrest.entity.Role;
import ua.springrest.entity.State;
import ua.springrest.entity.User;
import ua.springrest.entity.form.RegistrationForm;
import ua.springrest.populator.RegistrationFormToUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegistrationFormToUserTest {

    private RegistrationForm form;
    private User user;

    private static final String FIRST_NAME = "Vlad";
    private static final String LAST_NAME = "Bagach";
    private static final String USERNAME = "vladbagach";
    private static final String PASSWORD = "password";
    private static final String ENCODED_PASSWORD = "encodedpassword";
    private static final String PHONE_NUMBER = "0661008714";
    private static final String EMAIL = "vlad@mail.ru";
    private static final Role ROLE = Role.USER;
    private static final State STATE = State.ACTIVE;

    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private RegistrationFormToUser registrationFormToUser;

    @BeforeEach
    void setUp(){
        form = new RegistrationForm();
        form.setFirstName(FIRST_NAME);
        form.setLastName(LAST_NAME);
        form.setEmail(EMAIL);
        form.setPhoneNumber(PHONE_NUMBER);
        form.setUsername(USERNAME);
        form.setPassword(PASSWORD);

        user = new User();
        when(passwordEncoder.encode(PASSWORD)).thenReturn(ENCODED_PASSWORD);
    }

    @Test
    void shouldPopulate(){
        registrationFormToUser.converter(form , user);
        assertThat(user.getId()).isNotNull();
        assertThat(user.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(user.getLastName()).isEqualTo(LAST_NAME);
        assertThat(user.getPhoneNumber()).isEqualTo(PHONE_NUMBER);
        assertThat(user.getEmail()).isEqualTo(EMAIL);
        assertThat(user.getUsername()).isEqualTo(USERNAME);
        assertThat(user.getPassword()).isEqualTo(ENCODED_PASSWORD);
        assertThat(user.getRole()).isEqualTo(ROLE);
        assertThat(user.getState()).isEqualTo(STATE);
    }

}
