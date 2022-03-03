<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
    <meta charset="utf-8">
    <title>Book Event</title>
    <jsp:include page="jsp_packets/head.jsp"/>
</head>

<body>

<jsp:include page="jsp_packets/parentHeader.jsp"/>

<script type="text/javascript">
    function alertName(){
        var myString="${message}";
        if (!(myString=="")){
            swal(myString);
        }
    }
</script>

<div class="content-wrapper">
    <br><br>
    <div class="container" align="center">
        <div class="panel panel-default">
            <div class="panel-heading"><h4><strong><b>Αγορά Εισιτηρίων για το Event: "${event.getTitle()}"</b></strong></h4></div>
            <div class="panel-body" style="font-size: medium;">

                <div class="col-md-6" style="font-size: inherit">

                    <form method="POST" action="/book">
                        <div class="login-input" style="margin-bottom:10px;">
                                <div class="col-md-6">
                                    <img src="${event.getEventPhotosById().iterator().next().getLink()}" class="img-thumbnail" alt="Aliii">
                                </div>
                            <h4>Τιμή εισιτηρίου: ${event.getTicketPrice()}</h4>
                            <h4>Διαθέσιμα εισιτήρια: ${event.getAvailableTickets()}</h4>
                            <input type="hidden" name="eventId" value="${event.getId()}" >
                            <input type="number" name="numberOfTickets" placeholder="Αριθμός Εισιτηρίων" min="1" max="${event.getAvailableTickets()}" required>

                            <input type="submit" name="register" class="btn btn-success" value="Αγορά" onclick="this.value='Παρακαλώ περιμένετε...';this.disabled='disabled'; this.form.submit();" style="margin-top: 20px; cursor: pointer;">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="jsp_packets/footer.jsp"/>
<jsp:include page="jsp_packets/scripts.jsp"/>

<script type="text/javascript"> window.onload = alertName; </script>


</body>



</html>
