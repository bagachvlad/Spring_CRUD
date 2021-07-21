package ua.springrest.populator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.springrest.entity.Role;
import ua.springrest.entity.State;
import ua.springrest.entity.User;
import ua.springrest.entity.form.RegistrationForm;

@Component
public class RegistrationFormToUser implements Populator<RegistrationForm, User> {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationFormToUser(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void converter(RegistrationForm registrationForm, User user) {

        populateId(user);
        user.setFirstName(registrationForm.getFirstName());
        user.setLastName(registrationForm.getLastName());
        user.setUsername(registrationForm.getUsername());
        populatePassword(registrationForm , user);
        user.setEmail(registrationForm.getEmail());
        user.setPhoneNumber(registrationForm.getPhoneNumber());
        user.setRole(Role.USER);
        user.setState(State.ACTIVE);
    }

    private void populateId(User user){
        long l = 1L;
        long r = 100L;
        Long generatedLong = l + (long)(Math.random() * (r - l ));
        user.setId(generatedLong);
    }

    private void populatePassword(RegistrationForm form , User user){
        String password = form.getPassword();
        user.setPassword(passwordEncoder.encode(password));
    }


}
