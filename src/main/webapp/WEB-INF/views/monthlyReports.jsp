<%@ page import="gr.ntua.ece.project.entities.Event" %>
<%@ page import="gr.ntua.ece.project.entities.Ticket" %>
<%@ page import="gr.ntua.ece.project.entities.EventPhotos" %>
<%@ page import="gr.ntua.ece.project.entities.Category" %>
<%@ page import="java.util.Random" %>
<%@ page import="gr.ntua.ece.project.entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="jsp_packets/head.jsp"/>
    <title>RozPanda | Provider</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

    <style>
        label{
            font-size: inherit;
            width: 100%;
        }
        .table tr {
            cursor: pointer;
        }
    </style>

    <script>
        function myFunction(id) {
            window.location.assign("/event/" + id );
        }
    </script>
</head>

<body>


<jsp:include page="jsp_packets/providerHeader.jsp"/>


<div class="content-wrapper">
    <div class="container" style="width: 1200px">
        <div class="panel panel-default">
            <br><br>
            <div><h3 align="center"><strong><b>Μηνιαία Αναφορά</b></strong></h3></div>
            <hr>
            <div class="panel-body">

                <div><h3 align="center"><strong><b>Εισητήρια που αγοράστηκαν τον προηγούμενο μήνα</b></strong></h3></div>

                <table class="table table-bordered" style="background-color: ghostwhite">
                    <thead>
                    <tr>
                        <th>Εκδήλωση</th>
                        <th>Ποσότητα Εισητηρίων</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${eventsMonthly}" var="event" varStatus="status">
                        <tr>
                            <td><c:out value="${event.title}"/></td>
                            <td><c:out value="${amounts[status.index]}"/></td>
                            <td>
                                <div class="col-md-12 text-center">
                                <a class="btn btn-info btn-sm"  href="/event/${event.id}">
                                   Περισσοτερα
                                </a>
                                </div>
                        </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>


                <div><h3 align="center"><strong><b>Εκδηλώσεις που ολοκληρώθηκαν τον προηγούμενο μήνα</b></strong></h3></div>

                <table class="table table-bordered" style="background-color: ghostwhite">
                    <thead>
                    <tr>
                        <th>Εκδήλωση</th>
                        <th>Ποσότητα Πωληθέντων Εισιτηρίων</th>
                        <th>Έσοδα σε Ευρώ</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${eventsDoneMonthly}" var="event" varStatus="status">
                        <tr>
                            <td><c:out value="${event.title}"/></td>
                            <td><c:out value="${event.totalTickets-event.availableTickets}"/></td>
                            <td><c:out value="${(event.totalTickets-event.availableTickets)*event.ticketPrice}"/></td>
                            <td>
                                <div class="col-md-12 text-center">
                                    <c:if test="${event.isActive==1}">
                                    <a class="btn btn-info btn-sm"  href="/provider/updateWallet/${event.getId()}">
                                        BuyOut
                                    </a>
                                    </c:if>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>



<jsp:include page="jsp_packets/footer.jsp"/>
<jsp:include page="jsp_packets/scripts.jsp"/>

</body>
</html>