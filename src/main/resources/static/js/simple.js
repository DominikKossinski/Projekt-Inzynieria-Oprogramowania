function replace() {
    var text = document.getElementById("result-p").innerText;
    document.getElementById("text-area").value = text;
}

function toUpperCase() {
    var text = document.getElementById("text-area").value;
    var p = document.getElementById("result-p");
    var url = "/api/upper?text=" + text;
    fetch(url).then(function (value) {
        return value.text();
    }).then(function (text) {
        console.log(text);
        p.innerText = text;
    })
}

function toLowerCase() {
    var text = document.getElementById("text-area").value;
    var p = document.getElementById("result-p");
    var url = "/api/lower?text=" + text;
    fetch(url).then(function (value) {
        return value.text();
    }).then(function (text) {
        console.log(text);
        p.innerText = text;
    })
}

function autoCorrect() {
    var text = document.getElementById("text-area").value;
    var p = document.getElementById("result-p");
    var url = "/api/autocorrect?text=" + text;
    fetch(url).then(function (value) {
        return value.text();
    }).then(function (text) {
        console.log(text);
        p.innerText = text;
    })
}

function capitalize() {
    var text = document.getElementById("text-area").value;
    var p = document.getElementById("result-p");
    var url = "/api/capitalize?text=" + text;
    fetch(url).then(function (value) {
        return value.text();
    }).then(function (text) {
        console.log(text);
        p.innerText = text;
    })
}

function deleteRepeatedWord() {
    var text = document.getElementById("text-area").value;
    var p = document.getElementById("result-p");
    var url = "/api/repeatDel?text=" + text;
    fetch(url).then(function (value) {
        return value.text();
    }).then(function (text) {
        console.log(text);
        p.innerText = text;
    })
}

function expandShortcuts() {
    var text = document.getElementById("text-area").value;
    var p = document.getElementById("result-p");
    var url = "/api/expandShortcuts?text=" + text;
    fetch(url).then(function (value) {
        return value.text();
    }).then(function (text) {
        console.log(text);
        p.innerText = text;
    })
}

function inverse() {
    var text = document.getElementById("text-area").value;
    var p = document.getElementById("result-p");
    var url = "/api/inverse?text=" + text;
    fetch(url).then(function (value) {
        return value.text();
    }).then(function (text) {
        console.log(text);
        p.innerText = text;
    })
}

function expandMyShortcuts() {
    var text = document.getElementById("text-area").value;
    var p = document.getElementById("result-p");
    var url = "/api/expandMyShortcuts?text=" + text;
    fetch(url).then(function (value) {
        return value.text();
    }).then(function (text) {
        console.log(text);
        p.innerText = text;
    })
}

function expandNumbers(lg) {
    var text = document.getElementById("text-area").value;
    var p = document.getElementById("result-p");
    var url = "/api/numbers?text=" + text + "&lg=" + lg;
    fetch(url).then(function (value) {
        return value.text();
    }).then(function (text) {
        console.log(text);
        p.innerText = text;
    })

}

function goToBetterGui() {
    window.location.assign("/textTransformer");
}

