<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <jsp:include page="jsp_packets/head.jsp"/>
    <title>RozPanda | Προφίλ</title>
</head>

<body>

<c:set var = "userId" scope = "session" value = "${user.getRoleId()}"/>
<c:if test="${userId==1}">
    <jsp:include page="jsp_packets/adminHeader.jsp"/>
</c:if>
<c:if test="${userId==2}">
    <jsp:include page="jsp_packets/parentHeader.jsp"/>
</c:if>
<c:if test="${userId==3}">
    <jsp:include page="jsp_packets/providerHeader.jsp"/>
</c:if>


<div class="content-wrapper">
    <br><br>
    <div class="container" align="center">
        <div class="panel panel-default">
            <div class="panel-heading"><h4><strong><b>Προφίλ Χρήστη</b></strong></h4></div>
            <div class="panel-body" style="font-size: medium;">

                <div class="col-md-6" style="font-size: inherit">
                    <div align="center"><img alt="User Photo" src="${viewuser.getProviderById().getPhotoLink()}" id="profile-image1">
                        <br><br>
                        <h4><strong>${viewuser.getProviderById().getName()} ${viewuser.getProviderById().getSurname()}</strong></h4>

                        <strong>Πάροχος</strong>
                        <br>

                    </div>
                </div>

                <hr>

                <div class="profile-table">
                    <table class="table styletd table-bordered" style="font-size: inherit">
                        <tr>
                            <td><strong> Όνομα Χρήστη: </strong></td>
                            <td>${viewuser.getUsername()}
                            </td>
                        </tr>
                        <tr>
                            <td><strong> Email: </strong></td>
                            <td>${viewuser.getEmail()}
                            </td>
                        </tr>
                        <tr>
                            <td><strong> Τηλέφωνο: </strong></td>
                            <td>${viewuser.getProviderById().getPhone()}
                            </td>
                        </tr>

                        <tr>
                            <td><strong> Οδός: </strong></td>
                            <td>${viewuser.getProviderById().getStreet()} ${viewuser.getProviderById().getStreetNumber()}
                            </td>
                        </tr>
                        <tr>
                            <td><strong> ΤΚ: </strong></td>
                            <td>${viewuser.getProviderById().getPostalCode()}
                            </td>
                        </tr>
                        <tr>
                            <td><strong>Όνομα Επιχείρησης: </strong></td>
                            <td>${viewuser.getProviderById().getCompanyName()}
                            </td>
                        </tr>
                        <tr>
                            <td><strong>ΑΦΜ: </strong></td>
                            <td>${viewuser.getProviderById().getAfm()}
                            </td>
                        </tr>
                        <tr>
                            <td><strong>Περιγραφή: </strong></td>
                            <td>${viewuser.getProviderById().getDescription()}
                            </td>
                        </tr>
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
