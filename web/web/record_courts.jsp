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
                    <h1>Enregistrer des courts</h1>
                    <p>enregistrer un nouveau court en indiquant son nom.</p>
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
                                <td>Nom</td><td><input class="vert" type="text" name="nom" required value="${courtnom}" /></td>
                            </tr>

                            <input type="hidden" name="form" value="record_courts.jsp"/>
                            <input type="hidden" name="${null!=oid?"update":"insert"}" value="action"/>
                            <tr><td>
                                    <input type="submit" value="éditer le court" ${null!=oid?"":"disabled"}/>
                                </td><td>
                                    <input type="submit" value="ajouter le court"  ${null==oid?"":"disabled"}/>
                                </td>
                            </tr>
                            <tr><td>
                        </table>
                    </form>
                </div>
            </div>
            <div id="rmenu">
                <form action="${pageContext.request.contextPath}/bddaction" method="post">
                    <input type="hidden" name="form" value="record_courts.jsp"/>
                    <input type="hidden" name="delete" value="record_courts.jsp"/>
                    <table class="rtable"><tr><th colspan="1">Liste des Courts enregistrés</th><th>select</th><th>edit</th></tr>
                                <c:forEach var="l" items="${itemlist}" varStatus="i">
                            <tr><td>${l.getNom()}
                                </td>
                                <td>
                                    <input type="checkbox" name="${i.index}" value="${l.getNumero()}"/>${l.getNumero()}
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/bddaction?select=court&id=${l.getNumero()}&form=record_courts.jsp">
                                        <img width="15px" src="${pageContext.request.contextPath}/images/edit.png"></img>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                        <tr>
                            <td colspan="3">
                                <input title="supprimer les Courts sélectionnés" class="del" type="submit" value="supprimer"/>
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