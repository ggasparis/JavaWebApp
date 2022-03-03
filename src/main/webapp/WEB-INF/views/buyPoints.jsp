<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="gr.ntua.ece.project.entities.User" %>
<%@ page import="gr.ntua.ece.project.entities.Parent" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<html>

<head>
    <jsp:include page="jsp_packets/head.jsp"/>
    <title>RozPanda | Buy Points</title>
</head>

<body>

<jsp:include page="jsp_packets/parentHeader.jsp"/>

<div class="content-wrapper">
    <br><br>
    <div class="container" align="center">
        <div class="panel panel-default">
            <div class="panel-heading"><h4><strong><b>Αγορά Πόντων</b></strong></h4></div>
            <div class="panel-body" style="font-size: medium;">

                <div class="col-md-6" style="font-size: inherit">
                    <div align="center"><img alt="User Photo" src="${user.getParentById().getPhotoLink()}" id="profile-image1">
                        <br><br>
                        <h4><strong>${user.getParentById().getName()} ${user.getParentById().getSurname()}</strong></h4>

                        <strong>Γονέας</strong>
                        <br>

                    </div>
                </div>

                <hr>

                <div class = "table">
                    <table class="table " style="font-size: inherit">
                        <thead>
                        <tr>
                            <td><strong> Διαθέσιμα Πακέτα </strong></td>
                            <th>500 πόντοι</th>
                            <th>1000 πόντοι</th>
                            <th>2000 πόντοι</th>
                            <th>5000 πόντοι</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th><strong> Κόστος: </strong></th>
                            <th>5€</th>
                            <th>10€</th>
                            <th>20€</th>
                            <th>50€</th>
                        </tr>
                        <tr>

                            <th><a class="btn btn-success" name="btn2" href="addCard"></i> Αγορα </a></th>

                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="jsp_packets/footer.jsp"/>
<jsp:include page="jsp_packets/scripts.jsp"/>

</body>
</html>

