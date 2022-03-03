<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="gr.ntua.ece.project.entities.Parent" %>
<%@ page import="gr.ntua.ece.project.entities.User" %>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<html>

<head>

    <jsp:include page="jsp_packets/head.jsp"/>
    <title>Add Points</title>
    <style rel="stylesheet">
        .viewbody {
            background: url("${contextPath}/resources/img/cloud_background.jpg") no-repeat center center;
            background-size: 100% 100%;
            height: 100vh;
        }
    </style>



</head>



<style>
    body {
        font-size: 16px;
    }
    .blue-circle-button {
        box-shadow: 2px 4px 0 2px rgba(0,0,0,0.5);
        border: .5em solid #9bc1ff;
        font-size: 1em;
        line-height: 1.1em;
        color: #ffffff;
        background-color: #5697ff;
        margin: auto;
        border-radius: 50%;
        height: 10em;
        width: 10em;
        position: relative;
    }
    .blue-circle-button:hover {
        color:#ffffff;
        background-color: #9bc1ff;
        text-decoration: none;
        border-color: #9bc1ff;

    }
    .blue-circle-button:visited {
        color:#ffffff;
        background-color: #9bc1ff;
        text-decoration: none;

    }
    .blue-circle-link-greater-than {
        font-size: 1em;
    }




</style>
<body>

<jsp:include page="jsp_packets/parentHeader.jsp"/>

<div class="viewbody hm-black-light">

<div class="content-wrapper">
    <div class="container" align="center">
         <div class="panel panel-default">
            <div class="panel-heading"><h4><strong><b>Add Points</b></strong></h4></div>
                 <br><br>



                 <div class="btn-group">
                     <div class="col-md-12">

                         <button class="btn btn-default blue-circle-button" href="" onclick="myFunction(50)" >50 points<span class="blue-circle-greater-than"></span></button>        <br/><br/><br/>
                         <button class="btn btn-default blue-circle-button" href="" onclick="myFunction(1000)" >1000 points<span class="blue-circle-greater-than"></span></button>      <br/><br/><br/>
                         <button class="btn btn-default blue-circle-button" href="" onclick="myFunction(2000)" >2000 points<span class="blue-circle-greater-than"></span></button>      <br/><br/><br/>
                         <button class="btn btn-default blue-circle-button" href="" onclick="myFunction(5000)" >5000 points<span class="blue-circle-greater-than"></span></button>      <br/><br/><br/>
                     </div>
                 </div>




         </div>
    </div>
</div>

</div>

<jsp:include page="jsp_packets/footer.jsp"/>
<jsp:include page="jsp_packets/scripts.jsp"/>

<script>
    function myFunction(x) {
        alert("Οι πόντοι  προστέθηκαν στο πορτοφόλι σας.");
        window.location.replace("https://localhost:8443//successBuy?option="+x);
    }
</script>

</body>
</html>
