/*
 */
package fr.futurskill.rolandgarros.dto.dao;

import fr.futurskill.rolandgarros.dto.entites.Arbitre;
import fr.futurskill.rolandgarros.dto.entites.Court;
import fr.futurskill.rolandgarros.dto.entites.Joueur;
import fr.futurskill.rolandgarros.dto.entites.Match;
import fr.futurskill.rolandgarros.dto.entites.TypeTournoi;
import fr.futurskill.rolandgarros.dto.persistance.JDBCArbitre;
import fr.futurskill.rolandgarros.dto.persistance.JDBCCourt;
import fr.futurskill.rolandgarros.dto.persistance.JDBCJoueur;
import fr.futurskill.rolandgarros.dto.persistance.JDBCMatch;
import fr.futurskill.rolandgarros.dto.persistance.JDBCTypeTournoi;
import fr.futurskill.rolandgarros.dto.persistance.JDBCUtilisateur;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author poec_sl
 */
public class DAOFactory {

    private static final String FICHIER_PROPERTIES = "fr/futurskill/rolandgarros/dto/dao/dao.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    private static final String PROPERTY_MOT_DE_PASSE = "motdepasse";

    private final String url;
    private final String username;
    private final String password;

    /* package */
    DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * singleton
     *
     * @return instance
     * @throws DAOConfigurationException
     */
    public static DAOFactory getInstance() throws DAOConfigurationException {
        Properties properties = new Properties();
        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);

        if (fichierProperties == null) {
            throw new DAOConfigurationException("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.");
        }

        try {
            properties.load(fichierProperties);
            url = properties.getProperty(PROPERTY_URL);
            driver = properties.getProperty(PROPERTY_DRIVER);
            nomUtilisateur = properties.getProperty(PROPERTY_NOM_UTILISATEUR);
            motDePasse = properties.getProperty(PROPERTY_MOT_DE_PASSE);
        } catch (FileNotFoundException e) {
            throw new DAOConfigurationException("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.", e);
        } catch (IOException e) {
            throw new DAOConfigurationException("Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e);
        }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationException("Le driver est introuvable dans le classpath.", e);
        }

        DAOFactory instance = new DAOFactory(url, nomUtilisateur, motDePasse);
        return instance;
    }

    /**
     * open connection
     *
     * @return Connexion to database
     * @throws DAOException
     */
    public Connection getConnection() throws DAOException {
        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection(url, username, password);
            connexion.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DAOException("Connexion impossible.", e);
        }
        return connexion;
    }

    /**
     * close connection
     *
     * @param connexion
     * @throws DAOException
     */
    public void closeConnexion(Connection connexion) throws DAOException {
        try {
            connexion.close();
        } catch (SQLException e) {
            throw new DAOException("Fermeture connexion incorrecte.", e);
        }
    }

    /**
     *
     * @return DAO Arbitre
     */
    public DAO<Arbitre> getArbitreDAO() {
        return new JDBCArbitre();
    }

    /**
     *
     * @return DAO Joueur
     */
    public DAO<Joueur> getJoueurDAO() {
        return new JDBCJoueur();
    }

    /**
     *
     * @return DAO Court
     */
    public DAO<Court> getCourtsDAO() {
        return new JDBCCourt();
    }

    /**
     *
     * @return DAO Match
     */
    public DAO<Match> getMatchDAO() {
        return new JDBCMatch();
    }

    /**
     *
     * @return DAO TypeTournoi
     */
    public DAO<TypeTournoi> getTypeTournoiDAO() {
        return new JDBCTypeTournoi();
    }

    /**
     *
     * @return DAO Utilisateur
     */
    public JDBCUtilisateur getUtilisateurDAO() {
        return new JDBCUtilisateur();
    }

}
