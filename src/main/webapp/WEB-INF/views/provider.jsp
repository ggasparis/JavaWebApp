<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="gr.ntua.ece.project.entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="jsp_packets/head.jsp"/>
    <title>RozPanda | Provider</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

    <style>
        label{
            font-size: inherit;
            width: 100%;
        }
        .table tr {
            cursor: pointer;
        }
    </style>

    <script>
        function myFunction(id) {
            window.location.assign("/event/" + id );
        }
    </script>
</head>

<body>



<jsp:include page="jsp_packets/providerHeader.jsp"/>

<c:choose>
    <c:when test="${user.providerById.isApproved==1}">

<div class="content-wrapper">
    <br><br>
    <div class="container" style="width: 1200px">
        <div class="panel panel-default">
            <div class="panel-heading withmargin" id="myEventsHead" style="cursor: pointer;">
                <h4>
                    <strong><b>Οι Εκδηλώσεις Μου</b></strong>
                    <i class="fa fa-caret-down fa-fw fa-lg" aria-hidden="true" style="color: white;"></i>
                </h4>
            </div>
            <div class="panel-body" id="myEventsBody" style="display: none;">
                <table class="table table-bordered" style="background-color: ghostwhite">
                    <thead>
                    <tr>
                        <%--<th>Photo</th>--%>
                        <th>Εκδήλωση</th>
                        <th>Οδός</th>
                        <th>Τιμή Εισιτηρίου</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${events}" var="ev">
                    <tr onclick="myFunction(${ev.getId()})">
                        <%--<td><a href="<%=ev.getPictureUrl()%>"><img src="<%=ev.getThumbnailUrl()%>" /></a></td>--%>
                        <td><c:out value="${ev.getTitle()}"/></td>
                        <td><c:out value="${ev.getStreet()}"/></td>
                        <td><c:out value="${ev.getTicketPrice()}"/></td>

                    </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </div>


    <div class="container" style="width: 1200px">
        <div class="panel panel-default">
            <div class="panel-heading"><h4><strong><b>Νέα Εκδήλωση</b></strong></h4></div>

            <jsp:useBean id="now" class="java.util.Date"/>
            <jsp:useBean id="dateValue" class="java.util.Date" />


            <%

                User user=(User)request.getAttribute("user");
                Date Expiry = user.getProviderById().getSubscriptionExpiryDate();
                if(Expiry.after(new java.util.Date())){%>







            <div class="panel-body">

            <form:form method="post" modelAttribute="eventForm" enctype="multipart/form-data" id="importApForm">
                    <div id="wrapper">
                        <div id="first">
                            <label class="row col-md-5 col-center-block"><b>Εισαγωγή Εικόνας</b></label>
                            <div class="fupload" id="fupload1">
                                <div class="imbox"><img src="" alt=""></div>
                                <div class="cntrls">
                                    <input type="file" name="file"/>
                                </div>
                            </div>

                            <br><br><br><br><br>

                            <label class="row col-md-5 col-center-block"><b>Προσδιορίστε Θέση στο Χάρτη</b></label>
                            <input id="pac-input" class="controls" type="text" placeholder="Αναζήτηση" style="color: #000000;">
                            <div class="container" id="map" style="width:380px;height:380px;background:yellow;padding-left: 75px">
                            </div>
                            <spring:bind path="latitude">
                                <form:input type="hidden" id="latitude" path="latitude" value=""></form:input>
                            </spring:bind>
                            <spring:bind path="longtitude">
                                <form:input type="hidden" id="longtitude" path="longtitude" value=""></form:input>
                            </spring:bind>
                        </div>

                        <div id="second">
                            <label><b>Τίτλος</b>
                                <spring:bind path="title">
                                    <form:input type="text" value="" path="title"></form:input>
                                </spring:bind>
                            </label>

                            <label><b>Περιγραφή</b>
                                <spring:bind path="description">
                                    <form:textarea class="hostinput" rows="4" cols="50" value="" path="description"></form:textarea>
                                </spring:bind>
                            </label>

                            <label><b>Ηλικία από</b>
                                <spring:bind path="ageFrom">
                                    <form:input type="number" class="hostinput" value="1" path="ageFrom" min="1" max="18"></form:input>
                                </spring:bind>
                            </label>

                            <label><b>Ηλικία Μέχρι</b>
                                <spring:bind path="ageTo">
                                    <form:input type="number" class="hostinput" value="18" path="ageTo" min="1" max="18"></form:input>
                                </spring:bind>
                            </label>
                            <label for="from"><b>Ημερομηνία</b>
                                <spring:bind path="date">
                                    <form:input type="text" id="from" value="" path="date"></form:input>
                                </spring:bind>
                            </label>

                            <label><b>Ώρα</b>
                                <spring:bind path="time">
                                    <form:input type="time" value="" path="time"></form:input>
                                </spring:bind>

                            </label>

                            <label><b>Οδός</b>
                                <spring:bind path="street">
                                    <form:input type="text" value="" path="street"></form:input>
                                </spring:bind>
                            </label>

                            <label><b>Αριθμός</b>
                                <spring:bind path="streetNumber">
                                <form:input class="hostInput" type="text" value="" path="streetNumber"></form:input>
                                </spring:bind>
                            </label>

                            <label><b>Ταχυδρομικός Κώδικας</b>
                                <spring:bind path="postalCode">
                                    <form:input class="hostInput" type="text" value="" path="postalCode"></form:input>
                                </spring:bind>
                            </label>

                            <label><b>Κατηγορία</b></label>
                            <spring:bind path="category">
                            <form:radiobutton path="category" value="Αθλητισμός"/>Αθλητισμός
                            <form:radiobutton path="category" value="Μουσική"/>Μουσική
                                <form:radiobutton path="category" value="Μαθήματα"/>Μαθήματα
                                <form:radiobutton path="category" value="Θέατρο"/>Θέατρο
                                <form:radiobutton path="category" value="Σινεμά"/>Σινεμά
                                <form:radiobutton path="category" value="Τέχνη"/>Τέχνη
                            </spring:bind>

                            <label><b><br>Διαθέσιμα Εισιτήρια</b>
                                <spring:bind path="availableTickets">
                                <form:input type="number" class="hostinput" value="" path="availableTickets" min="1"></form:input>
                                </spring:bind>
                            </label>

                            <label><b>Τιμή Εισιτηρίου</b>
                                <spring:bind path="ticketPrice">
                                <form:input type="number" class="hostinput" value="" path="ticketPrice" min="0"></form:input>
                                </spring:bind>
                            </label>

                            <!--<h5 style="color: red"><b>All fields are requiredor else bad things happen</b>-->
                                <br>
                                <div style="color: red">
                                    <h3><form:errors path="title" /></h3><br>
                                    <h3><form:errors path="description"/></h3><br>
                                    <h3><form:errors path="street"/></h3><br>
                                    <h3><form:errors path="latitude"/></h3><br>
                                </div>



                            </h5>
                        </div>
                    </div>


                    <div class="row col-md-5 col-center-block" style="margin-top: 40px;">
                        <input type="submit" name="importAp" class="mybtn" value="SUBMIT">
                    </div>



            </form:form>

            </div>
        </div>
    </div>
</div>

<%}
else{%>
<br/><br/><br/><br/><br/><br/><br/>
<h1 class="lead text-center"><strong class="text-danger">Παρακαλώ ανανεώστε τη συνδρομή σας για να δημιουργήσετε νέες εκδηλώσεις</strong></h1>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<%}%>


    </c:when>
    <c:otherwise>
        <br/><br/><br/><br/><br/><br/><br/>
        <h1 class="lead text-center"><strong class="text-danger">Παρακαλώ αναμένετε επιβεβαίωση του λογαριασμού σας απο τον διαχειριστή της σελίδας</strong></h1>
        <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
    </c:otherwise>
