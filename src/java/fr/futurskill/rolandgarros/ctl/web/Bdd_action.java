/*
 */
package fr.futurskill.rolandgarros.ctl.web;

import fr.futurskill.rolandgarros.dto.dao.DAO;
import fr.futurskill.rolandgarros.dto.dao.DAOConfigurationException;
import fr.futurskill.rolandgarros.dto.dao.DAOException;
import fr.futurskill.rolandgarros.dto.dao.DAOFactory;
import fr.futurskill.rolandgarros.dto.entites.Arbitre;
import fr.futurskill.rolandgarros.dto.entites.Court;
import fr.futurskill.rolandgarros.dto.entites.Joueur;
import fr.futurskill.rolandgarros.dto.entites.Match;
import fr.futurskill.rolandgarros.dto.entites.TypeTournoi;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author poec_sl
 */
@WebServlet(name = "bdd_action", urlPatterns = {"/bddaction"})
public class Bdd_action extends HttpServlet {

    private static final long serialVersionUID = 1L;

    final static String FORWARD = "web/request_action.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        LogRequest log = new LogRequest();
        try {

            if (null != request.getParameter("delete")) {
                boolean isempty = delete(request);
                request.setAttribute("gback", request.getParameter("form"));
                request.setAttribute("form", request.getContextPath() + "/record?record=" + request.getParameter("form"));
                if (isempty) {
                    request.setAttribute("msg",
                            "<p style=\"color:red\">aucun élément sélectionnés<br></p>");
                } else {
                    request.setAttribute("msg",
                            "<p style=\"color:green\">suppresssion réussie<br></p>");
                }
                request.getRequestDispatcher(FORWARD).forward(request, response);
            } else if (null != request.getParameter("select")) {

                Map<String, Object> m = select(request);
                m.entrySet().stream().forEach((entry) -> {
                    request.setAttribute(entry.getKey(), entry.getValue());
                });
                String selpars = "&select=" + request.getParameter("select") + "id=" + request.getParameter("id");
                request.getRequestDispatcher("/record?record=" + request.getParameter("form") + selpars).forward(request, response);
            } else if (null != request.getParameter("update")) {

                log = update(request, log);
                request.setAttribute("gback", request.getParameter("form"));
                request.setAttribute("form", request.getContextPath() + "/record?record=" + request.getParameter("form"));
                request.setAttribute("msg",
                        "<p style=\"color:green\">modification de "
                        + log.form + " réussi<br>" + log.o.toString() + "</p>");
                request.getRequestDispatcher(FORWARD).forward(request, response);
            } else if (null != request.getParameter("insert")) {
                log = insert(request, log);
                request.setAttribute("gback", request.getParameter("form"));
                request.setAttribute("form", request.getContextPath() + "/record?record=" + request.getParameter("form"));
                request.setAttribute("msg",
                        "<p style=\"color:green\">ajout de "
                        + log.form + " réussi<br>" + log.o.toString() + "</p>");
                request.getRequestDispatcher(FORWARD).forward(request, response);
            } else {
                request.setAttribute("gback", request.getParameter("form"));
                request.setAttribute("form", request.getContextPath() + "/record?record=" + request.getParameter("form"));
                request.setAttribute("msg",
                        "<p style=\"color:red\">wtf are you doing?</p>");
                request.getRequestDispatcher(FORWARD).forward(request, response);
            }

        } catch (DAOConfigurationException | DAOException | ServletException | IOException ex) {
            request.setAttribute("form", request.getContextPath() + "/record?record=" + request.getParameter("form"));
            request.setAttribute("msg",
                    "<p style=\"color:red\">action sur "
                    + log.form + " échoué<br>" + log.o.toString() + "<br>" + ex.getMessage() + "</p>");
            request.getRequestDispatcher(FORWARD).forward(request, response);

        }
    }

    private Map<String, Object> select(HttpServletRequest request) throws DAOException, DAOConfigurationException {
        Map<String, Object> m = new HashMap<>();

        switch (request.getParameter("select")) {
            case "joueur":
                Joueur jo = DAOFactory.getInstance().getJoueurDAO().findById(Integer.parseInt(request.getParameter("id")));
                m.put("oid", jo.getId());
                m.put("nom", jo.getNom());
                m.put("prenom", jo.getPrenom());
                m.put("sexe", jo.getSexe());
                m.put("nation", jo.getNation());
                m.put("classement", jo.getClassement());
                break;
            case "arbitre":
                Arbitre ar = DAOFactory.getInstance().getArbitreDAO().findById(Integer.parseInt(request.getParameter("id")));
                m.put("oid", ar.getId());
                m.put("nom", ar.getNom());
                m.put("prenom", ar.getPrenom());
                m.put("nation", ar.getNation());
                m.put("classement", ar.getNiveau());
                break;
            case "court":
                Court co = DAOFactory.getInstance().getCourtsDAO().findById(Integer.parseInt(request.getParameter("id")));
                m.put("oid", co.getNumero());
                m.put("courtnom", co.getNom());
                break;
            case "match":
                Match ma = DAOFactory.getInstance().getMatchDAO().findById(Integer.parseInt(request.getParameter("id")));
                m.put("oid", ma.getId());
                m.put("typetournoi", ma.getRefType().getRefType());
                m.put("datetournoi", ma.getDateTournoi().toString());
                m.put("nocourt", ma.getNocourt().getNumero());
                m.put("arbitre", ma.getRefArbitre().getId());
                m.put("joueur1", ma.getRefJoueur1().getId());
                m.put("joueur2", ma.getRefJoueur2().getId());
                m.put("nbsets1", ma.getNbSetsJ1());
                m.put("nbsets2", ma.getNbSetsJ2());
                break;
            default:
                throw new DAOException("invalid request (select)");
        }
        return m;
    }

    private boolean delete(HttpServletRequest request) throws DAOConfigurationException, DAOException {

        boolean isEmptyRequest = true;
        int nbi = Integer.parseInt(request.getParameter("nbi"));
        String form = request.getParameter("form");
        DAOFactory facto = DAOFactory.getInstance();
        DAO d;

        switch (form) {
            case "record_players.jsp":
                d = facto.getJoueurDAO();
                break;
            case "record_referee.jsp":
                d = facto.getArbitreDAO();
                break;
            case "record_courts.jsp":
                d = facto.getCourtsDAO();
                break;
            case "record_match.jsp":
                d = facto.getMatchDAO();
                break;
            default:
                throw new DAOException("invalid request (delete)");
        }
        for (int i = 0; i <= nbi; i++) {
            if (null != request.getParameter("" + i)) {
                isEmptyRequest = false;
                d.delete(Integer.parseInt(request.getParameter("" + i)));
            }
        }
        return isEmptyRequest;
    }

    private LogRequest update(HttpServletRequest request, LogRequest log) throws DAOException, DAOConfigurationException {

        switch (request.getParameter("form")) {
            case "record_courts.jsp":
                log.form = "Court";
                log.o = new Court(
                        Integer.parseInt(request.getParameter("id")),
                        request.getParameter("nom")
                );
                DAOFactory.getInstance().getCourtsDAO().update((Court) log.o);
                break;
            case "record_players.jsp":
                log.form = "Joueur";
                log.o = new Joueur(
                        Integer.parseInt(request.getParameter("classement")),
                        request.getParameter("sexe").charAt(0),
                        Integer.parseInt(request.getParameter("id")),
                        request.getParameter("nom"),
                        request.getParameter("prenom"),
                        request.getParameter("nationalite")
                );
                DAOFactory.getInstance().getJoueurDAO().update((Joueur) log.o);
                break;
            case "record_referee.jsp":
                log.form = "Arbitre";
                log.o = new Arbitre(
                        Integer.parseInt(request.getParameter("niveau")),
                        Integer.parseInt(request.getParameter("id")),
                        request.getParameter("nom"),
                        request.getParameter("prenom"),
                        request.getParameter("nationalite")
                );
                DAOFactory.getInstance().getArbitreDAO().update((Arbitre) log.o);
                break;
            case "record_match.jsp":
                log.form = "Match";
                log.o = new Match(
                        Integer.parseInt(request.getParameter("id")),
                        getTypeTournoi(Integer.parseInt(request.getParameter("reftype"))),
                        shapeDate(request.getParameter("matchtime")),
                        getCourt(Integer.parseInt(request.getParameter("nocourt"))),
                        getArbitre(Integer.parseInt(request.getParameter("refArbitre"))),
                        getJoueur(Integer.parseInt(request.getParameter("refJoueur1"))),
                        getJoueur(Integer.parseInt(request.getParameter("refJoueur2"))),
                        Integer.parseInt(request.getParameter("nbsetsJ1")),
                        Integer.parseInt(request.getParameter("nbsetsJ2"))
                );
                DAOFactory.getInstance().getMatchDAO().update((Match) log.o);
                break;

            default:
                throw new DAOException("invalid request (update)");
        }
        return log;
    }

    private LogRequest insert(HttpServletRequest request, LogRequest log) throws DAOConfigurationException, DAOException {

        switch (request.getParameter("form")) {
            case "record_players.jsp":
                log.form = "Joueur";
                log.o = new Joueur(
                        Integer.parseInt(
                                request.getParameter("classement")),
                        request.getParameter("sexe").charAt(0),
                        request.getParameter("nom"),
                        request.getParameter("prenom"),
                        request.getParameter("nationalite")
                );
                DAOFactory.getInstance().getJoueurDAO().insert((Joueur) log.o);
                break;
            case "record_courts.jsp":
                log.form = "Court";
                log.o = new Court(
                        request.getParameter("nom")
                );
                DAOFactory.getInstance().getCourtsDAO().insert((Court) log.o);
                break;
            case "record_referee.jsp":
                log.form = "Arbitre";
                log.o = new Arbitre(
                        Integer.parseInt(
                                request.getParameter("niveau")),
                        request.getParameter("nom"),
                        request.getParameter("prenom"),
                        request.getParameter("nationalite")
                );
                DAOFactory.getInstance().getArbitreDAO().insert((Arbitre) log.o);
                break;
            case "record_match.jsp":
                log.form = "Match";
                log.o = new Match(
                        getTypeTournoi(
                                Integer.parseInt(
                                        request.getParameter("reftype"))),
                        shapeDate(request.getParameter("matchtime")),
                        getCourt(
                                Integer.parseInt(
                                        request.getParameter("nocourt"))),
                        getArbitre(
                                Integer.parseInt(
                                        request.getParameter("refArbitre"))),
                        getJoueur(
                                Integer.parseInt(
                                        request.getParameter("refJoueur1"))),
                        getJoueur(
                                Integer.parseInt(
                                        request.getParameter("refJoueur2"))),
                        Integer.parseInt(
                                request.getParameter("nbsetsJ1")),
                        Integer.parseInt(
                                request.getParameter("nbsetsJ2"))
                );
                DAOFactory.getInstance().getMatchDAO().insert((Match) log.o);
                break;

            default:
                throw new DAOException("invalid request (insert)");
        }
        return log;
    }

    private Date shapeDate(String par) {
        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(par);
            return new java.sql.Date(date.getTime());
        } catch (ParseException ex) {
            return new java.sql.Date(Calendar.getInstance().getTime().getTime());
        }
    }

    private TypeTournoi getTypeTournoi(int par) throws DAOConfigurationException, DAOException {
        return DAOFactory.getInstance().getTypeTournoiDAO().findById(par);
    }

    private Arbitre getArbitre(int par) throws DAOConfigurationException, DAOException {
        return DAOFactory.getInstance().getArbitreDAO().findById(par);
    }

    private Joueur getJoueur(int par) throws DAOConfigurationException, DAOException {
        return DAOFactory.getInstance().getJoueurDAO().findById(par);
    }

    private Court getCourt(int par) throws DAOConfigurationException, DAOException {
        return DAOFactory.getInstance().getCourtsDAO().findById(par);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static class LogRequest {

        private String form;
        private Object o;

        public LogRequest() {
            form = "";
            o = new Object();
        }
    }

}
