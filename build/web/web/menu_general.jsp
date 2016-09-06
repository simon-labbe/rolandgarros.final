<%-- 
    Document   : JSP
    Author     : poec_sl
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <jsp:include page="partial_head.jsp"/>
    <body>


        <div id="content">
            <div id="inner" class="vert">
                <div id="pop" class="vert">
                    <h1>Menu général</h1>
                    <p>ici, vous pouvez enregistrer des joueurs, enregistrer des arbitres, enregistrer des courts, planifier des matchs ou enregistrer des résultats de match.</p>
                </div>
                <div id="db" class="vert">
                    <table class="mg">
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/record?record=record_players.jsp">
                                    enregistrer des joueurs<br>
                                    <img width="200px" src="${pageContext.request.contextPath}/images/pla.png"></img>
                                </a></td>
                            <td><a href="${pageContext.request.contextPath}/record?record=record_referee.jsp">
                                    enregistrer des arbitres<br>
                                    <img width="200px" src="${pageContext.request.contextPath}/images/ref.png"></img>
                                </a></td>
                            <td><a href="${pageContext.request.contextPath}/record?record=record_courts.jsp">
                                    enregistrer des courts<br>
                                    <img width="200px" src="${pageContext.request.contextPath}/images/cou.png"></img>
                                </a></td>
                        </tr><tr>
                            <td><a href="${pageContext.request.contextPath}/record?record=record_match.jsp">
                                    planifier des matchs<br>
                                    <img width="200px" src="${pageContext.request.contextPath}/images/mat.jpg"></img>
                                </a></td>
                            <td><a href="dc.jsp">
                                    se déconecter du site<br>
                                    <img width="200px" src="${pageContext.request.contextPath}/images/log.png"></img>
                                </a></td>

                        </tr></table>
                </div>
            </div>
            <div id="rmenu">
            </div>
            <jsp:include page="partial_footer.jsp"/>
            <jsp:include page="partial_topbar.jsp"/>
        </div>
    </body>
    <script src="${pageContext.request.contextPath}/js/rg.js"></script>
</html>