package fr.futurskill.rolandgarros.dto.dao;

public class DAOConfigurationException extends Exception {

    private static final long serialVersionUID = 1L;

    public DAOConfigurationException() {
        super();
    }

    public DAOConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOConfigurationException(String message) {
        super(message);
    }

    public DAOConfigurationException(Throwable cause) {
        super(cause);
    }

}
