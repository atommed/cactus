var map;
var serverIP="10.55.28.12";
var markerArray = [];
function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 50.4546600, lng: 30.5238000},
    zoom: 12
    });
  //var infoWindow = new google.maps.InfoWindow({map: map});


  // Try HTML5 geolocation.
  /*if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
      var pos = {
        lat: position.coords.latitude,
        lng: position.coords.longitude
      };

      //infoWindow.setPosition(pos);
      //infoWindow.setContent('You are here.');
      map.setCenter(pos);
    }, function() {
      handleLocationError(true, infoWindow, map.getCenter());
    });
  } else {
    // Browser doesn't support Geolocation
    handleLocationError(false, infoWindow, map.getCenter());
  }*/

  //placeMarker("Hello!",50.455500, 30.356876);
  var curPos = {lat: 50.447573, lng:30.422884};
  placeInitialMarkers();
  map.setCenter(curPos);
}
function handleLocationError(browserHasGeolocation, infoWindow, pos) {
  infoWindow.setPosition(pos);
  infoWindow.setContent(browserHasGeolocation ?
                        'Error: The Geolocation service failed.' :
                        'Error: Your browser doesn\'t support geolocation.');
}

function placeMarker(object, lattitude, longtitude, id){
  var myLatLng = {lat: lattitude, lng: longtitude};
  map.setCenter(myLatLng);

  var marker = new google.maps.Marker({
    position: myLatLng,
    map: map
  });
  markerArray.push(marker);

  /*var contentString = desc;
  var infowindow = new google.maps.InfoWindow({
    content: contentString
  });*/

  marker.addListener('click', function() {
    //infowindow.open(map, marker);
    if (marker.getAnimation() != null) {
      marker.setAnimation(null);
      document.getElementById('event_block_wrapper').style.display="none";
    } else {
      for (var i = 0; i<markerArray.length; i++){
        markerArray[i].setAnimation(null);
      }
      marker.setAnimation(google.maps.Animation.BOUNCE);
      setEventInfo(object);
      document.getElementById('event_block_wrapper').style.display="block";
    }
  });
}

function placeInitialMarkers(){
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
      var response = JSON.parse(xhttp.responseText);
      for (var i = 0; i < response.length; i++){
        var desc = response[i].name + " ("+moment(response[i].date).format('DD.MM.YYYY HH:mm')+")";
        placeMarker(response[i], response[i].lat, response[i].lon, response[i].id);
      }
    }
  };
  xhttp.open("GET", "http://"+serverIP+":9090/backend/api/db", true);
  xhttp.send();
}
