/*
 */
package fr.futurskill.rolandgarros.dto.persistance;

import fr.futurskill.rolandgarros.dto.dao.DAO;
import fr.futurskill.rolandgarros.dto.dao.DAOConfigurationException;
import fr.futurskill.rolandgarros.dto.dao.DAOException;
import fr.futurskill.rolandgarros.dto.dao.DAOFactory;
import fr.futurskill.rolandgarros.dto.entites.TypeTournoi;
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
public class JDBCTypeTournoi implements DAO<TypeTournoi> {

    /**
     * SELECT * FROM TypeTournoi
     *
     * @return the list of all TypeTournoi stored in the database converted as
     * objects
     * @throws DAOException
     */
    @Override
    public List<TypeTournoi> findAll() throws DAOException {
        String sql = "SELECT * FROM TypeTournoi";
        List<TypeTournoi> ls = new ArrayList<>();
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    ls.add(new TypeTournoi(
                            rs.getInt("REFERENCETYPE"),
                            rs.getString("NOMTOURNOI"),
                            rs.getInt("NBSETS")));
                }
            }
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
        return ls;
    }

    /**
     * SELECT * FROM TypeTournoi WHERE REFERENCETYPE = ?
     *
     * select by id
     *
     * @param id id of the object to get
     * @return object having the id
     * @throws DAOException
     */
    @Override
    public TypeTournoi findById(int id) throws DAOException {

        String sql = "SELECT * FROM TypeTournoi WHERE REFERENCETYPE = ?";
        TypeTournoi ls = null;
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    ls = new TypeTournoi(
                            rs.getInt("REFERENCETYPE"),
                            rs.getString("NOMTOURNOI"),
                            rs.getInt("NBSETS"));
                }
            }
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
        return ls;
    }

    /**
     * DELETE FROM TypeTournoi WHERE REFERENCETYPE = ?
     *
     * delete the matching databalse entry with dto id
     *
     * @param id object id to delete
     * @throws DAOException
     */
    @Override
    public void delete(int id) throws DAOException {
        TypeTournoi dto = findById(id);
        String sql = "DELETE FROM TypeTournoi WHERE REFERENCETYPE = ?";
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, dto.getRefType());

            ps.executeUpdate();
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
    }

    /**
     * INSERT INTO TypeTournoi (REFERENCETYPE, NOMTOURNOI, NBSETS) VALUES
     * (?,?,?)
     *
     * insert new database entry (ignoring id because of auto_increment)
     *
     * @param dto object to insert
     * @throws DAOException
     */
    @Override
    public void insert(TypeTournoi dto) throws DAOException {
        String sql = "INSERT INTO TypeTournoi (REFERENCETYPE, NOMTOURNOI, NBSETS) VALUES (?,?,?)";
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, dto.getRefType());
            ps.setString(2, dto.getNomTournoi());
            ps.setInt(3, dto.getNbSets());

            ps.executeUpdate();
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
    }

    /**
     * UPDATE TypeTournoi SET NOMTOURNOI = ?, NBSETS WHERE REFERENCETYPE = ?
     *
     * update entry with the same id
     *
     * @param dto object to update
     * @throws DAOException
     */
    @Override
    public void update(TypeTournoi dto) throws DAOException {
        String sql = "UPDATE TypeTournoi SET NOMTOURNOI = ?, NBSETS  WHERE REFERENCETYPE = ?";
        try (Connection con = DAOFactory.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, dto.getNomTournoi());
            ps.setInt(2, dto.getNbSets());

            ps.setInt(3, dto.getRefType());

            ps.executeUpdate();
        } catch (SQLException | DAOConfigurationException e) {
            throw new DAOException(e);
        }
    }

}
