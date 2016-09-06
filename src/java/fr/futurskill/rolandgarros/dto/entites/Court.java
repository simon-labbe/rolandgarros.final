/*
 */
package fr.futurskill.rolandgarros.dto.entites;

/**
 *
 * @author poec_sl
 */
public class Court {

    private int numero;
    private String nom;

    public Court(int numero, String nom) {
        this.numero = numero;
        this.nom = nom;
    }

    public Court(String nom) {
        this.nom = nom;
        this.numero = 0;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "numero:" + numero + "\nnom:" + nom;
    }

}
