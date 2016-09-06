/*
 */
package fr.futurskill.rolandgarros.dto.entites;

/**
 *
 * @author poec_sl
 */
public class Utilisateur {

    String login;
    String password;

    public Utilisateur(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "login=" + login + ", password=" + password + '}';
    }

}
