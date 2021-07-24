package ua.springrest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.springrest.entity.User;
import ua.springrest.entity.form.RegistrationForm;
import ua.springrest.exceptions.ValidateException;
import ua.springrest.populator.RegistrationFormToUser;
import ua.springrest.repository.UserRepository;
import ua.springrest.validation.RegistrationFormValidate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {
    private RegistrationForm form;

    @Mock
    private RegistrationFormValidate formValidate;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RegistrationFormToUser registrationFormToUser;

    @InjectMocks
    private RegistrationService registrationService;

    @BeforeEach
    void setUp(){
        form = new RegistrationForm();
    }

    @Test
    void shouldRegister() throws ValidateException{
        boolean isRegister = registrationService.register(form);
        assertThat(isRegister).isTrue();
        verify(formValidate).validate(form);
        verify(userRepository).save(any(User.class));
        verify(registrationFormToUser).converter(eq(form) , any(User.class));
    }

    @Test
    void shouldNotRegister() throws ValidateException{
        doThrow(ValidateException.class).when(formValidate).validate(form);
        boolean isRegister = registrationService.register(form);
        assertThat(isRegister).isFalse();
        verify(registrationFormToUser , never()).converter(eq(form) , any(User.class));
        verify(userRepository , never()).save(any(User.class));
    }



}
