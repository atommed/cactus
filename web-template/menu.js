function getPopular() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
      alert(xhttp.readyState);
      alert(xhttp.responseText);
    }
  };
  xhttp.open("GET", "http://10.55.33.91:8080/backend/api/k", true);
  xhttp.send();
}

var usualColor = "rgb(247, 247, 156)";
var hoverColor = "rgb(247, 247, 44)";
var clickColor = "rgb(247, 107, 0)";

function filter_click(fId){
  var filter = document.getElementById(fId);
  var color = window.getComputedStyle(filter, null).backgroundColor;
  if (color.localeCompare(hoverColor) == 0){
    filter.style.backgroundColor = clickColor;
    document.getElementById(fId+'_status').innerHTML="true";
  } else{
    filter.style.backgroundColor = usualColor;
    document.getElementById(fId+'_status').innerHTML="false";
  }
}

function filter_hover(fId){
  var filter = document.getElementById(fId);
  if (document.getElementById(fId+'_status').innerHTML == "false"){
    filter.style.backgroundColor = hoverColor;
  }
}

function filter_out(fId){
  var filter = document.getElementById(fId);
  if (document.getElementById(fId+'_status').innerHTML == "false"){
    filter.style.backgroundColor = usualColor;
  }
}

function filter_submit(){
  var status1 = document.getElementById('filter1_status').innerHTML;
  var status2 = document.getElementById('filter2_status').innerHTML;
  var status3 = document.getElementById('filter3_status').innerHTML;
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
      alert(xhttp.responseText);
    }
  };
  var path = "http://10.55.33.91:8080/api/k?"+"filter1="+status1+"&"+"filter2="+status2+"&"+"filter3="+status3;
  xhttp.open("GET", path , true);
  xhttp.send();
}
