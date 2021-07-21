package ua.springrest.validation;


import ua.springrest.exceptions.ValidateException;

public interface Validator <T> {
    void validate(T t) throws ValidateException;
}
