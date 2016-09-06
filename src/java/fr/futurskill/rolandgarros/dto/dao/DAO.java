/*
 */
package fr.futurskill.rolandgarros.dto.dao;

import java.util.List;

/**
 *
 * @author poec_sl
 * @param <T>
 */
public interface DAO<T> {

    /**
     *
     * @return all BDD table items
     * @throws DAOException
     */
    List<T> findAll() throws DAOException;

    /**
     *
     * @param dto
     * @return BDD item with given id
     * @throws DAOException
     */
    T findById(int dto) throws DAOException;

    /**
     * delete item with id
     *
     * @param id
     * @throws DAOException
     */
    void delete(int id) throws DAOException;

    /**
     * insert T dao
     *
     * @param dao
     * @throws DAOException
     */
    void insert(T dao) throws DAOException;

    /**
     * update T dao
     *
     * @param dao
     * @throws DAOException
     */
    void update(T dao) throws DAOException;
}
