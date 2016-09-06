/*
 */
package fr.futurskill.rolandgarros.dto.entites;

/**
 *
 * @author poec_sl
 */
public class Arbitre extends Personne {

    private int niveau;

    public Arbitre(int niveau, int id, String nom, String prenom, String nation) {
        super(id, nom, prenom, nation);
        this.niveau = niveau;
    }

    public Arbitre(int niveau, String nom, String prenom, String nation) {
        super(nom, prenom, nation);
        this.niveau = niveau;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "id:" + id + "\tnom:" + nom + "\tprenom:" + prenom + "\tniveau:" + niveau + "\tnation:" + nation;
    }

}
