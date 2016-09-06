/*
 */
package fr.futurskill.rolandgarros.ctl.web;

import fr.futurskill.rolandgarros.dto.dao.DAOConfigurationException;
import fr.futurskill.rolandgarros.dto.dao.DAOException;
import fr.futurskill.rolandgarros.dto.dao.DAOFactory;
import fr.futurskill.rolandgarros.dto.entites.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
@WebServlet(name = "bdd_action", urlPatterns = {"/record"})
public class Record extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
        String forward = "index.jsp";
        try {
            System.out.println("p" + request.getParameter("gback"));
            System.out.println("a" + request.getAttribute("gback"));
            if (null != request.getParameter("gback")) {
                forward = request.getParameter("gback");
            }
            if (null != request.getParameter("record")) {
                forward = request.getParameter("record");
            }
            switch (forward) {
                case "record_courts.jsp":
                    List<Court> l = DAOFactory.getInstance().getCourtsDAO().findAll();
                    request.setAttribute("itemlist", l);
                    request.getRequestDispatcher("/web/" + forward).forward(request, response);
                    break;
                case "record_players.jsp":
                    List<Joueur> lj = DAOFactory.getInstance().getJoueurDAO().findAll();
                    request.setAttribute("itemlist", lj);
                    request.getRequestDispatcher("/web/" + forward).forward(request, response);
                    break;
                case "record_referee.jsp":
                    List<Arbitre> lr = DAOFactory.getInstance().getArbitreDAO().findAll();
                    request.setAttribute("itemlist", lr);
                    request.getRequestDispatcher("/web/" + forward).forward(request, response);
                    break;
                case "record_match.jsp":

                    List<Arbitre> lma = DAOFactory.getInstance().getArbitreDAO().findAll();
                    List<Joueur> lmj = DAOFactory.getInstance().getJoueurDAO().findAll();
                    List<Court> lmc = DAOFactory.getInstance().getCourtsDAO().findAll();
                    List<TypeTournoi> lmt = DAOFactory.getInstance().getTypeTournoiDAO().findAll();
                    List<Match> lm = DAOFactory.getInstance().getMatchDAO().findAll();

                    Map<Integer, String> ma = new HashMap<>();
                    lma.stream().forEach((a) -> {
                        ma.put(a.getId(), a.getNom());
                    });
                    Map<Integer, String> mj = new HashMap<>();
                    lmj.stream().forEach((a) -> {
                        mj.put(a.getId(), a.getNom());
                    });
                    Map<Integer, String> mc = new HashMap<>();
                    lmc.stream().forEach((a) -> {
                        mc.put(a.getNumero(), a.getNom());
                    });
                    Map<Integer, String> mt = new HashMap<>();
                    lmt.stream().forEach((a) -> {
                        mt.put(a.getRefType(), a.getNomTournoi());
                    });

                    request.setAttribute("itemmapa", ma);
                    request.setAttribute("itemmapj", mj);
                    request.setAttribute("itemmapc", mc);
                    request.setAttribute("itemmapt", mt);

                    request.setAttribute("itemlista", lma);
                    request.setAttribute("itemlistj", lmj);
                    request.setAttribute("itemlistc", lmc);
                    request.setAttribute("itemlistt", lmt);
                    request.setAttribute("itemlist", lm);
                    request.getRequestDispatcher("/web/" + forward).forward(request, response);
                    break;
                default:
                    throw new DAOException("perdu");
            }
        } catch (DAOConfigurationException | DAOException ex) {

            request.setAttribute("form", request.getContextPath() + "/web/menu_general.jsp");
            request.setAttribute("msg",
                    "<p style=\"color:red\">echec<br>" + ex.getMessage() + "</p>");
            request.getRequestDispatcher("web/request_action.jsp").forward(request, response);
        }
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

}
