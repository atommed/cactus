function setEventInfo(obj){
  var name = document.getElementById('event_name');
  var address = document.getElementById('event_address');
  var date = document.getElementById('event_date');
  var price = document.getElementById('event_price');
  var description = document.getElementById('event_description');
  var image = document.getElementById('event_image');

  name.innerHTML = obj.name;
  address.innerHTML = obj.address;
  date.innerHTML = moment(obj.date).format('DD.MM.YYYY HH:mm');
  if (obj.price == 0){
    price.innerHTML = "Стоимость: бесплатно";
  }
  else{
    price.innerHTML = "Стоимость: "+obj.price +" грн.";
  }
  description.innerHTML  = obj.description;
  image.src=obj.uri;
}

function fillTop(){
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
      var response = JSON.parse(xhttp.responseText);
      var list = document.getElementById('top_list');
      for (var i = 0; i < response.length; i++){
        var child = document.createElement('a');
        child.className="collection-item";
        child.id="top_element#"+response[i].id;
        child.href="javascript:getTopElement("+response[i].id+")";
        child.innerHTML=response[i].name+" ("+moment(response[i].date).format('DD.MM.YYYY')+")";
        list.appendChild(child);
      }
    }
  };
  xhttp.open("GET", "http://"+serverIP+":8080/backend/api/raiting", true);
  xhttp.send();
}

function getTopElement(id){
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
      var response = JSON.parse(xhttp.responseText);
      for (var i = 0; i<response.length; i++){
        var button = document.getElementById('top_element#'+response[i].id);
        button.setAttribute("class","collection-item");
      }
      for (var i = 0; i < response.length; i++){var coord = {lat:response[i].lat, lng:response[i].lon};
        if (response[i].id == id){
          var button = document.getElementById('top_element#'+response[i].id);
          if (button.getAttribute("class").localeCompare("collection-item") == 0){
            button.setAttribute("class","collection-item active");
            setEventInfo(response[i]);
            document.getElementById('event_block_wrapper').style.display="block";
            var coord = {lat:response[i].lat, lng:response[i].lon};
            for (var j = 0; j<markerArray.length; j++){
              if (markerArray[j].position.lat() == coord.lat && markerArray[j].position.lng() == coord.lng){
                markerArray[j].click();
                break;
              }
            }
          }
          else{
            button.setAttribute("class","collection-item");
            document.getElementById('event_block_wrapper').style.display="none";
          }
          break;
        }
      }
    }
  };
  xhttp.open("GET", "http://"+serverIP+":8080/backend/api/raiting", true);
  xhttp.send();
}

function submitAddData(){
  var name = document.getElementById('name').value;
  var place = document.getElementById('place').value;
  var time = document.getElementById('time').value;
  var imgLink = document.getElementById('imgLink').value;
  var description = document.getElementById('description').value;

  var request = JSON.stringify({
    "name":name,
    "place":place,
    "time":time,
    "imgLink":imgLink,
    "description":description
  });

  var xhr = new XMLHttpRequest();
  xhr.open("POST", 'http://'+serverIP+':8080/backend/api/addEvent', true)
  //xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
  xhr.onreadystatechange = function() {
    if (xhr.readyState == 4 && xhr.status == 200) {
      alert(xhr.responseText);
    }
  }
  xhr.send();
}
