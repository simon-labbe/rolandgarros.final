/*
 */
package fr.futurskill.rolandgarros.dto.entites;

import java.sql.Date;

/**
 *
 * @author poec_sl
 */
public class Match {

    private int id;
    private TypeTournoi refType;
    private Date dateTournoi;
    private Court nocourt;
    private Arbitre refArbitre;
    private Joueur refJoueur1;
    private Joueur refJoueur2;
    private int nbSetsJ1;
    private int nbSetsJ2;

    public Match(int id, TypeTournoi refType, Date dateTournoi, Court nocourt, Arbitre refArbitre, Joueur refJoueur1, Joueur refJoueur2, int nbSetsJ1, int nbSetsJ2) {
        this.id = id;
        this.refType = refType;
        this.dateTournoi = dateTournoi;
        this.nocourt = nocourt;
        this.refArbitre = refArbitre;
        this.refJoueur1 = refJoueur1;
        this.refJoueur2 = refJoueur2;
        this.nbSetsJ1 = nbSetsJ1;
        this.nbSetsJ2 = nbSetsJ2;
    }

    public Match(TypeTournoi refType, Date dateTournoi, Court nocourt, Arbitre refArbitre, Joueur refJoueur1, Joueur refJoueur2, int nbSetsJ1, int nbSetsJ2) {
        this.refType = refType;
        this.dateTournoi = dateTournoi;
        this.nocourt = nocourt;
        this.refArbitre = refArbitre;
        this.refJoueur1 = refJoueur1;
        this.refJoueur2 = refJoueur2;
        this.nbSetsJ1 = nbSetsJ1;
        this.nbSetsJ2 = nbSetsJ2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeTournoi getRefType() {
        return refType;
    }

    public void setRefType(TypeTournoi refType) {
        this.refType = refType;
    }

    public Date getDateTournoi() {
        return dateTournoi;
    }

    public void setDateTournoi(Date dateTournoi) {
        this.dateTournoi = dateTournoi;
    }

    public Court getNocourt() {
        return nocourt;
    }

    public void setNocourt(Court nocourt) {
        this.nocourt = nocourt;
    }

    public Arbitre getRefArbitre() {
        return refArbitre;
    }

    public void setRefArbitre(Arbitre refArbitre) {
        this.refArbitre = refArbitre;
    }

    public Joueur getRefJoueur1() {
        return refJoueur1;
    }

    public void setRefJoueur1(Joueur refJoueur1) {
        this.refJoueur1 = refJoueur1;
    }

    public Joueur getRefJoueur2() {
        return refJoueur2;
    }

    public void setRefJoueur2(Joueur refJoueur2) {
        this.refJoueur2 = refJoueur2;
    }

    public int getNbSetsJ1() {
        return nbSetsJ1;
    }

    public void setNbSetsJ1(int nbSetsJ1) {
        this.nbSetsJ1 = nbSetsJ1;
    }

    public int getNbSetsJ2() {
        return nbSetsJ2;
    }

    public void setNbSetsJ2(int nbSetsJ2) {
        this.nbSetsJ2 = nbSetsJ2;
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", refType=" + refType + ", dateTournoi=" + dateTournoi + ", nocourt=" + nocourt + ", refArbitre=" + refArbitre + ", refJoueur1=" + refJoueur1 + ", refJoueur2=" + refJoueur2 + ", nbSetsJ1=" + nbSetsJ1 + ", nbSetsJ2=" + nbSetsJ2 + '}';
    }

}
