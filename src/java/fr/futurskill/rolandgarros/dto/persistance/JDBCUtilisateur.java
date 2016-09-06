/*
 */
package fr.futurskill.rolandgarros.dto.persistance;

import fr.futurskill.rolandgarros.dto.dao.DAO;
import fr.futurskill.rolandgarros.dto.dao.DAOConfigurationException;
import fr.futurskill.rolandgarros.dto.dao.DAOException;
import fr.futurskill.rolandgarros.dto.dao.DAOFactory;
import fr.futurskill.rolandgarros.dto.entites.Utilisateur;
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
public class JDBCUtilisateur implements DAO<Utilisateur> {

    /**
     * SELECT * FROM Utilisateur
     *
     * @return the list of all Utilisateurs stored in the database converted as
     * objects
     * @throws DAOException
     */
    @Override
    public List<Utilisateur> findAll() throws DAOException {
        String sql = "SELECT * FROM Utilisateur";
        List<Utilisateur> ls = new ArrayList<>();
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    ls.add(new Utilisateur(
                            rs.getString("LOGIN"),
                            rs.getString("PASSWORD")));
                }
            }
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
        return ls;
    }

    /**
     * SELECT * FROM Utilisateur WHERE LOGIN = ?
     *
     * @param login
     * @return password assigned to the login
     * @throws DAOException
     */
    public Utilisateur findByLogin(String login) throws DAOException {

        String sql = "SELECT * FROM Utilisateur WHERE LOGIN = ?";
        Utilisateur ls = null;
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, login);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    ls = new Utilisateur(
                            rs.getString("LOGIN"),
                            rs.getString("PASSWORD"));
                }
            }
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
        return ls;
    }

    /**
     * SELECT * FROM Utilisateur WHERE ID = ?
     *
     * select by id
     *
     * @param id id of the object to get
     * @return object having the id
     * @throws DAOException
     */
    @Override
    public Utilisateur findById(int id) throws DAOException {

        String sql = "SELECT * FROM Utilisateur WHERE ID = ?";
        Utilisateur ls = null;
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    ls = new Utilisateur(
                            rs.getString("LOGIN"),
                            rs.getString("PASSWORD"));
                }
            }
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
        return ls;
    }

    /**
     * DELETE FROM Utilisateur WHERE LOGIN = ?
     *
     * delete the matching databalse entry with dto LOGIN
     *
     * @param login login to delete
     * @throws DAOException
     */
    public void delete(String login) throws DAOException {
        Utilisateur dto = findByLogin(login);
        String sql = "DELETE FROM Utilisateur WHERE LOGIN = ?";
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, dto.getLogin());

            ps.executeUpdate();
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        throw new UnsupportedOperationException("Not supported.");
    }

    /**
     * INSERT INTO Utilisateur (LOGIN, PASSWORD) VALUES (?,?)
     *
     * insert new database entry
     *
     * @param dto object to insert
     * @throws DAOException
     */
    @Override
    public void insert(Utilisateur dto) throws DAOException {
        String sql = "INSERT INTO Utilisateur (NUMERO, NOM) VALUES (?,?)";
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, dto.getLogin());
            ps.setString(2, dto.getPassword());

            ps.executeUpdate();
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
    }

    /**
     * UPDATE Utilisateur SET PASSWORD = ? WHERE LOGIN = ?
     *
     * update entry with the same LOGIN
     *
     * @param dto object to update
     * @throws DAOException
     */
    @Override
    public void update(Utilisateur dto) throws DAOException {
        String sql = "UPDATE Utilisateur SET PASSWORD = ? WHERE LOGIN = ?";
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, dto.getPassword());

            ps.setString(2, dto.getLogin());

            ps.executeUpdate();
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
    }

}
