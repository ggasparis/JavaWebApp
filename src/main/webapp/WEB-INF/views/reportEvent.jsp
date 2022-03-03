<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Report Event</title>
    <jsp:include page="jsp_packets/head.jsp"/>
</head>

<body>

<jsp:include page="jsp_packets/parentHeader.jsp"/>


<div class="content-wrapper">
    <br><br>
    <div class="container" align="center">
        <div class="panel panel-default">
            <div class="panel-heading"><h4><strong><b>Αναφορά Event "${event.getTitle()}"</b></strong></h4></div>
            <div class="panel-body" style="font-size: medium;">

                <div class="col-md-6" style="font-size: inherit">

                    <form method="POST" action="/report">
                        <div class="login-input" style="margin-bottom:10px;">
                            <input type="hidden" name="eventId" value="${event.getId()}" >
                            <input type="text" name="comments" placeholder="Your comments" required>

                            <input type="submit" name="register" class="btn btn-danger" value="Αναφορά" style="margin-top: 20px; cursor: pointer;">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="jsp_packets/footer.jsp"/>
<jsp:include page="jsp_packets/scripts.jsp"/>

</body>



</html>
