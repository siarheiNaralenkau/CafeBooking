function loadFacetzCollector(M, Q, A) {
    var O = "front.facetz.net";
    var S = "";
    if (A && typeof A === "object") {
       for(var k in A) {
            S += "&" + encodeURIComponent(k) + "=" + encodeURIComponent(A[k]);
       }
    }
    var P = "//" + O + "/collect?source=" + encodeURIComponent(M) + "&id=" +
        encodeURIComponent(Q) + "&previous_url=" + encodeURIComponent(document.referrer) +
        "&rn=" + Math.random() + S;
    var L = new Image();
    L.src = P;
    L.onload = function () {
        return;
    }
};

function loadFacetzRetargeting(M, Q, A) {
    var O = "front.facetz.net";
    var S = "";
    if (A && typeof A === "object") {
       for(var k in A) {
            S += "&" + encodeURIComponent(k) + "=" + encodeURIComponent(A[k]);
       }
    }
    var P = "//" + O + "/retarget?source=" + encodeURIComponent(M) + "&id=" +
        encodeURIComponent(Q) + "&previous_url=" + encodeURIComponent(document.referrer) +
        "&rn=" + Math.random() + S;
    var L = new Image();
    L.src = P;
    L.onload = function () {
        return;
    }
}
