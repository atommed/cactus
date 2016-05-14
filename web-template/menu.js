function getPopular() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
      alert(xhttp.responseText);
    }
  };
  xhttp.open("GET", "http://10.55.33.91:8080/api/k", true);
  xhttp.send();
}
