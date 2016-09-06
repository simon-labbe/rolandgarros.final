<%-- 
    Document   : JSP
    Author     : poec_sl
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <jsp:include page="partial_head.jsp"/>
    <body class="lg">

    </div>
    <div id="content">
        <div>
            <%request.getSession().removeAttribute("user");%>
            <h1>vous vous êtes déconnecté avec succès.</h1>
            <p><a href="${pageContext.request.contextPath}">Se Connecter</a></p>
        </div>
        <jsp:include page="partial_footer.jsp"/>
        <jsp:include page="partial_topbar.jsp"/>
    </div>
</body>
<script src="${pageContext.request.contextPath}/js/rg.js"></script>
</html>