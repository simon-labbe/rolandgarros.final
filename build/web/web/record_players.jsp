<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
﻿<%-- 
    Document   : JSP
    Author     : poec_sl
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Collection"%>
<html>
    <jsp:include page="partial_head.jsp"/>
    <body>

        <div id="content">
            <div id="inner" class="vert">
                <div id="pop" class="vert">
                    <h1>Enregistrement d'un nouveau joueur</h1>
                    <p>enregistrer un nouveau joueur en indiquant son nom, son prénom, son sexe et sa nationalité</p>
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
                                <td>Nom</td>
                                <td><input class="vert" type="text" name="nom" value="${nom} "required /></td>
                            </tr>
                            <tr>
                                <td>Prénom</td>
                                <td><input class="vert" type="text" name="prenom" value="${prenom}" required /></td>
                            </tr>
                            <tr>
                                <td>Sexe</td>
                                <td>
                                    <select name="sexe">
                                        <option value="M">masculin</option>
                                        <option value="F">féminin</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Nationalité</td><td>
                                    <select name="nationalite">
                                        <option value="breton">breton</option>
                                        <option value="etranger">étranger</option>
                                    </select></td>
                            </tr>
                            <tr>
                                <td>Classement</td><td>
                                    <input type="number" value="${null==classement?0:classement}" name="classement"/>
                                </td>
                            </tr>
                            <input type="hidden" name="form" value="record_players.jsp"/>
                            <input type="hidden" name="${null!=oid?"update":"insert"}" value="action"/>
                            <tr><td>
                                    <input type="submit" value="éditer le joueur" ${null!=oid?"":"disabled"}/>
                                </td><td>
                                    <input type="submit" value="ajouter le joueur"  ${null==oid?"":"disabled"}/>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
            <div id="rmenu">

                <form action="${pageContext.request.contextPath}/bddaction" method="post">
                    <input type="hidden" name="form" value="record_players.jsp"/>
                    <input type="hidden" name="delete" value="record_players.jsp"/>
                    <table class="rtable"><tr><th colspan="2">Liste des joueurs enregistrés</th><th>sexe</th><th>nation</th><th>rank</th><th>select</th><th>edit</th></tr>

                        <c:forEach var="l" items="${itemlist}" varStatus="i">
                            <tr><td>${l.getNom()}
                                </td><td>${l.getPrenom()}
                                </td><td>${l.getSexe()}
                                </td><td>${l.getNation()}
                                </td><td>${l.getClassement()}
                                </td><td><input type="checkbox" name="${i.index}" value="${l.getId()}"/>${l.getId()}
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/bddaction?select=joueur&id=${l.getId()}&form=record_players.jsp">
                                        <img width="15px" src="${pageContext.request.contextPath}/images/edit.png"></img>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr><td colspan="4"></td><td colspan="3"><input title="supprimer les Joueurs sélectionnés" class="del" type="submit" value="supprimer"/></td></tr>
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