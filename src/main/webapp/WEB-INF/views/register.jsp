 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Εγγραφή</title>
    <jsp:include page="jsp_packets/head.jsp"/>
</head>

<body>

<jsp:include page="jsp_packets/header.jsp"/>


<div class="content-wrapper">
    <br><br>
    <div class="container" align="center">
        <div class="panel panel-default">
            <div class="panel-heading"><h4><strong><b>Εγγραφή</b></strong></h4></div>
            <div class="panel-body" style="font-size: medium;">

                <div class="col-md-8" style="font-size: inherit">

                    <form:form method="POST" modelAttribute="userForm" enctype="multipart/form-data" id="registerForm">
                        <div class="login-input" style="margin-bottom:10px;">
                            <div class="control-group file-upload" id="file-upload1">
                                <div class="image-box text-center"><img src="" alt=""></div>
                                <div class="controls">
                                    <spring:bind path="file">
                                        <form:input type="file" name="file" path="file"/></div>
                                    </spring:bind>
                                <label class="row col-md-5 col-center-block"><b>Ανέβασμα Εικόνας Προφίλ</b></label>

                            </div>

                            <spring:bind path="username">
                                <form:input class="myInput" type="text" path="username" placeholder="Όνομα Χρήστη" style="margin-right: 5%"></form:input>
                            </spring:bind>
                            <spring:bind path="email">
                                <form:input class="myInput" type="email" path="email" id="email" placeholder="Email"></form:input>
                            </spring:bind>
                            <spring:bind path="password">
                                <form:input class="myInput" type="password" path="password" id="reg-password" placeholder="Κωδικός (τουλάχιστον 8 χαρακτήρες)"  style="margin-right: 5%"></form:input>
                            </spring:bind>
                            <spring:bind path="password_confirm">
                            <form:input class="myInput" type="password" path="password_confirm" id="conPassword" placeholder="Επιβεβαίωση Κωδικού"></form:input>
                            </spring:bind>
                            <spring:bind path="name">
                                <form:input class="myInput" type="text" path="name" id="name" placeholder="Όνομα" style="margin-right: 5%"></form:input>
                            </spring:bind>

                            <spring:bind path="surname">
                                <form:input class="myInput" type="text" path="surname" id="surname" placeholder="Επίθετο"></form:input>
                            </spring:bind>
                            <spring:bind path="street">
                                <form:input class="myInput" type="text" path="street" id="street" placeholder="Οδός"  style="margin-right: 5%"></form:input>
                            </spring:bind>
                            <spring:bind path="streetNumber">
                                <form:input class="myInput" type="text" path="streetNumber" id="strno" placeholder="Αριθμός"></form:input>
                            </spring:bind>

                            <spring:bind path="postalCode">
                                <form:input class="myInput" type="text" path="postalCode" id="postcode" placeholder="Ταχυδρομικός Κώδικας"  style="margin-right: 5%"></form:input>
                            </spring:bind>
                            <spring:bind path="phone">
                                <form:input class="myInput" type="tel" path="phone" id="phonenum" placeholder="Τηλέφωνο (πχ 2101234567)" pattern="^\d{10}$"></form:input>
                            </spring:bind>

                            <div class="container" style="text-align: left;margin-top: 5%"> Διαλέξτε Ρόλο:
                                <radiobuttons>
                                    <input id="parent" class="radio-inline__input" type="radio" name="role" value="parent" checked="checked"/>
                                    <label class="radio-inline__label" style="margin-left: 35%" for="parent">
                                        Γονέας
                                    </label>
                                    <input id="provider" class="radio-inline__input" type="radio" name="role" value="provider"/>
                                    <label class="radio-inline__label" for="provider">
                                        Πάροχος
                                    </label>
                                </radiobuttons>
                            </div>
                        </div>

                        <div id="parentform" style='display:none'>

                            <div class="col-md-12">
                                <label class="row col-md-5 col-center-block"><b>Συμπληρώστε την τοποθεσία σας στο Χάρτη</b></label>
                                <input id="pac-input" class="controls" type="text" placeholder="Αναζήτηση" style="color: #000000;">
                                <div class="container" id="map" style="width:380px;height:380px;background:yellow;padding-left: 75px">
                                </div>
                                <spring:bind path="latitude">
                                    <form:input type="hidden" id="parentLat" placeholder="37.975446" path="latitude" value="${myLat}"></form:input>
                                </spring:bind>
                                <spring:bind path="longtitude">
                                    <form:input type="hidden" id="parentLon" default="23.734774" path="longtitude" value="${myLon}"></form:input>
                                </spring:bind>
                            </div>
                        </div>


                        <div id="providerform" style='display:none'>
                            <spring:bind path="companyName">
                                <form:input class="myInput" type="text"  path="companyName" id="providerAd" placeholder="Όνομα Επιχείρησης" style="margin-right: 5%"></form:input>
                            </spring:bind>
                            <spring:bind path="afm">
                                <form:input class="myInput" type="text"  path="afm" id="providerNe" placeholder="ΑΦΜ"></form:input>
                            </spring:bind>
                            <label style="margin-top: 5%"><strong>Περιγραφή</strong></label>
                            <spring:bind path="description">
                                <form:textarea rows="4" cols="50" path="description" id="providerInfo"></form:textarea>
                            </spring:bind>
                        </div>
                        <form:errors path="username" /><br>
                        <form:errors path="password"/><br>
                        <form:errors path="password_confirm"/> <br>
                        <form:errors path="email" /><br>
                        <form:errors path="afm"/> <br>

                        <input type="submit" name="register" class="btn btn-primary login register-submit" value="Εγγραφή" onclick="this.value='Παρακαλώ περιμένετε...'; this.disabled='disabled'; this.form.submit();" style="margin-top: 20px; cursor: pointer;">
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="jsp_packets/footer.jsp"/>
<jsp:include page="jsp_packets/scripts.jsp"/>

</body>


<script>
    $(document).ready(function() {

        $('.file-upload').hide();
        $('#file-upload1').show();

        $('.image-box').click(function() {
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
        $('#parentform').show();
        $("input[name='role']").click(function () {
            var role = $(this).attr('id');
            if (role === "provider") {
                $('#parentform').hide();
                $('#providerform').show();
                $('#providerAd').attr('required', true);
                $('#providerNe').attr('required', true);
                $('#providerInfo').attr('required', true);
            }
            else {
                $('#providerform').hide();
                $('#parentform').show();
                $('#parentLat').attr('required', true);
                $('#parentLon').attr('required', true);
            }
        });
    });
</script>




<script>

    function initAutocomplete() {


        //alert( "Latitude: "+ ${myLat} +" "+", longitude: "+ ${myLon} );
        var mapCanvas = document.getElementById("map");
        var myCenter=new google.maps.LatLng("37.975446", "23.734774");
        var mapOptions = {center: myCenter, zoom: 16};
        var map = new google.maps.Map(mapCanvas, mapOptions);

        document.getElementById("parentLat").value = myCenter.lat();
        document.getElementById("parentLon").value =  myCenter.lng();

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
            document.getElementById("parentLat").value = bounds2.lat();
            document.getElementById("parentLon").value = bounds2.lng();
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
            document.getElementById("parentLat").value = myCenter.lat();
            document.getElementById("parentLon").value =  myCenter.lng();
        }

    }

</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCQtXy0F2y3PihS1SSU6dfCgGyGIySYIKw&libraries=places&callback=initAutocomplete"></script>



</html>
