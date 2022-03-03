<%@ page import="gr.ntua.ece.project.entities.Event" %>
<%@ page import="gr.ntua.ece.project.entities.EventPhotos" %>
<%@ page import="gr.ntua.ece.project.entities.Category" %>
<%@ page import="java.util.Random" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



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

<body>

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


<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">


<!------------- MAIN PAGE CONTENT AREA --------->
<div class="site-panel"style="margin-top:110px">
    <div class="container">
        <div class="row">
            <div class="col-md-12 ">
                <div class= "content-box well">

                    <legend>${event.getTitle()}</legend>

                    <div class="container">
                        <div class="row">
                            <div class="col-md-6">
                                <div class=" clearfix">

                                    <h4>${event.getDescription()}</h4>
                                    <hr>
                                    <c:forEach var="photo" items="${event.eventPhotosById}">
                                        <div class="col-md-6">
                                            <img src="${photo.link}" class="img-thumbnail" alt="Aliii">
                                        </div>
                                    </c:forEach>
                                    <!-- <div class="col-md-6">
                                     <img src="https://images.genius.com/5c92e25125aa81b259306caf603a5260.600x444x1.jpg" class="img-thumbnail" alt="Aliii">
                                     </div> -->
                                    <ul class="list-inline">
                                        <li><h5><strong>Κατηγορία:  </strong> </h5> </li>
                                        <li>${event.categoryByCategoryId.getName()}</li>
                                    </ul>
                                    <ul class="list-inline">
                                        <li><h5><strong>Tags: </strong></h5></li>
                                        <li>${event.getTags()}</li>
                                    </ul>
                                    <ul class="list-inline">
                                        <li><h5><strong>Τιμή Εισιτηρίου:</strong></h5></li>
                                        <fmt:formatNumber var="points" value="${event.getTicketPrice()*100}" minFractionDigits="0" maxFractionDigits="0"/>
                                        <br/>
                                        <li> <h4>${points} πόντοι</li> </h4><br/>
                                        <li> <h4>${event.getTicketPrice()} €</h4></li>
                                    </ul>
                                    <hr >


                                </div>
                            </div>
                            <div class="col-md-6">
                                <div id="pac-input" class="controls" style="color: #000000;"></div>
                                <div class="container" id="map" style="width:480px;height:320px;background:yellow;padding-left: 75px">
                                </div>
                                <br>

                                <div class="col-md-6 text-center">
                                    <div class="row">
                                        <div class="col-md-12 stats-box">
                                            <h2>${event.getAvailableTickets()}</h2><h4>διαθέσιμα εισιτήρια</h4>
                                            <a href="/book/${event.getId()}">
                                                <c:if test="${role==2}">
                                                    <button type="button" class="btn btn-success">Κρατηση Θεσεων</button>
                                                </c:if>
                                                <c:if test="${role==0}">
                                                    <button type="button" class="btn btn-success">Κρατηση Θεσεων</button>
                                                </c:if>
                                            </a>
                                        </div>

                                        <div class="col-md-12 stats-box ">
                                            <br>
                                            <c:if test="${role==2}">
                                                <a href="/report/${event.getId()}" class="btn btn-danger">Αναφορά</a>
                                            </c:if>

                                        </div>

                                        <c:if test="${role==1}">
                                            <c:set var = "active" scope = "session" value = "${event.getIsActive()}"/>

                                            <c:if test="${active==0}">
                                                <td>
                                                    <a href="/admin/changestatus/event/${event.getId()}"><button type="button" class="btn btn-success">ενεργοποίηση</button></a>
                                                </td>
                                            </c:if>
                                            <c:if test="${active==1}">
                                                <td>
                                                    <a href="/admin/changestatus/event/${event.getId()}"><button type="button" class="btn btn-success">απενεργοποίηση</button></a>
                                                </td>
                                            </c:if>

                                        </c:if>
                                    </div>
                                </div>
                                <div class="col-md-6">


                                    <div class="col-md-12 text-right">
                                        <img class="img-responsive" src="${event.providerByProviderUserId.getPhotoLink()}" alt="Chania">

                                        <h4>${event.providerByProviderUserId.getCompanyName()}</h4>
                                        <h5>${event.providerByProviderUserId.getDescription()}</h5>

                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>




                </div>
            </div>
        </div>
    </div>
</div>













<script>

    function initAutocomplete() {

        var mapCanvas = document.getElementById("map");
        var myCenter=new google.maps.LatLng(${event.getLatitude()}, ${event.getLongtitude()});

        var mapOptions = {center: myCenter, zoom: 16};
        var map = new google.maps.Map(mapCanvas, mapOptions);


        // Create the search box and link it to the UI element.
        var input = document.getElementById('pac-input');
        var searchBox = new google.maps.places.SearchBox(input);
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

        // Bias the SearchBox results towards current map's viewport.

        map.addListener('bounds_changed', function() {

            searchBox.setBounds(map.getBounds());

        });

        var markers = [];
        // Listen for the event fired when the user selects a prediction and retrieve
        // more details for that place.
        searchBox.addListener('places_changed', function() {
            var places = searchBox.getPlaces();

            if (places.length == 0) {
                return;
            }

            // Clear out the old markers.
            markers.forEach(function(marker) {
                marker.setMap(myCenter);
            });
            markers = [];

            // For each place, get the icon, name and location.
            var bounds = new google.maps.LatLngBounds();

            places.forEach(function(place) {
                if (!place.geometry) {
                    console.log("Returned place contains no geometry");
                    return;
                }
                var icon = {
                    url: place.icon,
                    size: new google.maps.Size(71, 71),
                    origin: new google.maps.Point(0, 0),
                    anchor: new google.maps.Point(17, 34),
                    scaledSize: new google.maps.Size(25, 25)
                };

                // Create a marker for each place.
                markers.push(new google.maps.Marker({
                    map: map,
                    icon: icon,
                    title: place.name,
                    position: place.geometry.location
                }));

                if (place.geometry.viewport) {
                    // Only geocodes have viewport.
                    bounds.union(place.geometry.viewport);
                } else {
                    bounds.extend(place.geometry.location);
                }
            });

            var bounds2 = new google.maps.LatLng();
            map.fitBounds(bounds);
            bounds2 = map.getCenter();
            document.getElementById("latitude").value = bounds2.lat();
            document.getElementById("longtitude").value = bounds2.lng();
        });

        var title = "${event.getTitle().replace("\'", "\\\'")}";
        var contentString = title + ' <br> ${event.getStreet()} ${event.getStreetNumber()}, ${event.getPostalCode()}';
        var infowindow = new google.maps.InfoWindow({
            content: contentString
        });

        var marker = new google.maps.Marker({
            position: myCenter,
            map: map,
            title: title
        });

        marker.addListener('click', function() {
            infowindow.open(map, marker);
        });




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






<jsp:include page="jsp_packets/footer.jsp"/>

<jsp:include page="jsp_packets/scripts.jsp"/>

</body>
</html>
