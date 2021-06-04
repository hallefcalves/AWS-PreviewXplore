var local = window.localStorage.getItem("trailerName");
local = local.replace(/\s/g, '%20');
console.log(local);
var j = {}
var api = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=20&q=" + local + "%20trailer&type=video&key=AIzaSyCgp5gmSDx4AqtdNR9KoBjH8pz97myDTUE"
console.log(api);
var data = {}
$.ajax({
  type: 'GET',
  url: api,
  data: data,
  async: false,
  beforeSend: function (xhr) {
    if (xhr && xhr.overrideMimeType) {
      xhr.overrideMimeType('application/json;charset=utf-8');
    }
  },
  dataType: 'json',
  success: function (data) {
  	console.log(data)
    j = data
  }
});
console.log(j)
var id = j.items[0].id.videoId
var url = 'https://youtube.com/embed/' + id
console.log(j)
console.log(id)
console.log(url)
var frame = document.createElement("iframe");
frame.src = url;
frame.title = "YouTube video player"
frame.allow = "accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
frame.allowfullscreen = true
document.getElementById('div2').appendChild(frame);

