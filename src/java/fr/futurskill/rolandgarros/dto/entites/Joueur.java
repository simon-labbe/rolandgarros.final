/*
 */
package fr.futurskill.rolandgarros.dto.entites;

/**
 *
 * @author poec_sl
 */
public class Joueur extends Personne {

    private int classement;
    private char sexe;

    public Joueur(int classement, char sexe, int id, String nom, String prenom, String nation) {
        super(id, nom, prenom, nation);
        this.classement = classement;
        this.sexe = sexe;
    }

    public Joueur(int classement, char sexe, String nom, String prenom, String nation) {
        super(nom, prenom, nation);
        this.classement = classement;
        this.sexe = sexe;
        this.id = 0;
    }

    public int getClassement() {
        return classement;
    }

    public void setClassement(int classement) {
        this.classement = classement;
    }

    public char getSexe() {
        return sexe;
    }

    public void setSexe(char sexe) {
        this.sexe = sexe;
    }

    @Override
    public String toString() {
        return "id:" + id + "\tnom:" + nom + "\tprenom:" + prenom + "\tsexe:"
                + sexe + "\tnation:" + nation + "\tclassement:" + classement;
    }

}
