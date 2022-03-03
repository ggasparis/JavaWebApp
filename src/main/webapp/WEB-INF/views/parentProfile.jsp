<%@ page import="gr.ntua.ece.project.entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <jsp:include page="jsp_packets/head.jsp"/>
    <title>RozPanda | Προφίλ</title>
</head>

<body>

<jsp:include page="jsp_packets/parentHeader.jsp"/>

<div class="content-wrapper">
    <br><br>
    <div class="container" align="center">
        <div class="panel panel-default">
            <div class="panel-heading"><h4><strong><b>Προφίλ</b></strong></h4></div>
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

                <div class="profile-table">
                    <table class="table styletd table-bordered" style="font-size: inherit">
                        <tr>
                            <td><strong> Όνομα Χρήστη: </strong></td>
                            <td>${user.getUsername()}
                            </td>
                        </tr>
                        <tr>
                            <td><strong> Email: </strong></td>
                            <td>${user.getEmail()}
                            </td>
                        </tr>
                        <tr>
                            <td><strong> Τηλέφωνο: </strong></td>
                            <td>${user.getParentById().getPhone()}
                            </td>
                        </tr>

                        <tr>
                            <td><strong> Διεύθυνση: </strong></td>
                            <td>${user.getParentById().getStreet()} ${user.getParentById().getStreetNumber()}
                            </td>
                        </tr>
                        <tr>
                            <td><strong> ΤΚ: </strong></td>
                            <td>${user.getParentById().getPostalCode()}
                            </td>
                        </tr>
                        <tr>
                            <td><strong>Πορτοφόλι: </strong></td>
                            <td>${user.getParentById().getWalletPoints()} πόντοι
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <!--Update User-->
            <a href="editParentProfile" class="btn btn-default">Επεξεργασια</a>
        </div>
    </div>
</div>


<jsp:include page="jsp_packets/footer.jsp"/>
<jsp:include page="jsp_packets/scripts.jsp"/>

</body>
</html>
