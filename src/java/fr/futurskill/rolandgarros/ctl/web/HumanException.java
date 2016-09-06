/*
 */
package fr.futurskill.rolandgarros.ctl.web;

/**
 *
 * @author poec_sl
 */
public class HumanException extends Exception {

    private static final long serialVersionUID = 1L;

    public HumanException() {
    }

    public HumanException(String message, Throwable cause) {
        super(message, cause);
    }

    public HumanException(String message) {
        super(message);
    }

    public HumanException(Throwable cause) {
        super(cause);
    }
}
