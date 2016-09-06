/*
 */
package fr.futurskill.rolandgarros.dto.entites;

/**
 *
 * @author poec_sl
 */
public abstract class Personne {

    int id;
    String nom;
    String prenom;
    String nation;

    public Personne(int id, String nom, String prenom, String nation) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.nation = nation;
    }

    public Personne(String nom, String prenom, String nation) {
        this.nom = nom;
        this.prenom = prenom;
        this.nation = nation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", nation=" + nation + '}';
    }

}
