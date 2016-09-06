<%-- 
    Document   : JSP
    Author     : poec_sl
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <jsp:include page="partial_head.jsp"/>
    <body class="lg">
        <div id="content" class="lg">
            <div class="contentlg">
                <form action="login" method="post">
                    <table>
                        <h2>Connexion</h2>
                        <tr><td>
                                <input class="vert" type="text" name="login" required />
                        </tr></td>
                        <tr><td>
                                <input class="vert" type="password" name="password" required />
                        </tr></td>
                        <tr><td>
                                <span class="vert"><input type="checkbox" disabled=""/>Se souvenir de moi</span>
                        </tr></td>
                        <tr><td>
                                <span class="vert">${valid}</span>
                            </td></tr>
                    </table>
                    <input type="submit"/>
                </form>
            </div>
            <jsp:include page="partial_footer.jsp"/>
        </div>
    </body>
    <script src="${pageContext.request.contextPath}/js/rg.js"></script>
</html>