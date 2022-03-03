<%@ page import="gr.ntua.ece.project.entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <jsp:include page="jsp_packets/head.jsp"/>
    <title>RozPanda | Profile</title>
</head>

<body>

<jsp:include page="jsp_packets/providerHeader.jsp"/>

<div class="content-wrapper">
    <br><br>
    <div class="container" align="center">
        <div class="panel panel-default">
            <div class="panel-heading"><h4><strong><b>Προφίλ</b></strong></h4></div>
            <div class="panel-body" style="font-size: medium;">

                <div class="col-md-6" style="font-size: inherit">
                    <div align="center"><img alt="User Photo" src="${user.getProviderById().getPhotoLink()}" id="profile-image1">
                        <br><br>
                        <h4><strong>${user.getProviderById().getName()} ${user.getProviderById().getSurname()}</strong></h4>

                        <strong>Πάροχος Υπηρεσιών</strong>
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
                            <td><strong>Πορτοφόλι: </strong></td>
                            <td>${user.getProviderById().getWalletPoints()} Πόντοι
                            </td>
                        </tr>
                        <tr>
                            <td><strong> Τηλέφωνο: </strong></td>
                            <td>${user.getProviderById().getPhone()}
                            </td>
                        </tr>

                        <tr>
                            <td><strong> Οδός: </strong></td>
                            <td>${user.getProviderById().getStreet()} ${user.getProviderById().getStreetNumber()}
                            </td>
                        </tr>
                        <tr>
                            <td><strong> ΤΚ: </strong></td>
                            <td>${user.getProviderById().getPostalCode()}
                            </td>
                        </tr>
                        <tr>
                            <td><strong>Ονομα Επιχείρησης: </strong></td>
                            <td>${user.getProviderById().getCompanyName()}
                            </td>
                        </tr>
                        <tr>
                            <td><strong>ΑΦΜ: </strong></td>
                            <td>${user.getProviderById().getAfm()}
                            </td>
                        </tr>
                        <tr>
                            <td><strong>Περιγραφή: </strong></td>
                            <td>${user.getProviderById().getDescription()}
                            </td>
                        </tr>
                        <tr>
                            <td><strong>Λήξη Συνδρομής: </strong></td>
                            <td>${user.getProviderById().getSubscriptionExpiryDate()}
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <!--Update User-->

            <a href="editProviderProfile" class="btn btn-default">Επεξεργασια</a>
            <a href="/provider/subcscribe" class="btn btn-primary">Ανανεωση Συνδρομης</a>
            <a href="#" class="btn btn-default">Εισπραξη Εσοδων</a>
        </div>
    </div>
</div>


<jsp:include page="jsp_packets/footer.jsp"/>
<jsp:include page="jsp_packets/scripts.jsp"/>

</body>
</html>
