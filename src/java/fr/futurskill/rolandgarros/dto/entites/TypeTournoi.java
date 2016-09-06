/*
 */
package fr.futurskill.rolandgarros.dto.entites;

/**
 *
 * @author poec_sl
 */
public class TypeTournoi {

    int refType;
    String nomTournoi;
    int nbSets;

    public TypeTournoi(int refType, String nomTournoi, int nbSets) {
        this.refType = refType;
        this.nomTournoi = nomTournoi;
        this.nbSets = nbSets;
    }

    public int getRefType() {
        return refType;
    }

    public void setRefType(int refType) {
        this.refType = refType;
    }

    public String getNomTournoi() {
        return nomTournoi;
    }

    public void setNomTournoi(String nomTournoi) {
        this.nomTournoi = nomTournoi;
    }

    public int getNbSets() {
        return nbSets;
    }

    public void setNbSets(int nbSets) {
        this.nbSets = nbSets;
    }

    @Override
    public String toString() {
        return "TypeTournoi{" + "refType=" + refType + ", nomTournoi=" + nomTournoi + ", nbSets=" + nbSets + '}';
    }

}
