package ua.springrest.exceptions;

public class FieldValidationException extends RuntimeException{
    private String invalidFieldName;
    private String invalidValue;
    private String reason;

    public FieldValidationException(String invalidFieldName, String invalidValue, String reason) {
        this.invalidFieldName = invalidFieldName;
        this.invalidValue = invalidValue;
        this.reason = reason;
    }

    public String getInvalidFieldName() {
        return invalidFieldName;
    }


    public String getInvalidValue() {
        return invalidValue;
    }


    public String getReason() {
        return reason;
    }

}
