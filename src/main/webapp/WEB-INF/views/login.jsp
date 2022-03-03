<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Σύνδεση</title>
    <jsp:include page="jsp_packets/head.jsp"/>
</head>

<body>


<jsp:include page="jsp_packets/header.jsp"/>
<%--<%if (((Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION"))!=null){%>--%>
<%--<%=((Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION")).getLocalizedMessage() %>--%>
<%--<%}%>--%>

<script type="text/javascript">
    function alertName(){
        var myString="${message}";
        if (!(myString=="")){
            swal(myString);
            if (myString=="you are banned"){
                $.playSound('${contextPath}/resources/BanHammer.mp3');
            }
        }
    }

</script>
<div class="content-wrapper">
    <br><br>
    <div class="container" align="center">
        <div class="panel panel-default">
            <div class="panel-heading"><h4><strong><b>Είσοδος</b></strong></h4></div>
            <div class="panel-body" style="font-size: medium;">

                <div class="col-md-6" style="font-size: inherit">

                    <form method="POST">
                        <div class="login-input" style="margin-bottom:10px;">
                                <input type="text" name="username" placeholder="Όνομα Χρήστη" required>
                                <input type="password" name="password" id="reg-password" placeholder="Κωδικός (τουλάχιστον 8 χαρακτήρες)" required>
                        <br/><br/><br/>
                        <input type="submit" name="register" class=" btn btn-primary login register-submit" value="Σύνδεση" style="margin-top: 20px; cursor: pointer;">
                        </div>
                    </form>
                    <div class="login-help">
                        <a id="login-from-register" href="/register">Εγγραφή</a> - <a href="#">Ξέχασα τον Κωδικό Μου</a>
                    </div>
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
