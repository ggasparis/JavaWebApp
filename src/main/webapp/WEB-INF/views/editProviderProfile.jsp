<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <jsp:include page="jsp_packets/head.jsp"/>
    <title>RozPanda | Επεξεργασία Προφίλ</title>
</head>

<body>
<jsp:include page="jsp_packets/providerHeader.jsp"/>

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
                                <div class="box text-center"><img src="${user.getProviderById().getPhotoLink()}" alt=""></div>
                                <div class="controls"><input type="file" name="file"/></div>
                            </div>
                            <spring:bind path="username">
                                <form:input class="myInput2" type="hidden"  path="username" value="${user.getUsername()}" style="margin-right: 5%"></form:input>
                            </spring:bind>
                            <spring:bind path="name">
                                <form:input class="myInput2" type="text" placeholder="Name" path="name" value="${user.getProviderById().getName()}" style="margin-right: 5%"></form:input>
                            </spring:bind>
                            <spring:bind path="surname">
                                <form:input class="myInput2" type="text" placeholder="Surname" path="surname" id="name" value="${user.getProviderById().getSurname()}"></form:input>
                            </spring:bind>
                            <spring:bind path="email">
                                <form:input class="myInput2" type="email" placeholder="Email" path="email" id="email" value="${user.getEmail()}"  style="margin-right: 5%"></form:input>
                            </spring:bind>
                            <spring:bind path="postalCode">
                                <form:input class="myInput2" type="text"  placeholder="Postal Code"  path="postalCode" id="postcode" value="${user.getProviderById().getPostalCode()}"></form:input>
                            </spring:bind>
                            <spring:bind path="street">
                                <form:input class="myInput2" type="text"  placeholder="Street" path="street" id="street" value="${user.getProviderById().getStreet()}"  style="margin-right: 5%"></form:input>
                            </spring:bind>
                            <spring:bind path="streetNumber">
                                <form:input class="myInput2" type="text" placeholder="Streer Number" path="streetNumber" id="strno" value="${user.getProviderById().getStreetNumber()}"></form:input>
                            </spring:bind>
                            <spring:bind path="phone">
                                <form:input class="myInput2" type="tel" placeholder="Phone" path="phone" id="phonenum" value="${user.getProviderById().getPhone()}" pattern="^\d{10}$" style="margin-right: 5%"></form:input>
                            </spring:bind>
                            <spring:bind path="companyName">
                                <form:input class="myInput2" type="text" placeholder="Company Name" path="companyName" id="providerAd" value="${user.getProviderById().getCompanyName()}" style="margin-right: 5%"></form:input>
                            </spring:bind>
                            <spring:bind path="afm">
                                <form:input class="myInput2" type="text" placeholder="AFM" path="afm" id="providerNe" value="${user.getProviderById().getAfm()}"></form:input>
                            </spring:bind>
                            <label style="margin-top: 5%"><strong>Description</strong></label>
                            <spring:bind path="description">
                                <form:textarea rows="4" cols="50" path="description" value="${user.getProviderById().getDescription()}" placeholder="${user.getProviderById().getDescription()}" id="providerInfo"></form:textarea>
                            </spring:bind>

                        </div>

                        <input type="submit" name="update"  class="mybtn" value="Ενημέρωση" style="margin-top: 20px; cursor: pointer;">
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="jsp_packets/footer.jsp"/>
<jsp:include page="jsp_packets/scripts.jsp"/>


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

</body>
</html>
