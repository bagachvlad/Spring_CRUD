package ua.springrest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.springrest.entity.User;
import ua.springrest.exceptions.FieldNotFoundException;
import ua.springrest.repository.UserRepository;

import java.util.List;

import static ua.springrest.constants.LoggingConstants.*;

@Service
public class UserService{
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<User> getFindAll(){
        logger.debug(LOG_DEBUG_EMPTY_PATTERN.getMessage());
        return userRepository.findAll();
    }

    @Transactional
    public User getUserById(Long id){
        logger.debug(LOG_DEBUG_ONE_ARG_PATTERN.getMessage() , id);
        return userRepository.findById(id).orElseThrow(()-> generateGeneralCustomerException(User.ID_FIELD , id.toString()));
    }

    @Transactional
    public User getUserByUsername(String username){
        logger.debug(LOG_DEBUG_ONE_ARG_PATTERN.getMessage() , username);
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> generateGeneralCustomerException(User.USERNAME_FIELD , username));
    }

    @Transactional
    public void deleteUserById(Long id){
        logger.debug(LOG_DEBUG_ONE_ARG_PATTERN.getMessage() , id);
        userRepository.deleteById(id);
    }

    @Transactional
    public void saveUser(User user){
        logger.debug(LOG_DEBUG_ONE_ARG_PATTERN.getMessage() , user);
        userRepository.save(user);
    }
    
    private RuntimeException generateGeneralCustomerException(String invalidField, String invalidValue) {
        String message = String.format(GENERAL_USER_NOT_FOUND_PATTERN.getMessage(), invalidField, invalidValue);
        logger.warn(message);
        return new FieldNotFoundException(message);
    }

}
