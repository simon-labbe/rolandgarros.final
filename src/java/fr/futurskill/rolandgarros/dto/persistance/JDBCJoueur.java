/*
 */
package fr.futurskill.rolandgarros.dto.persistance;

import fr.futurskill.rolandgarros.dto.dao.DAO;
import fr.futurskill.rolandgarros.dto.dao.DAOConfigurationException;
import fr.futurskill.rolandgarros.dto.dao.DAOException;
import fr.futurskill.rolandgarros.dto.dao.DAOFactory;
import fr.futurskill.rolandgarros.dto.entites.Joueur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author poec_sl
 */
public class JDBCJoueur implements DAO<Joueur> {

    /**
     * SELECT * FROM Joueur
     *
     * @return the list of all Players stored in the database converted as
     * objects
     * @throws DAOException
     */
    @Override
    public List<Joueur> findAll() throws DAOException {
        String sql = "SELECT * FROM Joueur";
        List<Joueur> ls = new ArrayList<>();
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    ls.add(new Joueur(
                            rs.getInt("CLASSEMENT"),
                            rs.getString("SEXE").charAt(0),
                            rs.getInt("IDENTIFIANT"),
                            rs.getString("NOM"),
                            rs.getString("PRENOM"),
                            rs.getString("NATION")));
                }
            }
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
        return ls;
    }

    /**
     * SELECT * FROM Joueur WHERE Identifiant = ?
     *
     * select by id
     *
     * @param id id of the object to get
     * @return object having the id
     * @throws DAOException
     */
    @Override
    public Joueur findById(int id) throws DAOException {

        String sql = "SELECT * FROM Joueur WHERE Identifiant = ?";
        Joueur ls = null;
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    ls = new Joueur(
                            rs.getInt("CLASSEMENT"),
                            rs.getString("SEXE").charAt(0),
                            rs.getInt("IDENTIFIANT"),
                            rs.getString("NOM"),
                            rs.getString("PRENOM"),
                            rs.getString("NATION"));
                }
            }
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
        return ls;
    }

    /**
     * DELETE FROM Joueur WHERE Identifiant = ?
     *
     * delete the matching databalse entry with dto id
     *
     * @param id object id to delete
     * @throws DAOException
     */
    @Override
    public void delete(int id) throws DAOException {
        Joueur dto = findById(id);
        String sql = "DELETE FROM Joueur WHERE Identifiant = ?";
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, dto.getId());

            ps.executeUpdate();
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
    }

    /**
     * INSERT INTO Joueur (NOM,PRENOM,SEXE,NATION,CLASSEMENT) VALUES (?,?,?,?,?)
     *
     * insert new database entry (ignoring id because of auto_increment)
     *
     * @param dto object to insert
     * @throws DAOException
     */
    @Override
    public void insert(Joueur dto) throws DAOException {
        String sql = "INSERT INTO Joueur (NOM,PRENOM,SEXE,NATION,CLASSEMENT) VALUES (?,?,?,?,?)";
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, dto.getNom());
            ps.setString(2, dto.getPrenom());
            ps.setString(3, String.valueOf(dto.getSexe()));
            ps.setString(4, dto.getNation());
            ps.setInt(5, dto.getClassement());

            ps.executeUpdate();
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
    }

    /**
     * UPDATE Joueur SET NOM = ?, PRENOM = ?, SEXE = ?, NATION = ?, CLASSEMENT =
     * ? WHERE Identifiant = ?
     *
     * update entry with the same id
     *
     * @param dto object to update
     * @throws DAOException
     */
    @Override
    public void update(Joueur dto) throws DAOException {
        String sql = "UPDATE Joueur SET NOM = ?, PRENOM = ?, SEXE = ?, NATION = ?, CLASSEMENT = ? WHERE Identifiant = ?";
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, dto.getNom());
            ps.setString(2, dto.getPrenom());
            ps.setString(3, String.valueOf(dto.getSexe()));
            ps.setString(4, dto.getNation());
            ps.setInt(5, dto.getClassement());

            ps.setInt(6, dto.getId());

            ps.executeUpdate();
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
    }

}
