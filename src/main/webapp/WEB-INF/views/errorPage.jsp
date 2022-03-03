<%@ page import="gr.ntua.ece.project.entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <jsp:include page="jsp_packets/head.jsp"/>
    <title>RozPanda | Profile</title>
</head>

<body>

<jsp:include page="jsp_packets/header.jsp"/>

<div class="content-wrapper">
    <br><br>
    <div class="container" align="center">
        <div class="panel panel-default">
            <div class="panel-heading"><h4><strong><b>Ουπς! Κάτι πήγε στραβά...</b></strong></h4></div>
        </div>
    </div>
    <div class="text-center">
    <img src="${contextPath}/resources/img/Jimmy.png" class="img-responsive center-block" alt="Jimmy">
    </div>
</div>


<jsp:include page="jsp_packets/footer.jsp"/>
<jsp:include page="jsp_packets/scripts.jsp"/>

</body>
</html>
