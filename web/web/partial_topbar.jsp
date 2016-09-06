<%-- 
    Document   : partial_topbar
    Author     : poec_sl
--%>
<!DOCTYPE html>
<div id="topbar" class="inline" >
    <figure id="logo" class="inline"><img class="logopic" alt="logo" src="${pageContext.request.contextPath}/images/logo.png" />
    </figure>
    <div id="menu">
        <span id="title" class="inline">ROLLAND GARROS</span>
        <span class="inline mb"><a title="menu" href="${pageContext.request.contextPath}/web/menu_general.jsp" class="cen">Home</a></span>
        <span class="inline mb"><a href="#" class="cen">About</a></span>
        <span class="inline mb"><a href="#" class="cen">Contact</a></span>
        <span class="inline mb">
            <a title="retour sur l'acceuil" href="${pageContext.request.contextPath}/web/dc.jsp" class="cen">
                ${(sessionScope["user"]!=null?'Déconnection '.concat(sessionScope["user"]):"Connection")}
            </a></span>
    </div>

</div>

