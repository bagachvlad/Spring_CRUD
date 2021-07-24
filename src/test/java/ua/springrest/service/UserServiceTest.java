package ua.springrest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.springrest.entity.Role;
import ua.springrest.entity.State;
import ua.springrest.entity.User;
import ua.springrest.exceptions.FieldNotFoundException;
import ua.springrest.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private User user;

    private static final Long ID = 1L;
    private static final String USERNAME = "vladbagach";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void shouldGetFindAll(){
        userService.getFindAll();
        verify(userRepository).findAll();
    }

    @Test
    void shouldFindByUsername(){
        Optional<User> userOptional = Optional.of(user);
        when(userRepository.findUserByUsername(USERNAME)).thenReturn(userOptional);
        User actual = userService.getUserByUsername(USERNAME);
        assertThat(actual).isEqualTo(user);
    }

    @Test
    void shouldGetUserById(){
        Optional<User> userOptional = Optional.of(user);
        when(userRepository.findById(ID)).thenReturn(userOptional);
        User actual = userService.getUserById(ID);
        assertThat(actual).isEqualTo(user);
    }

    @Test
    void shouldDeleteById(){
        userService.deleteUserById(ID);
        verify(userRepository).deleteById(ID);
    }

    @Test
    void shouldSave(){
        userService.saveUser(user);
        verify(userRepository).save(user);
    }

    @Test
    void shouldExportExceptionOnUsername(){
        when(userRepository.findUserByUsername(USERNAME)).thenReturn(Optional.empty());
        assertThrows(FieldNotFoundException.class , () -> userService.getUserByUsername(USERNAME));
    }

    @Test
    void shouldProcessExportExceptionOnInvalidId() {
        when(userRepository.findById(ID)).thenReturn(Optional.empty());
        assertThrows(FieldNotFoundException.class , () -> userService.getUserById(ID));
    }

}
