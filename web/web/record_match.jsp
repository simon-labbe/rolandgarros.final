<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="fr.futurskill.rolandgarros.dto.entites.Match"%>
<%@page import="fr.futurskill.rolandgarros.dto.entites.Court"%>
<%@page import="fr.futurskill.rolandgarros.dto.entites.Arbitre"%>
<%@page import="fr.futurskill.rolandgarros.dto.entites.TypeTournoi"%>
<%@page import="fr.futurskill.rolandgarros.dto.entites.Joueur"%>
<%@page import="fr.futurskill.rolandgarros.dto.dao.DAOFactory"%>
<%@page import="java.util.Collection"%>
﻿<%--
    Document   : JSP
    Author     : poec_sl
--%>
<html>
    <jsp:include page="partial_head.jsp"/>
    <body>
        <div id="content">
            <div id="inner" class="vert">
                <div id="pop" class="vert">
                    <h1>${null!=oid?"éditer":"ajouter"} des matchs</h1>
                    <p>
                        sélectionnez un match dans la liste de droite<br>
                        pour l'éditer
                    </p>
                </div>
                <div id="db" class="vert">
                    <form action="${pageContext.request.contextPath}/bddaction" method="post">
                        <table>
                            <tr>
                                <td>id</td>
                                <td>
                                    <input name="id" type="number" readonly value="${oid}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Tournoi</td><td>
                                    <select name="reftype">
                                        <c:forEach var="l" items="${itemlistt}" varStatus="i">
                                            <option value="${l.getRefType()}" ${((null != reftype && l.getRefType() == Integer.parseInt(reftype))?"selected" : "")}>${l.getNomTournoi()}</option>
                                        </c:forEach>
                                    </select></td></tr>
                            <td>Joueur 1</td><td>
                                <select name="refJoueur1">
                                    <c:forEach var="l" items="${itemlistj}" varStatus="i">
                                        <option value="${l.getId()}" ${((null != joueur1 && l.getId() == Integer.parseInt(joueur1))?"selected" : "")}>${l.getNom()} ${l.getPrenom()}</option>
                                    </c:forEach>
                                </select></td></tr>
                            <td>Joueur 2</td><td>
                                <select name="refJoueur2">
                                    <c:forEach var="l" items="${itemlistj}" varStatus="i">
                                        <option value="${l.getId()}" ${((null != joueur2 && l.getId() == Integer.parseInt(joueur2))?"selected" : "")}>${l.getNom()} ${l.getPrenom()}</option>
                                    </c:forEach>
                                </select></td></tr>
                            <td>Arbitre</td><td>
                                <select name="refArbitre">
                                    <c:forEach var="l" items="${itemlista}" varStatus="i">
                                        <option value="${l.getId()}" ${((null != arbitre && l.getId() == Integer.parseInt(arbitre))?"selected" : "")}>${l.getNom()} ${l.getPrenom()}</option>
                                    </c:forEach>
                                </select></td></tr>
                            </td></tr>
                            <td>Court</td><td>
                                <select name="nocourt">
                                    <c:forEach var="l" items="${itemlistc}" varStatus="i">
                                        <option value="${l.getNumero()}" ${((null != nocourt && l.getNumero() == Integer.parseInt(nocourt))?"selected" : "")}>${l.getNom()}</option>
                                    </c:forEach>
                                </select>
                            </td></tr>
                            <tr><td>Date du match</td><td><input type="date" name="matchtime" value="${datetournoi}"/></td></tr>
                            <tr><td>nombre de sets joueur 1</td><td><input type="number" name="nbsetsJ1" value="${null!=nbsets1?nbsets1:0}" ${null!=oid?"":"readonly"}/></td></tr>
                            <tr><td>nombre de sets joueur 1</td><td><input type="number" name="nbsetsJ2" value="${null!=nbsets2?nbsets2:0}" ${null!=oid?"":"readonly"}/></td></tr>
                            <input type="hidden" name="form" value="record_match.jsp"/>
                            <input type="hidden" name="${null!=oid?"update":"insert"}" value="action"/>
                            <tr><td>
                                    <input type="submit" value="éditer le match" ${null!=oid?"":"disabled"}/>
                                </td><td>
                                    <input type="submit" value="ajouter le match"  ${null==oid?"":"disabled"}/>
                                </td>
                            </tr>
                            <tr><td>
                                </td></tr>
                        </table>

                    </form>
                </div>
            </div>
            <div id="rmenu">
                <form action="${pageContext.request.contextPath}/bddaction" method="post">
                    <input type="hidden" name="form" value="record_match.jsp"/>
                    <input type="hidden" name="delete" value="record_match.jsp"/>
                    <table class="rtable"><tr><th colspan="10">Liste des Match enregistrés</th></tr>
                        <tr><th>type</th><th>date</th><th>court</th><th>arbitre</th><th>joueur1</th>
                            <th>joueur2</th><th>set j1</th><th>set j2</th><th>select</th><th>edit</th></tr>
                                <c:forEach var="l" items="${itemlist}" varStatus="i">
                            <tr>
                                <td>
                                    ${itemmapt[l.getRefType().getRefType()]}
                                </td>
                                <td>
                                    ${l.getDateTournoi().toString()}
                                </td>
                                <td>
                                    ${itemmapc[l.getNocourt().getNumero()].toString()}
                                </td>
                                <td>
                                    ${itemmapa[l.getRefArbitre().getId()]}
                                </td>
                                <td>
                                    ${itemmapj[l.getRefJoueur1().getId()]}
                                </td>
                                <td>
                                    ${itemmapj[l.getRefJoueur2().getId()]}
                                </td>
                                <td>
                                    ${l.getNbSetsJ1()}
                                </td>
                                <td>
                                    ${l.getNbSetsJ2()}
                                </td>
                                <td>
                                    <input type="checkbox" name="${i.index}" value="${l.getId()}"/>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/bddaction?select=match&id=${l.getId()}&form=record_match.jsp">
                                        <img width="15px" src="${pageContext.request.contextPath}/images/edit.png"></img>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="6"></td>
                            <td colspan="4">
                                <input title="supprimer les Matchs sélectionnés" class="del" type="submit" value="supprimer"/>
                            </td>
                        </tr>
                    </table>
                    <input type="hidden" name="nbi" value="${itemlist.size()}"/>
                </form>
            </div>
            <jsp:include page="partial_footer.jsp"/>
            <jsp:include page="partial_topbar.jsp"/>
        </div>
        <script src="${pageContext.request.contextPath}/js/rg.js"></script>
    </body>
</html>