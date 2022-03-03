<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <jsp:include page="jsp_packets/head.jsp"/>
    <title>RozPanda | Επεξεργασία Προφίλ</title>

    <!-- JQuery -->
    <script type="text/javascript" src="//code.jquery.com/jquery-2.2.3.min.js"></script>
    <!-- JQuery UI -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script type="text/javascript" src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<body>
<jsp:include page="jsp_packets/parentHeader.jsp"/>

<div class="content-wrapper">
                <br><br>
                <div class="container" align="center">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h4><strong><b>Επεξεργασία Προφίλ</b></strong></h4></div>
                        <div class="panel-body" style="font-size: medium;">

                <div class="col-md-8" style="font-size: inherit">

                    <form:form method="POST" modelAttribute="userForm" enctype="multipart/form-data" id="registerForm">
                        <div class="login-input" style="margin-bottom:10px;">
                            <div class="control-group file-upload" id="file-upload1">
                                <div class="box text-center"><img src="${user.getParentById().getPhotoLink()}" alt=""></div>
                                <div class="controls"><input type="file" name="file"/></div>
                            </div>
                            <spring:bind path="username">
                                <form:input class="myInput2" type="hidden" path="username" value="${user.getUsername()}" style="margin-right: 5%"></form:input>
                            </spring:bind>
                            <spring:bind path="name">
                                <form:input class="myInput2" type="text"  placeholder="First Name" path="name" value="${user.getParentById().getName()}" style="margin-right: 5%"></form:input>
                            </spring:bind>
                            <spring:bind path="surname">
                                <form:input class="myInput2" type="text" placeholder="Surname" path="surname" id="name" value="${user.getParentById().getSurname()}"></form:input>
                            </spring:bind>
                            <spring:bind path="email">
                                <form:input class="myInput2" type="email" placeholder="Email" path="email" id="email" value="${user.getEmail()}"  style="margin-right: 5%"></form:input>
                            </spring:bind>
                            <spring:bind path="postalCode">
                                <form:input class="myInput2" type="text" placeholder="Postal Code" path="postalCode" id="postcode" value="${user.getParentById().getPostalCode()}"></form:input>
                            </spring:bind>
                            <spring:bind path="street">
                                <form:input class="myInput2" type="text" placeholder="Street" path="street" id="street" value="${user.getParentById().getStreet()}"  style="margin-right: 5%"></form:input>
                            </spring:bind>
                            <spring:bind path="streetNumber">
                                <form:input class="myInput2" type="text" placeholder="Street Number" path="streetNumber" id="strno" value="${user.getParentById().getStreetNumber()}"></form:input>
                            </spring:bind>
                            <spring:bind path="phone">
                                <form:input class="myInput2" type="tel" placeholder="Phone" path="phone" id="phonenum" value="${user.getParentById().getPhone()}" pattern="^\d{10}$" style="margin-right: 5%"></form:input>
                            </spring:bind>

                        </div>

                        <input type="submit" name="update"  class="mybtn" value="Ενημέρωση" style="margin-top: 20px; cursor: pointer;">
                    </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<script>
    $(document).ready(function () {


        $('.file-upload').hide();
        $('#file-upload1').show();

        //language=JQuery-CSS
        $('.box').click(function () {
            var imgg = $(this).children('img');
            $(this).siblings().children("input").trigger('click');

            $(this).siblings().children("input").change(function () {
                var reader = new FileReader();

                reader.onload = function (e) {
                    var urll = e.target.result;
                    $(imgg).attr('src', urll);
                    imgg.parent().css('background', 'transparent');
                    imgg.show();
                    imgg.siblings('p').hide();

                };
                reader.readAsDataURL(this.files[0]);
            });
        });
    });
</script>


<jsp:include page="jsp_packets/footer.jsp"/>
<jsp:include page="jsp_packets/scripts.jsp"/>

</body>
</html>
