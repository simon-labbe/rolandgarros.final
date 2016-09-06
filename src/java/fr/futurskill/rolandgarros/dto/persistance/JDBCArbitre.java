/*
 */
package fr.futurskill.rolandgarros.dto.persistance;

import fr.futurskill.rolandgarros.dto.dao.DAO;
import fr.futurskill.rolandgarros.dto.dao.DAOConfigurationException;
import fr.futurskill.rolandgarros.dto.dao.DAOException;
import fr.futurskill.rolandgarros.dto.dao.DAOFactory;
import fr.futurskill.rolandgarros.dto.entites.Arbitre;
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
public class JDBCArbitre implements DAO<Arbitre> {

    /**
     * SELECT * FROM Arbitre
     *
     * @return the list of all Refferees stored in the database converted as
     * objects
     * @throws DAOException
     */
    @Override
    public List<Arbitre> findAll() throws DAOException {
        String sql = "SELECT * FROM Arbitre";
        List<Arbitre> ls = new ArrayList<>();
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    ls.add(new Arbitre(
                            rs.getInt("NIVEAU"),
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
     * SELECT * FROM Arbitre WHERE Identifiant = ?
     *
     * select by id
     *
     * @param id id of the object to get
     * @return object having the id
     * @throws DAOException
     */
    @Override
    public Arbitre findById(int id) throws DAOException {

        String sql = "SELECT * FROM Arbitre WHERE Identifiant = ?";
        Arbitre ls = null;
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    ls = new Arbitre(
                            rs.getInt("NIVEAU"),
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
     * DELETE FROM Arbitre WHERE Identifiant = ?
     *
     * delete the matching databalse entry with dto id
     *
     * @param id object id to delete
     * @throws DAOException
     */
    @Override
    public void delete(int id) throws DAOException {
        Arbitre dto = findById(id);
        String sql = "DELETE FROM Arbitre WHERE Identifiant = ?";
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, dto.getId());

            ps.executeUpdate();
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
    }

    /**
     * INSERT INTO Arbitre (NOM,PRENOM,NIVEAU,NATION) VALUES (?,?,?,?)
     *
     * insert new database entry (ignoring id because of auto_increment)
     *
     * @param dto object to insert
     * @throws DAOException
     */
    @Override
    public void insert(Arbitre dto) throws DAOException {
        String sql = "INSERT INTO Arbitre (NOM,PRENOM,NIVEAU,NATION) VALUES (?,?,?,?)";
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, dto.getNom());
            ps.setString(2, dto.getPrenom());
            ps.setInt(3, dto.getNiveau());
            ps.setString(4, dto.getNation());

            ps.executeUpdate();
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
    }

    /**
     * UPDATE Arbitre SET NOM = ?, PRENOM = ?, NIVEAU = ?, NATION = ? WHERE
     * Identifiant = ?
     *
     * update entry with the same id
     *
     * @param dto object to update
     * @throws DAOException
     */
    @Override
    public void update(Arbitre dto) throws DAOException {
        String sql = "UPDATE Arbitre SET NOM = ?, PRENOM = ?, NIVEAU = ?, NATION = ? WHERE Identifiant = ?";
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, dto.getNom());
            ps.setString(2, dto.getPrenom());
            ps.setInt(3, dto.getNiveau());
            ps.setString(4, dto.getNation());

            ps.setInt(5, dto.getId());

            ps.executeUpdate();
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
    }

}
