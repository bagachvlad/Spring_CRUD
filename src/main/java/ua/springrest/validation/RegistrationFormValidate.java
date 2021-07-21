package ua.springrest.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.springrest.entity.User;
import ua.springrest.entity.form.RegistrationForm;
import ua.springrest.exceptions.FieldValidationException;
import ua.springrest.exceptions.ValidateException;
import ua.springrest.repository.UserRepository;

import java.util.List;

import static ua.springrest.constants.LoggingConstants.LOG_DEBUG_ONE_ARG_PATTERN;
import static ua.springrest.entity.User.*;

@Service
public class RegistrationFormValidate implements Validator<RegistrationForm>{

    private static final Logger logger = LoggerFactory.getLogger(RegistrationFormValidate.class);

    private static final String VALIDATION_EXCEPTION_PATTERN = "Field: '%s', value: '%s' , reason: '%s'";
    private static final String DUPLICATION_REASON = "Duplicated instance in database";

    private final UserRepository userRepository;

    public RegistrationFormValidate(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(RegistrationForm registrationForm) throws ValidateException {
        logger.info(LOG_DEBUG_ONE_ARG_PATTERN.getMessage() , registrationForm);
        List<User> allUsers = userRepository.findAll();
        try{
            allUsers.forEach(user -> validateFields(registrationForm , user));
        }catch (FieldValidationException exception){
            String message = generateValidationMessage(exception);
            logger.warn(message);
            throw new ValidateException(message);
        }
    }

    private String generateValidationMessage(FieldValidationException ex) {
        String invalidFieldName = ex.getInvalidFieldName();
        String invalidValue = ex.getInvalidValue();
        String reason = ex.getReason();
        return String.format(VALIDATION_EXCEPTION_PATTERN, invalidFieldName, invalidValue, reason);
    }


    private void validateFields(RegistrationForm form , User user) throws FieldValidationException{
        checkEmailValidation(form , user);
        checkPhoneNumberValidation(form , user);
        checkUsernameValidation(form , user);
    }

    private void checkPhoneNumberValidation(RegistrationForm form , User user){
        boolean checkPhoneNumber = user.getPhoneNumber().equals(form.getPhoneNumber());
        if (checkPhoneNumber)
            throw new FieldValidationException(PHONE_NUMBER_FIELD , form.getPhoneNumber() , DUPLICATION_REASON);
    }

    private void checkEmailValidation(RegistrationForm form , User user){
        boolean checkEmail = user.getEmail().equals(form.getEmail());
        if (checkEmail)
            throw new FieldValidationException(EMAIL_FIELD , form.getEmail(), DUPLICATION_REASON);
    }


    private void checkUsernameValidation(RegistrationForm form , User user){
        boolean checkUsername = user.getUsername().equals(form.getUsername());
        if (checkUsername)
            throw new FieldValidationException(USERNAME_FIELD , form.getUsername(), DUPLICATION_REASON);
    }
}
