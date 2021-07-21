package ua.springrest.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.springrest.entity.form.RegistrationForm;
import ua.springrest.service.RegistrationService;

@RestController
@RequestMapping("/api/")
public class RegistrationController {

    private final RegistrationService form;

    @Autowired
    public RegistrationController(RegistrationService form) {
        this.form = form;
    }

    @PostMapping("/registration")
    public String registration(@RequestBody RegistrationForm registrationForm){
        if(!form.register(registrationForm)){
            return "false";
        }
        return "true";
    }

}
