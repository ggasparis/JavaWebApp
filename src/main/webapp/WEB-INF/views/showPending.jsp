
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <html>

<head>
    <jsp:include page="jsp_packets/head.jsp"/>
    <title>RozPanda | Διαχείριση</title>
</head>

<body>

<jsp:include page="jsp_packets/adminHeader.jsp"/>

<div class="content-wrapper">
    <div class="container" style="width: 1200px">
        <div class="panel panel-default">
            <br><br>
            <div><h3 align="center"><strong><b>Μη επιβεβαιωμένοι Πάροχοι</b></strong></h3></div>
            <hr>
            <div class="panel-body">
                <table class="table table-bordered" style="background-color: ghostwhite">
                    <thead>
                    <tr>
                        <th>Όνομα Χρήστη</th>
                        <th>Email</th>

                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${userList}" var="user">
                        <tr>
                            <td><c:out value="${user.getUsername()}"/></td>
                            <td><c:out value="${user.getEmail()}"/></td>
                            <td>
                                <a class="btn btn-info btn-sm"  href="publicUserProfile?username=${user.getUsername()}">
                                    <i class="fa fa-info" style="margin-right: 5px;"></i> Περισσοτερα
                                </a>
                                <a class="btn btn-info btn-sm" href="approveProvider?username=${user.getUsername()}">
                                    <i class="fa fa-info" style="margin-right: 5px;"></i> Επιβεβαιωση
                                </a>
                            </td>
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