</c:choose>

<jsp:include page="jsp_packets/footer.jsp"/>
<jsp:include page="jsp_packets/scripts.jsp"/>


<!-- Start of map search box -->

<script>

    function initAutocomplete() {

        //alert( "Latitude: "+ ${myLat} +" "+", longitude: "+ ${myLon} );
        var mapCanvas = document.getElementById("map");
        var myCenter=new google.maps.LatLng(37.975446, 23.734774);

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
                marker.setMap(null);
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

        var contentString = 'Hello';
        var infowindow = new google.maps.InfoWindow({
            content: contentString
        });

        google.maps.event.addListener(map, 'click', function( event ){
            //alert( "Latitude: "+event.latLng.lat()+" "+", longitude: "+event.latLng.lng() );
            //myCenter=google.maps.LatLng(event.latLng.lat(), event.latLng.lng());
            placeMarker(event.latLng);
            //alert( "Latitude: "+myCenter.lat()+" "+", longitude: "+event.latLng.lng() );
        });



        var oldMarker = new google.maps.Marker({
            position: myCenter,
            map: map,
            title: 'My Position',
            animation: google.maps.Animation.DROP
        });
        marker.setMap(map);


        marker.addListener('click', function() {
            infowindow.open(map, marker);
        });


        function placeMarker(location) {
            marker = new google.maps.Marker({
                animation: google.maps.Animation.DROP,
                position: location,
                map: map


            });
            myCenter=location;
            if (oldMarker != undefined){
                oldMarker.setMap(null);
            }
            oldMarker = marker;
            //map.setCenter(location);
            document.getElementById("latitude").value = myCenter.lat();
            document.getElementById("longtitude").value =  myCenter.lng();
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

<script src="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.3/jquery.timepicker.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $( function() {
            from = $( "#from" )
                .datepicker({
                    dateFormat: 'yy-mm-dd',
                    defaultDate: "+1w",
                    changeMonth: true,
                    numberOfMonths: 3
                })
                .on( "change", function() {
                    from.datepicker( "option", "maxDate", getDate( this ) );
                });

        function getDate( element ) {
            var date;
            try {
                date = $.datepicker.parseDate(element.value);
            } catch( error ) {
                date = null;
            }

            return date;
        }

        $('#myEventsHead').click(function() {
            if ($('#myEventsBody').is(":visible")) {
                $('#myEventsBody').slideUp();
                $('#myEventsHead > h4 > i').removeClass('fa-caret-up');
                $('#myEventsHead > h4 > i').addClass('fa-caret-down');
            } else {
                $('#myEventsBody').slideDown();
                $('#myEventsHead > h4 > i').removeClass('fa-caret-down');
                $('#myEventsHead > h4 > i').addClass('fa-caret-up');
            }

        });
    } );
</script>

</body>
</html>