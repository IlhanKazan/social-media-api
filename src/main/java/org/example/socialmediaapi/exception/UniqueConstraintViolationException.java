package org.example.socialmediaapi.exception;

public class UniqueConstraintViolationException extends RuntimeException {
    private final String fieldName;
    private final String fieldValue;

    public UniqueConstraintViolationException(String fieldName, String fieldValue) {
        super(String.format("%s '%s' already in use", fieldName, fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }
}
