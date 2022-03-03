
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <html>

<head>
    <jsp:include page="jsp_packets/head.jsp"/>
    <title>RozPanda | Αναφορές</title>
</head>

<body>

<jsp:include page="jsp_packets/adminHeader.jsp"/>

<div class="content-wrapper">
    <div class="container" style="width: 1200px">
        <div class="panel panel-default">
            <br><br>
            <div><h3 align="center"><strong><b>Αναφορές</b></strong></h3></div>
            <hr>
            <div class="panel-body">
                <table class="table table-bordered" style="background-color: ghostwhite">
                    <thead>
                    <tr>
                        <th>Όνομα Χρήστη</th>
                        <th>Εκδήλωση</th>


                    </tr>
                    </thead>
                    <tbody>



                    <c:forEach items="${reportList}" var="report">
                        <tr>

                            <td><a href="/publicUserProfile?username=<c:out value="${report.parentByParentUserId.userByUserId.username}"/>"><c:out value="${report.parentByParentUserId.userByUserId.username}"/></a></td>
                            <td><a href="/event/<c:out value="${report.eventByEventId.id}"/>"><c:out value="${report.eventByEventId.title}"/></a></td>
                            <td><c:out value="${report.getComments()}}"/></td>

                            <td>
                                <a class="btn btn-info btn-sm"  href="/admin/deleteReport/<c:out value="${report.id}"/>">
                                    <i class="fa fa-info" style="margin-right: 5px;"></i> Ολοκληρωθηκε
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