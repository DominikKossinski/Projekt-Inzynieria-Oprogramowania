dragElement(document.getElementById("auto-div"));
dragElement(document.getElementById("capitalize-div"));
dragElement(document.getElementById("del-div"));
dragElement(document.getElementById("expand-s-div"));
dragElement(document.getElementById("inverse-div"));
dragElement(document.getElementById("lower-div"));
dragElement(document.getElementById("expand-m-div"));
dragElement(document.getElementById("expand-eng-div"));
dragElement(document.getElementById("expand-pl-div"));
dragElement(document.getElementById("upper-div"));

var lewa = ["auto-div", "capitalize-div", "del-div", "expand-s-div", "inverse-div", "lower-div",
    "expand-m-div", "expand-eng-div", "expand-pl-div", "upper-div"];
var prawa = [];
var tempP = [];
var toDel = [];

function dragElement(elmnt) {
    var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
    if (document.getElementById(elmnt.id + "-header")) {
        /* if present, the header is where you move the DIV from:*/
        document.getElementById(elmnt.id + "-header").onmousedown = dragMouseDown;
    } else {
        /* otherwise, move the DIV from anywhere inside the DIV:*/
        elmnt.onmousedown = dragMouseDown;
    }

    function dragMouseDown(e) {
        e = e || window.event;
        e.preventDefault();
        // get the mouse cursor position at startup:
        pos3 = e.clientX;
        pos4 = e.clientY;
        document.onmouseup = closeDragElement;
        // call a function whenever the cursor moves:
        document.onmousemove = elementDrag;
    }

    function elementDrag(e) {
        e = e || window.event;
        e.preventDefault();
        // calculate the new cursor position:
        pos1 = pos3 - e.clientX;
        pos2 = pos4 - e.clientY;
        pos3 = e.clientX;
        pos4 = e.clientY;
        // set the element's new position:
        elmnt.style.top = (elmnt.offsetTop - pos2) + "px";
        elmnt.style.left = (elmnt.offsetLeft - pos1) + "px";
    }

    function closeDragElement() {
        document.onmouseup = null;
        document.onmousemove = null;
        if (elmnt.offsetLeft > 960 || elmnt.offsetLeft < 700 || elmnt.offsetTop < 20 || elmnt.offsetTop > 560) {
            if (prawa.indexOf(elmnt.id) >= 0) {
                prawa.splice(prawa.indexOf(elmnt.id), 1);
                toDel.push(elmnt.id);
            }
        } else {
            if (prawa.indexOf(elmnt.id) < 0) {
                tempP.push(elmnt.id);
            }
        }
        renderDivs();
        /*var lText = "Lewa:" + lewa.join(";");
        var rText = "Prawa: " + prawa.join(";");
        document.getElementById("left-label").innerText = lText;
        document.getElementById("right-label").innerText = rText;*/
    }
}

