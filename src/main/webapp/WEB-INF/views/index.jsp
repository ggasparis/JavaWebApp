<%@ page import="java.util.Random" %>
<%@ page import="gr.ntua.ece.project.entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <title>Home</title>

    <jsp:include page="jsp_packets/head.jsp"/>

    <style rel="stylesheet">
        .viewbody {
            background: url("${contextPath}/resources/img/cloud_background.jpg") no-repeat center center;
            background-size: 100% 100%;
            height: 100vh;
        }
    </style>


    <!-- JQuery -->
    <script type="text/javascript" src="//code.jquery.com/jquery-2.2.3.min.js"></script>
    <!-- JQuery UI -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script type="text/javascript" src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<body>


<%int Anon=(int)request.getAttribute("role"); %>
<%if (Anon==0) {%>
<jsp:include page="jsp_packets/header.jsp"/>
<%}else if (Anon==1) {%>
<jsp:include page="jsp_packets/adminHeader.jsp"/>
<%}else if (Anon==2) {%>
<jsp:include page="jsp_packets/parentHeader.jsp"/>
<%}else if (Anon==3) {%>
<jsp:include page="jsp_packets/providerHeader.jsp"/>

<%}%>

<jsp:include page="jsp_packets/search.jsp"/>
<jsp:include page="jsp_packets/footer.jsp"/>

<jsp:include page="jsp_packets/scripts.jsp"/>

</body>
</html>
