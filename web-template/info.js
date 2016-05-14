function setEventInfo(obj){
  var name = document.getElementById('event_name');
  var address = document.getElementById('event_address');
  var date = document.getElementById('event_date');
  var price = document.getElementById('event_price');
  var description = document.getElementById('event_description');

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
}
