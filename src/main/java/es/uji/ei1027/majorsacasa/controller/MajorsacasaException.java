package es.uji.ei1027.majorsacasa.controller;

public class MajorsacasaException extends RuntimeException {
    String message;     // Mensaje para mostrar en la vista
    String errorName;   // Identificador del error

    public MajorsacasaException(String message, String errorName) {
        this.message = message;
        this.errorName = errorName;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }
}
