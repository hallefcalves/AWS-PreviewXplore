const request = new XMLHttpRequest();
request.open("GET", "/FTT-WEB-091/game");

request.onload = function () {
  const response = JSON.parse(this.responseText);
  console.log(JSON.parse(this.responseText));
  
  const table = document.querySelector(".table-game");
  for(let line of response){
    var row = document.createElement("tr");
    var id = document.createElement("td");
    id.innerHTML = line.id;
    row.appendChild(id);
    var name = document.createElement("td");
    name.innerHTML = line.name;
    row.appendChild(name);
    var producer = document.createElement("td");
    producer.innerHTML = line.producer;
    row.appendChild(producer);
    var genre = document.createElement("td");
    genre.innerHTML = line.genre;
    row.appendChild(genre);
    var releaseDate = document.createElement("td");
    releaseDate.innerHTML = line.releaseDate;
    row.appendChild(releaseDate);
    table.appendChild(row);
  }
  console.log(table)
};

request.onerror = function () {
  console.log("erro ao executar a requisição");
};

request.send();

const requestMovie = new XMLHttpRequest();
requestMovie.open("GET", "/FTT-WEB-091/movie");

requestMovie.onload = function () {
  const response = JSON.parse(this.responseText);
  console.log(JSON.parse(this.responseText));
  
  const table = document.querySelector(".table-movie");
  for(let line of response){
    var row = document.createElement("tr");
    var id = document.createElement("td");
    id.innerHTML = line.id;
    row.appendChild(id);
    var name = document.createElement("td");
    name.innerHTML = line.name;
    row.appendChild(name);
    var producer = document.createElement("td");
    producer.innerHTML = line.producer;
    row.appendChild(producer);
    var genre = document.createElement("td");
    genre.innerHTML = line.genre;
    row.appendChild(genre);
    var releaseDate = document.createElement("td");
    releaseDate.innerHTML = line.releaseDate;
    row.appendChild(releaseDate);
    table.appendChild(row);
  }
  console.log(table)
};

requestMovie.onerror = function () {
  console.log("erro ao executar a requisição");
};

requestMovie.send();