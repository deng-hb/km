/**
 * @author denghb
 * @since 2021-09-11
 */
var el = function(id) {
  return document.getElementById(id);
}

el("last-time").onchange = function() {
  if (this.value == '') {
    el("day-range").style.display = "inline";
  } else {
    el("day-range").style.display = "none";
  }
}

var now = new Date();
var month = now.getMonth() + 1;
if (month < 10) {
    month = "0" + month;
}
el("day").value = now.getFullYear() + "-" + month + "-" + now.getDate();
el("time-start").value = "00:00";
el("time-end").value = "23:59";

// select
var kbEl = el("keyboard-layout");
kbEl.onchange = function() {
  refreshKbView();
}
var refreshKbView = function() {
  var options = kbEl.options;
  for (var i = 0; i < options.length; i++) {
    let v = options[i].value;
    if (v == kbEl.value) {
      el(v + "-container").style.display = "block";
    } else {
      el(v + "-container").style.display = "none";
    }
  }
}
if (navigator.userAgent.indexOf("Mac") > 0) {
  kbEl.value = "kaw";
  kbEl.style.display = "none";
  refreshKbView();
}

var getHeatmap = function(kb) {
  var heatmap = window['heatmap_' + kb];
  if (!heatmap) {
    heatmap = h337.create({
      radius: 60,
      container: el(kb)
    });
    window['heatmap_' + kb] = heatmap;
  }
  return heatmap;
}

var httpGet = function(url, callback) {
  // request server
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      var res = JSON.parse(this.responseText);
      callback(res);
    }
  };
  xhr.open("GET", url, true);
  xhr.send();
}

var doDraw = function() {
  // request server
  var url = "/km?lastTime=" + el('last-time').value;
  url += "&day=" + el('day').value;
  url += "&timeStart=" + el('time-start').value;
  url += "&timeEnd=" + el('time-end').value;

  httpGet(url, function(res) {
    // console.log(res);
    var stat = res.KEYBOARD.stat;
    el('total-keyboard').innerText = res.KEYBOARD.total;
    el('total-mouse').innerText = res.MOUSE.total;

    var arr = KEYBOARD[kbEl.value];
    var data = [];
    var max = 0, max2 = 0;
    for (var key in stat) {
      if (key.length == 1) {
        key = key.toUpperCase();
      }
      var point = arr[key];
      if (undefined == point) {
        console.log('"%o" undefined', key);
        continue;
      }
      point.value = stat[key];
      if (max < point.value) {
        max2 = max;
        max = point.value;
      }
      data.push(point);
    }

    //  > 20%
    if (max2 > 0 && max > max2 * 1.2) {
      max = max2 * 1.2;
    }
    var heatmap = getHeatmap(kbEl.value);
    heatmap.setData({
      max: max,
      data: data
    });
  });
}
// doDraw()
setInterval("doDraw()", 200);

// show all
var showAll = function(kb) {
  var heatmap = getHeatmap(kb);
  var arr = KEYBOARD[kb];
  var data = [];
  for (var key in arr) {
    var point = arr[key];
    point.value = 5;
    data.push(point);
  }

  heatmap.setData({
    max: 5,
    data: data
  });
}
// showAll('kaw');
