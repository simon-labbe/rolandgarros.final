/*
 */
package fr.futurskill.rolandgarros.dto.persistance;

import fr.futurskill.rolandgarros.dto.dao.DAO;
import fr.futurskill.rolandgarros.dto.dao.DAOConfigurationException;
import fr.futurskill.rolandgarros.dto.dao.DAOException;
import fr.futurskill.rolandgarros.dto.dao.DAOFactory;
import fr.futurskill.rolandgarros.dto.entites.Arbitre;
import fr.futurskill.rolandgarros.dto.entites.Court;
import fr.futurskill.rolandgarros.dto.entites.Joueur;
import fr.futurskill.rolandgarros.dto.entites.Match;
import fr.futurskill.rolandgarros.dto.entites.TypeTournoi;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author poec_sl
 */
public class JDBCMatch implements DAO<Match> {

    /**
     * SELECT * FROM Match
     *
     * @return the list of all Matches stored in the database converted as
     * objects
     * @throws DAOException
     */
    @Override
    public List<Match> findAll() throws DAOException {
        String sql = "SELECT * FROM tennis.MATCH";
        List<MatchFK> ls = new ArrayList<>();
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    ls.add(new MatchFK(
                            rs.getInt("IDENTIFIANT"),
                            rs.getInt("REFTYPE"),
                            rs.getDate("DATETOURNOI"),
                            rs.getInt("NOCOURT"),
                            rs.getInt("REFARBITRE"),
                            rs.getInt("REFJOUEUR1"),
                            rs.getInt("REFJOUEUR2"),
                            rs.getInt("NBSETSJOUEUR1"),
                            rs.getInt("NBSETSJOUEUR2")));
                }
            }
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
        List<Match> lm = new ArrayList<>();
        for (MatchFK a : ls) {
            lm.add(new Match(a.id,
                    getRefType(a.reftype),
                    a.date,
                    getRefCourt(a.nbcourt),
                    getRefArbitre(a.refa),
                    getRefJoueur(a.refj1),
                    getRefJoueur(a.refj2),
                    a.nbs1,
                    a.nbs2));
        }
        return lm;
    }

    private TypeTournoi getRefType(int id) throws DAOException {
        JDBCTypeTournoi c = new JDBCTypeTournoi();
        return c.findById(id);
    }

    private Arbitre getRefArbitre(int id) throws DAOException {
        JDBCArbitre c = new JDBCArbitre();
        return c.findById(id);
    }

    private Joueur getRefJoueur(int id) throws DAOException {
        JDBCJoueur c = new JDBCJoueur();
        return c.findById(id);
    }

    private Court getRefCourt(int id) throws DAOException {
        JDBCCourt c = new JDBCCourt();
        return c.findById(id);
    }

    /**
     * SELECT * FROM Match WHERE Identifiant = ?
     *
     * select by id
     *
     * @param id id of the object to get
     * @return object having the id
     * @throws DAOException
     */
    @Override
    public Match findById(int id) throws DAOException {

        String sql = "SELECT * FROM tennis.Match WHERE Identifiant = ?";
        Match ls = null;
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    ls = new Match(
                            rs.getInt("IDENTIFIANT"),
                            getRefType(rs.getInt("REFTYPE")),
                            rs.getDate("DATETOURNOI"),
                            getRefCourt(rs.getInt("NOCOURT")),
                            getRefArbitre(rs.getInt("REFARBITRE")),
                            getRefJoueur(rs.getInt("REFJOUEUR1")),
                            getRefJoueur(rs.getInt("REFJOUEUR2")),
                            rs.getInt("NBSETSJOUEUR1"),
                            rs.getInt("NBSETSJOUEUR2"));
                }
            }
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
        return ls;
    }

    /**
     * DELETE FROM Match WHERE Identifiant = ?
     *
     * delete the matching databalse entry with dto id
     *
     * @param id object id to delete
     * @throws DAOException
     */
    @Override
    public void delete(int id) throws DAOException {
        Match dto = findById(id);
        String sql = "DELETE FROM tennis.Match WHERE Identifiant = ?";
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, dto.getId());

            ps.executeUpdate();
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
    }

    /**
     * INSERT INTO Match (REFTYPE, DATETOURNOI, NOCOURT, REFARBITRE, REFJOUEUR1,
     * REFJOUEUR2, NBSETSJOUEUR1, NBSETSJOUEUR2) VALUES (?,?,?,?,?,?,?,?)
     *
     * insert new database entry (ignoring id because of auto_increment)
     *
     * @param dto object to insert
     * @throws DAOException
     */
    @Override
    public void insert(Match dto) throws DAOException {
        String sql = "INSERT INTO tennis.Match (REFTYPE, DATETOURNOI, NOCOURT, REFARBITRE, REFJOUEUR1, REFJOUEUR2, NBSETSJOUEUR1, NBSETSJOUEUR2) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, dto.getRefType().getRefType());
            ps.setDate(2, dto.getDateTournoi());
            ps.setInt(3, dto.getNocourt().getNumero());
            ps.setInt(4, dto.getRefArbitre().getId());
            ps.setInt(5, dto.getRefJoueur1().getId());
            ps.setInt(6, dto.getRefJoueur2().getId());
            ps.setInt(7, dto.getNbSetsJ1());
            ps.setInt(8, dto.getNbSetsJ2());

            ps.executeUpdate();
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
    }

    /**
     * UPDATE tennis.Match SET REFTYPE = ?, DATETOURNOI = ?, NOCOURT = ?,
     * REFARBITRE = ?, REFJOUEUR1 = ?, REFJOUEUR2 = ?, NBSETSJOUEUR1 = ?,
     * NBSETSJOUEUR2 = ? WHERE Identifiant = ?"
     *
     * update entry with the same id
     *
     * @param dto object to update
     * @throws DAOException
     */
    @Override
    public void update(Match dto) throws DAOException {
        String sql = "UPDATE tennis.Match SET REFTYPE = ?, DATETOURNOI = ?, NOCOURT = ?, REFARBITRE = ?, REFJOUEUR1 = ?, REFJOUEUR2 = ?, NBSETSJOUEUR1 = ?, NBSETSJOUEUR2 = ?  WHERE Identifiant = ?";
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, dto.getRefType().getRefType());
            ps.setDate(2, dto.getDateTournoi());
            ps.setInt(3, dto.getNocourt().getNumero());
            ps.setInt(4, dto.getRefArbitre().getId());
            ps.setInt(5, dto.getRefJoueur1().getId());
            ps.setInt(6, dto.getRefJoueur2().getId());
            ps.setInt(7, dto.getNbSetsJ1());
            ps.setInt(8, dto.getNbSetsJ2());

            ps.setInt(9, dto.getId());

            boolean ok = ps.execute();
            if (!ok) {
                System.out.println("up: " + ps.getUpdateCount());
            }

        } catch (SQLException | DAOConfigurationException e) {
            System.out.println(e.getMessage());
            throw new DAOException(e);
        }
    }

    private static class MatchFK {

        int id;
        int reftype;
        Date date;
        int nbcourt;
        int refa;
        int refj1;
        int refj2;
        int nbs1;
        int nbs2;

        public MatchFK(int id, int reftype, Date date, int nbcourt, int refa,
                int refj1, int refj2, int nbs1, int nbs2) {
            this.id = id;
            this.reftype = reftype;
            this.date = date;
            this.nbcourt = nbcourt;
            this.refa = refa;
            this.refj1 = refj1;
            this.refj2 = refj2;
            this.nbs1 = nbs1;
            this.nbs2 = nbs2;
        }

    }

}