function renderDivs() {
    for (var i = 0; i < lewa.length; i++) {
        var div = document.getElementById(lewa[i]);
        div.style.left = 1020 + "px";
        div.style.top = 40 + 50 * i + "px";
    }
    for (i = 0; i < tempP.length; i++) {
        prawa.push(tempP[i] + "_" + prawa.length);
        console.log("Prawa len: " + prawa.length);
    }
    tempP = [];
    for (i = 0; i < toDel.length; i++) {
        var element = document.getElementById(toDel[i]);
        console.log("Del: " + toDel[i]);
        if (element !== null) {
            element.innerHTML = "";
            element.parentNode.removeChild(element);
        }
    }
    toDel = [];
    for (i = 0; i < prawa.length; i++) {
        element = document.getElementById(prawa[i]);
        if (element !== null) {
            element.innerHTML = "";
            element.parentNode.removeChild(element);
        }
    }
    console.log(prawa);
    for (i = 0; i < prawa.length; i++) {
        div = document.createElement("div");
        div.setAttribute("id", prawa[i]);
        div.className = "l";
        console.log("id: '" + prawa[i] + "'");
        var div1 = document.createElement("div");
        div1.setAttribute("id", prawa[i] + "-header");
        if (prawa[i].substring(0, prawa[i].indexOf("_")) === "lower-div") {
            div1.innerText = "To Lower Case";
        } else if (prawa[i].substring(0, prawa[i].indexOf("_")) === "upper-div") {
            div1.innerText = "To Upper Case";
        } else if (prawa[i].substring(0, prawa[i].indexOf("_")) === "auto-div") {
            div1.innerText = "Auto Correct";
        } else if (prawa[i].substring(0, prawa[i].indexOf("_")) === "capitalize-div") {
            div1.innerText = "Capitalize";
        } else if (prawa[i].substring(0, prawa[i].indexOf("_")) === "del-div") {
            div1.innerText = "Delete Repeated Words";
        } else if (prawa[i].substring(0, prawa[i].indexOf("_")) === "expand-s-div") {
            div1.innerText = "Expand Shortcuts";
        } else if (prawa[i].substring(0, prawa[i].indexOf("_")) === "inverse-div") {
            div1.innerText = "Inverse";
        } else if (prawa[i].substring(0, prawa[i].indexOf("_")) === "expand-m-div") {
            div1.innerText = "Expand My Shortcuts";
        } else if (prawa[i].substring(0, prawa[i].indexOf("_")) === "expand-eng-div") {
            div1.innerText = "Expand English Numbers";
        } else if (prawa[i].substring(0, prawa[i].indexOf("_")) === "expand-pl-div") {
            div1.innerText = "Expand Polish Numbers";
        } else {
            div1.innerText = "Error";
        }
        div1.className = "transformation-div";
        div.appendChild(div1);
        div.style.left = 720 + "px";
        div.style.top = 40 + 50 * i + "px";
        document.getElementById("wrapper").appendChild(div);
        dragElement(div);
    }
}

function startTransformation() {
    var array = [];
    for (var i = 0; i < prawa.length; i++) {
        if (prawa[i].substring(0, prawa[i].indexOf("_")) === "lower-div") {
            array.push("lower");
        } else if (prawa[i].substring(0, prawa[i].indexOf("_")) === "upper-div") {
            array.push("upper");
        } else if (prawa[i].substring(0, prawa[i].indexOf("_")) === "auto-div") {
            array.push("auto");
        } else if (prawa[i].substring(0, prawa[i].indexOf("_")) === "capitalize-div") {
            array.push("capitalize");
        } else if (prawa[i].substring(0, prawa[i].indexOf("_")) === "del-div") {
            array.push("delete");
        } else if (prawa[i].substring(0, prawa[i].indexOf("_")) === "expand-s-div") {
            array.push("expandShortcuts");
        } else if (prawa[i].substring(0, prawa[i].indexOf("_")) === "inverse-div") {
            array.push("inverse");
        } else if (prawa[i].substring(0, prawa[i].indexOf("_")) === "expand-m-div") {
            array.push("expandMyShortcuts");
        } else if (prawa[i].substring(0, prawa[i].indexOf("_")) === "expand-eng-div") {
            array.push("expandNumbersEn");
        } else if (prawa[i].substring(0, prawa[i].indexOf("_")) === "expand-pl-div") {
            array.push("expandNumbersPl");
        }
    }
    console.log(array);
    var text = document.getElementById("text-area").value;
    var translations = JSON.stringify(array);
    console.log("text: '" + text + "'");
    console.log("tran: '" + translations + "'");
    translations = encodeURI(translations);
    console.log("tranUri: '" + translations + "'");
    fetch("api/multiTranslation?text=" + text + "&translations=" + translations).then(
        function (value) {
            return value.text();
        }
    ).then(function (data) {
        if (data === "ERROR") {
            alert("error");
        } else {
            document.getElementById("result-p").innerText = data;
        }
    })
}

function replace() {
    document.getElementById("text-area").value = document.getElementById("result-p").innerText;
    document.getElementById("result-p").innerText = "";
}

function clearTranslations() {
    for (var i = 0; i < prawa.length; i++) {
        var element = document.getElementById(prawa[i]);
        if (element !== null) {
            element.innerHTML = "";
            element.parentNode.removeChild(element);
        }
    }
    prawa = [];
    renderDivs();
}