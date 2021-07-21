package ua.springrest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ua.springrest.entity.User;
import ua.springrest.entity.form.RegistrationForm;
import ua.springrest.exceptions.ValidateException;
import ua.springrest.populator.Populator;
import ua.springrest.repository.UserRepository;
import ua.springrest.validation.RegistrationFormValidate;

import static ua.springrest.constants.LoggingConstants.*;

@Service
public class RegistrationService {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    private final RegistrationFormValidate registrationFormValidate;
    private final Populator<RegistrationForm , User> populator;
    private final UserRepository userRepository;

    @Autowired
    public RegistrationService(RegistrationFormValidate registrationFormValidate, Populator<RegistrationForm, User> populator, UserRepository userRepository) {
        this.registrationFormValidate = registrationFormValidate;
        this.populator = populator;
        this.userRepository = userRepository;
    }


    public boolean register(RegistrationForm form){
        if (!isRegistrationFormValid(form)){
            return false;
        }
        User user = convertRegistrationFormToUser(form);

        if(!isCustomerSavedSuccessfully(user)){
            return false;
        }
        processSuccessMessage(user);
        return true;
    }

    private void processUserCreation(User user){
        userRepository.save(user);
    }

    private void processSuccessMessage(User user) {
        String username = user.getUsername();
        String message = String.format(REGISTRATION_SUCCESS_PATTERN.getMessage(), username);
        logger.info(message);
    }

    private boolean isCustomerSavedSuccessfully(User user) {
        try {
            processUserCreation(user);
        } catch (DataAccessException ex) {
            String message = String.format(SAVE_FAILED_PATTERN.getMessage(), ex.getMessage());
            logger.warn(message);
            return false;
        }
        return true;
    }

    private boolean isRegistrationFormValid(RegistrationForm registrationForm) {
        try {
            registrationFormValidate.validate(registrationForm);
        } catch (ValidateException ex) {
            String message = String.format(VALIDATION_FAILED_PATTERN.getMessage() , ex.getMessage());
            logger.warn(message);
            return false;
        }
        return true;
    }

    private User convertRegistrationFormToUser(RegistrationForm form){
        User user = new User();
        populator.converter(form , user);
        return user;
    }


}
