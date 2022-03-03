<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="viewbody hm-black-light">
    <div class="mask flex-center pattern-4">
        <ul>
            <li>
                <br><br>
                <h1 align="left" class="h1-responsive wow fadeInUp title" style="color: #ffffff;"><strong>Βρείτε Εκδηλώσεις για τα παιδιά σας</strong></h1>
                <br><br><br><br><br><br><br><br>
            </li>
            <li class="searchbuttons" style="padding-left: 110px;">
                <br><br>

                <form:form method="post" action="results" modelAttribute="SearchForm" enctype="multipart/form-data" id="importApForm">
                    <div class="row wow fadeIn" data-wow-delay="0.4s">
                        <div class="col-md-3">
                            <div class="md-form">
                                <label for="text" style="color: #49071c; font-size: 1.2em; padding-left: 4px;">Ψάχνω..</label>
                                <spring:bind path="description">
                                    <form:input type="text" value=" " id="text" path="description"></form:input>
                                </spring:bind>
                            </div>

                        </div>
                        <%java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd"); %>
                        <% String Today=df.format(new java.util.Date()); %>
                        <% String Month=(Integer.toString(Integer.parseInt(Today.substring(6,7))+1)); %>          <!--THELEI POLI KALITERO TROPO APO AUTI TI MPAKALIA!!! -->
                        <% String NextMonth=Today.substring(0,6)+ Month + Today.substring(7); %>
                        <div class="col-md-2">
                            <div class="md-form">
                                <label for="dateFrom" style="color: #49071c; font-size: 1.2em; padding-left: 4px;">Από</label>
                                <spring:bind path="dateFrom">
                                    <form:input type="text" value="<%=Today%>" id="date2" path="dateFrom"></form:input>
                                </spring:bind>

                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="md-form">
                                <label for="dateTo" style="color: #49071c; font-size: 1.2em; padding-left: 4px;">Έως</label>
                                <spring:bind path="dateTo">
                                    <form:input type="text" value="<%= NextMonth %>" id="date" path="dateTo"></form:input>
                                </spring:bind>

                            </div>
                        </div>

                        <div class="col-md-2">
                            <div class="md-form">
                                <label for="age" style="color: #49071c; font-size: 1.2em; padding-left: 4px;">Ηλικία</label>
                                <spring:bind path="age">
                                    <form:input type="number" value="8" id="age" path="age"></form:input>
                                </spring:bind>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="md-form">
                                <label for="category" class="active" style="color: #49071c; font-size: 1.2em; padding-left: 4px;">Κατηγορία</label>
                                <spring:bind path="category">
                                    <form:select name="guests" path="category" id="category" class="form-control validate">
                                        <option  value="1">Αθλητισμός</option>
                                        <option  value="2">Μουσική</option>
                                        <option  value="3">Μαθήματα</option>
                                        <option  value="4">Θέατρο</option>
                                        <option  value="5">Σινεμά</option>
                                        <option  value="6">Τέχνη</option>
                                        <option  value="-1">Οποιοδήποτε</option>
                                    </form:select>
                                </spring:bind>


                            </div>
                        </div>

                        <div class="col-md-9"></div>
                        <div class="col-md-2">
                            <input type="hidden" name="offset" value="0"/>
                            <div class="md-form">
                                <button class="btn btn-lg btn-danger" style="background-color: #85ba61;">Αναζητηση</button>
                            </div>
                        </div>
                        <div class="col-md-9"></div>
                    </div>
                    <div class="row wow fadeIn" data-wow-delay="0.4s">
                        <div class="col-md-6">
                            <label class="row col-md-5 col-center-block"><b>Η τοποθεσία σας</b></label>
                            <input id="pac-input" class="controls" type="text" placeholder="Search Box" style="color: #000000;">
                            <div class="container" id="map" style="width:380px;height:380px;background:yellow;padding-left: 75px">
                            </div>
                            <spring:bind path="latitude">
                                <form:input type="hidden" id="latitude" path="latitude" value="${myLat}"></form:input>
                            </spring:bind>
                            <spring:bind path="longtitude">
                                <form:input type="hidden" id="longtitude" path="longtitude" value="${myLon}"></form:input>
                            </spring:bind>
                        </div>
                        <div class="col-md-2">
                        </div>
                        <div class="col-md-4">
                            </br></br></br></br></br></br></br>
                            <div class="md-form">
                                <label for="radius" class="active text-center" style="color: #49071c; font-size: 1.2em; padding-left: 4px;">Ακτίνα Αναζήτησης</label>
                                <div class="row">
                                    <div class="col-xs-6">
                                        </br>
                                        <div class="range text-center">
                                            <spring:bind path="radius">
                                                <form:input type="range" name="range" min="1" max="50" value="10"  onchange="range.value=value" path="radius"></form:input>
                                            </spring:bind>
                                            <br>
                                            <output id="range">10</output><output>km</output>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form:form>


            </li>
            <li class="payment">
                <i class="fa fa-cc-visa fa-2x" aria-hidden="true" style="color: #ffffff;"></i>
                <i class="fa fa-cc-mastercard fa-2x" aria-hidden="true" style="color: #ffffff;"></i>
                <i class="fa fa-money fa-2x" aria-hidden="true" style="color: #ffffff;"></i>
            </li>
        </ul>
    </div>
</div>

<script>
    $(function() {
        $("#checkin").datepicker();
        $("#checkout").datepicker();
    });

    $( function() {
        var dateFormat = "yy/mm/dd",
            from = $( "#date"  )
                .datepicker({
                    dateFormat: 'yy-mm-dd',
                    defaultDate: "+1w",
                    changeMonth: true,
                    numberOfMonths: 1
                })
                .on( "change", function() {
                    to.datepicker( "option", "minDate", getDate( this ) );
                }),
            to = $( "#checkout" ).datepicker({
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
                date = $.datepicker.parseDate( 'yy-mm-dd',dateFormat, element.value );
            } catch( error ) {
                date = null;
            }

            return date;
        }
    } );
</script>




<script>
    $(function() {
        $("#checkin").datepicker();
        $("#checkout").datepicker();
    });

    $( function() {
        var dateFormat = "yy/mm/dd",
            from = $( "#date2"  )
                .datepicker({
                    dateFormat: 'yy-mm-dd',
                    defaultDate: "+1w",
                    changeMonth: true,
                    numberOfMonths: 1
                })
                .on( "change", function() {
                    to.datepicker( "option", "minDate", getDate( this ) );
                }),
            to = $( "#checkout" ).datepicker({
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
                date = $.datepicker.parseDate( 'yy-mm-dd',dateFormat, element.value );
            } catch( error ) {
                date = null;
            }

            return date;
        }
    } );
</script>
















<script>

    function initAutocomplete() {

        //alert( "Latitude: "+ ${myLat} +" "+", longitude: "+ ${myLon} );
        var mapCanvas = document.getElementById("map");
        var myCenter=new google.maps.LatLng(${myLat}, ${myLon});
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

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCQtXy0F2y3PihS1SSU6dfCgGyGIySYIKw&libraries=places&callback=initAutocomplete"></script>
