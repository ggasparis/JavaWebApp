
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <html>

<head>
    <jsp:include page="jsp_packets/head.jsp"/>
    <title>RozPanda | Χρήστες</title>
</head>

<body>

<jsp:include page="jsp_packets/adminHeader.jsp"/>

<div class="content-wrapper">
    <div class="container" style="width: 1200px">
        <div class="panel panel-default">
            <br><br>
            <div><h3 align="center"><strong><b>Λίστα Χρηστών</b></strong></h3></div>
            <hr>
            <div class="panel-body">
                <table class="table table-bordered" style="background-color: ghostwhite">
                    <thead>
                    <tr>
                        <th>Όνομα Χρήστη</th>
                        <th>Email</th>
                        <th>Μπλοκαρισμένος</th>

                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${userList}" var="user">
                        <tr>
                            <td><c:out value="${user.getUsername()}"/></td>
                            <td><c:out value="${user.getEmail()}"/></td>
                            <td><c:out value="${user.getIsBanned()}"/></td>
                            <td>
                                <a class="btn btn-info btn-sm" href="publicUserProfile?username=${user.getUsername()}">
                                    <i class="fa fa-info" style="margin-right: 5px;"></i> περισσοτερα
                                </a>
                            </td>
                            <c:set var = "banned" scope = "session" value = "${user.getIsBanned()}"/>

                            <c:if test="${banned==0}">
                            <td>
                                <a class="btn btn-danger btn-sm" href="banUser?username=${user.getUsername()}">
                                    <i class="fa fa-info" style="margin-right: 5px;"></i> μπλοκαρισμα
                                </a>
                            </td>
                            </c:if>
                            <c:if test="${banned==1}">
                            <td>
                                <a class="btn btn-success btn-sm" href="unbanUser?username=${user.getUsername()}">
                                    <i class="fa fa-info" style="margin-right: 5px;"></i> ξεμπλοκαρισμα
                                </a>
                            </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<jsp:include page="jsp_packets/footer.jsp"/>
<jsp:include page="jsp_packets/scripts.jsp"/>

</body>
</html>