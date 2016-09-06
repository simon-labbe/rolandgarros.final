/*
 */
package fr.futurskill.rolandgarros.dto.persistance;

import fr.futurskill.rolandgarros.dto.dao.DAO;
import fr.futurskill.rolandgarros.dto.dao.DAOConfigurationException;
import fr.futurskill.rolandgarros.dto.dao.DAOException;
import fr.futurskill.rolandgarros.dto.dao.DAOFactory;
import fr.futurskill.rolandgarros.dto.entites.Court;
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
public class JDBCCourt implements DAO<Court> {

    /**
     * SELECT * FROM Court
     *
     * @return the list of all Courts stored in the database converted as
     * objects
     * @throws DAOException
     */
    @Override
    public List<Court> findAll() throws DAOException {
        String sql = "SELECT * FROM Court";
        List<Court> ls = new ArrayList<>();
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    ls.add(new Court(rs.getInt("NUMERO"), rs.getString("NOM")));
                }
            }
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
        return ls;
    }

    /**
     * SELECT * FROM Court WHERE NUMERO = ?
     *
     * select by id
     *
     * @param id id of the object to get
     * @return object having the id
     * @throws DAOException
     */
    @Override
    public Court findById(int id) throws DAOException {

        String sql = "SELECT * FROM Court WHERE NUMERO = ?";
        Court ls = null;
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    ls = new Court(rs.getInt("NUMERO"), rs.getString("NOM"));
                }
            }
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
        return ls;
    }

    /**
     * DELETE FROM Court WHERE NUMERO = ?
     *
     * delete the matching databalse entry with dto id
     *
     * @param id object id to delete
     * @throws DAOException
     */
    @Override
    public void delete(int id) throws DAOException {
        Court dto = findById(id);
        String sql = "DELETE FROM Court WHERE NUMERO = ?";
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, dto.getNumero());

            ps.executeUpdate();
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
    }

    /**
     * INSERT INTO Court (NUMERO, NOM) VALUES (?,?)
     *
     * insert new database entry (ignoring id because of auto_increment)
     *
     * @param dto object to insert
     * @throws DAOException
     */
    @Override
    public void insert(Court dto) throws DAOException {
        String sql = "INSERT INTO Court (NUMERO, NOM) VALUES (?,?)";
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, dto.getNumero());
            ps.setString(2, dto.getNom());

            ps.executeUpdate();
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
    }

    /**
     * UPDATE Court SET NOM = ? WHERE NUMERO = ?
     *
     * update entry with the same id
     *
     * @param dto object to update
     * @throws DAOException
     */
    @Override
    public void update(Court dto) throws DAOException {
        String sql = "UPDATE Court SET NOM = ? WHERE NUMERO = ?";
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, dto.getNom());

            ps.setInt(2, dto.getNumero());

            ps.executeUpdate();
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
    }

}
