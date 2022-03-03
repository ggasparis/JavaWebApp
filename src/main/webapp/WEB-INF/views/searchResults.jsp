<%@ page import="gr.ntua.ece.project.entities.Event" %>
<%@ page import="gr.ntua.ece.project.entities.EventPhotos" %>
<%@ page import="gr.ntua.ece.project.entities.Category" %>
<%@ page import="java.util.Random" %>
<%@ page import="gr.ntua.ece.project.entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <title>Home</title>

    <jsp:include page="jsp_packets/head.jsp"/>

    <style rel="stylesheet">
        .viewbody {
            background: url("${contextPath}/resources/img/cloud_background.jpg") no-repeat center center;
            background-size: 100% 100%;
            height: 100vh;
        }
    </style>


    <!-- JQuery -->
    <script type="text/javascript" src="//code.jquery.com/jquery-2.2.3.min.js"></script>
    <!-- JQuery UI -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script type="text/javascript" src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<body onload="initialize()">



<%int Anon=(int)request.getAttribute("role"); %>
<%if (Anon==0) {%>
<jsp:include page="jsp_packets/header.jsp"/>
<%}else if (Anon==1) {%>
<jsp:include page="jsp_packets/adminHeader.jsp"/>
<%}else if (Anon==2) {%>
<jsp:include page="jsp_packets/parentHeader.jsp"/>
<%}else if (Anon==3) {%>
<jsp:include page="jsp_packets/providerHeader.jsp"/>
<%}%>

<div class="viewbody hm-black-light">
        <div class="mask pattern-6">


<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">


<hgroup class="mb20">
    <h1 align="left" class="h1-responsive wow fadeInUp title text-center" style="color: #ffffff;"><strong>Αποτελέσματα Αναζήτησης</strong></h1>
    <h2 class="lead text-center"><strong class="text-danger">${events.size()}</strong> αποτελέσματα βρεθηκαν για <strong class="text-danger">${SearchForm.getDescription()}</strong></h2>
    <br/><br/><br/><br/>
</hgroup>

            <div class="panel panel-default" style="width: 70%; margin-left:15%">
                <div class="panel-body">


<div id="pac-input" class="controls" style="color: #000000;"></div>
<div class="container" id="map" style="width:480px;height:320px;background:yellow;padding-left: 75px">
</div>

<br/><br/>




<c:forEach items="${events}" var="event">



<div class="container">



    <section class="col-xs-12 col-sm-6 col-md-12">
        <article class="search-result row">
            <div class="col-xs-12 col-sm-12 col-md-3">
                <c:if test="${event.eventPhotosById.size()!=0}">
                <a href="/event/${event.getId()}" title="Lorem ipsum" class="thumbnail"><img src="${event.getEventPhotosById().iterator().next().getLink()}" alt="Lorem ipsum" /></a>
                </c:if>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-2">
                <ul class="meta-search">

                    <li><i class="glyphicon glyphicon-calendar"></i> <span><c:out value="${event.getDateTime().toString().split(\" \")[0]}"/></span></li>
                    <li><i class="glyphicon glyphicon-time"></i> <span><c:out value="${event.getDateTime().toString().split(\" \")[1].substring(0, event.getDateTime().toString().split(\" \")[1].length() - 5)}"/></span></li>
                    <li><i class="glyphicon glyphicon-tags"></i> <span><c:out value="${event.getCategoryByCategoryId().getName()}"/></span></li>
                </ul>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-7">
                <h3><a href="/event/${event.getId()}" title=""><td><c:out value="${event.getTitle()}"/></td></a></h3>
                <p></p>
                <span class="plus"><c:out value="${event.getDescription()}"/><a href="#" title="Lorem ipsum"><i class="glyphicon glyphicon-plus"></i></a></span>
            </div>
            <span class="clearfix border"></span>
        </article>

    </section>
</div>


</c:forEach>

    </div>
            </div>
        </div>
</div>

<script type="text/javascript">

    var locations = [
    <c:forEach items="${events}" var="event">
        [${event.id}, ${event.latitude}, ${event.longtitude}, "${event.title}", "${event.street}", "${event.streetNumber}", "${event.postalCode}"],
    </c:forEach>

     [0,0,0,"","","",""]
         ];
    function initialize() {

        var myOptions = {
            center: new google.maps.LatLng(37.975446, 23.734774),
            zoom: 8,
            mapTypeId: google.maps.MapTypeId.ROADMAP

        };
        var map = new google.maps.Map(document.getElementById("map"),
            myOptions);

        setMarkers(map,locations)

    }



    function setMarkers(map,locations){
        var marker, i

        for (i = 0; i < locations.length-1; i++)
        {

            var id = locations[i][0]
            var lat = locations[i][1]
            var lon = locations[i][2]
            var title = locations[i][3]
            var street = locations[i][4]
            var num = locations[i][5]
            var postal = locations[i][6]

            latlngset = new google.maps.LatLng(lat, lon);

            var marker = new google.maps.Marker({
                map: map, title: title , position: latlngset
            });


            map.setCenter(marker.getPosition())


            var content = "<a href=/event/"+id+">"+title+"</a><br/>"+ street +" "+num+", " + postal;

            var infowindow = new google.maps.InfoWindow()

            google.maps.event.addListener(marker,'click', (function(marker,content,infowindow){
                return function() {
                    infowindow.setContent(content);
                    infowindow.open(map,marker);
                };
            })(marker,content,infowindow));

        }
    }



</script>













<script>

    function initAutocomplete() {

        var mapCanvas = document.getElementById("map");
        var myCenter=new google.maps.LatLng("37.975446", "23.734774");

        var mapOptions = {center: myCenter, zoom: 11};
        var map = new google.maps.Map(mapCanvas, mapOptions);












        function placeMarker(location, Title, Street, Num, Postal) {
            marker = new google.maps.Marker({
                position: location,
                map: map,
                title: title
            });

            var contentString =  Title + ' <br> ' + Street + ' ' + Num + ", " + Postal;
            var infowindow = new google.maps.InfoWindow({
                content: contentString
            });


            marker.addListener('click', function() {
                infowindow.open(map, marker);
            });
        }


    }

</script>

<!-- End of map search box -->


<script>

    $('.fupload').hide();
    $('#fupload1').show();

    $('.imbox').click(function() {
        var imgg = $(this).children('img');
        $(this).siblings().children("input").trigger('click');

        $(this).siblings().children("input").change(function() {
            var reader = new FileReader();

            reader.onload = function (e) {
                var urll = e.target.result;
                $(imgg).attr('src', urll);
                imgg.parent().css('background','transparent');
                imgg.show();
                imgg.siblings('p').hide();

            };
            reader.readAsDataURL(this.files[0]);
        });
    });

</script>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCQtXy0F2y3PihS1SSU6dfCgGyGIySYIKw&libraries=places&callback=initAutocomplete"></script>






<%--<jsp:include page="jsp_packets/footer.jsp"/>--%>

<jsp:include page="jsp_packets/scripts.jsp"/>

</body>
</html>
