<%-- 
    Document   : request_action
    Author     : poec_sl
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page contentType="text/html"%>
<!DOCTYPE html>
<html>
    <body class="lg">
        <jsp:include page="partial_topbar.jsp"/>
        <div id="content" class="lg">
            ${msg}
            <br>
            <form action="${form}">
                <input type="hidden" name="record" value="${gback}"/>
                <input type="submit" value="retour"/>
            </form>
            <br>
            <jsp:include page="partial_footer.jsp"/>
            <jsp:include page="partial_head.jsp"/>
        </div>
    </div>
</body>
<script src="${pageContext.request.contextPath}/js/rg.js"></script>
</html>