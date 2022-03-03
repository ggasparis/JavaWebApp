<%@ page import="gr.ntua.ece.project.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <jsp:include page="jsp_packets/head.jsp"/>
    <title>RozPanda | Διαχείριση</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>

<body>

<jsp:include page="jsp_packets/adminHeader.jsp"/>

<div class="content-wrapper">

    <div class="admin-container">
        <h1 style="color: #49071c;"><b>Σελίδα Διαχείρισης</b></h1><br>

        <hr class="style">
        <br>


        <p style="color: #49071c"><strong>Προβολή Χρηστών:</strong></p>


        <form method="GET" action="showUsers">
            <ul class="list-group">
                <li class="list-group-item">
                    Γονείς
                    <div class="material-switch pull-right">
                        <input id="parent" name="role" value="parent" type="checkbox" checked/>
                        <label for="parent" class="label-default"></label>
                    </div>
                </li>
                <li class="list-group-item">
                    Πάροχοι
                    <div class="material-switch pull-right">
                        <input id="provider" name="role" value="provider" type="checkbox" checked/>
                        <label for="provider" class="label-default"></label>
                    </div>
                </li>
            </ul>
            <input type="submit" name="submit" class="btn btn-primary login register-submit" value="Προβολή">
        </form>

        <br/><br/><br/>
        <a href="showPending" ><button type="button" style="margin-bottom: 20%;margin-left: 20%" class="btn btn-success">Μη επιβεβαιωμενοι Παροχοι</button></a>
        <a href="/admin/showReports" ><button type="button" style="margin-bottom: 20%;margin-left: 20%" class="btn btn-success">Προβολη Αναφορων</button></a>
    </div>

</div>
<%--<!--Mask-->--%>
<%--<div class="content-wrapper">--%>

    <%--<div class="admin-container">--%>
        <%--<h1 style="color: #49071c;"><b>Administration Page</b></h1><br>--%>

        <%--<hr class="style">--%>
        <%--<br>--%>




    <%--</div>--%>

<%--</div>--%>


<jsp:include page="jsp_packets/footer.jsp"/>
<jsp:include page="jsp_packets/scripts.jsp"/>

</body>

</html>
