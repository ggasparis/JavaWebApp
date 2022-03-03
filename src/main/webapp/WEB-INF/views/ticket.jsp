<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
    <meta charset="utf-8">
    <title>Ticket</title>
    <jsp:include page="jsp_packets/head.jsp"/>
</head>

<body>

<jsp:include page="jsp_packets/parentHeader.jsp"/>

<div class="content-wrapper">
    <br><br>
    <div class="container" align="center">
        <div class="panel panel-default">
            <div class="panel-heading"><h4><strong><b>Επιτυχής αγορά για: "${event.getTitle()}"</b></strong></h4></div>
            <div class="panel-body" style="font-size: medium;">

                <div class="col-md-6" style="font-size: inherit">

                    <form method="POST" action="/book">
                        <div class="login-input" style="margin-bottom:10px;">
                                <div class="col-md-6">
                                    <img src="${event.getEventPhotosById().iterator().next().getLink()}" class="img-thumbnail" alt="Aliii">
                                </div>
                            <h4>Αριθμός εισιτηρίων: ${ticket.getQuantity()}</h4>
                            <h4>Συνολική τιμή: ${ticket.getQuantity()*event.getTicketPrice()}</h4>
                            <a href="/ticket${ticket.getId()}.pdf"  target="_blank" class="btn btn-success">Προβολή pdf</a>
                            <input type="hidden" name="ticketId" value="${ticket.getId()}" >
                            <a href="/parent" class="btn btn-success">Επιστροφή στην αρχική</a>
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
