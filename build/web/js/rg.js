
var err = false;
function todoInd(ev) {
    err = false;
    var toPrint = "";
    if ($('[name=login]').val().length > 8) {
        $('[name=login]').css('background', 'red');
        err = true;
        toPrint += "login trop long (>8)\n";
    } else if ($('[name=login]').val().length === 0) {
        $('[name=login]').css('background', 'red');
        err = true;
        toPrint += "login vide\n";
    } else if ($('[name=login]').val().length < 6) {
        $('[name=login]').css('background', 'red');
        err = true;
        toPrint += "login trop court (<6)\n";
    } else {
        $('[name=login]').css('background', 'transparent');
    }

    if ($('[name=password]').val().length < 6) {
        $('[name=password]').css('background', 'red');
        err = true;
        toPrint += "mdp trop court (<6)\n";
    } else {
        $('[name=password]').css('background', 'transparent');
    }
    if (toPrint === "")
        toPrint = "connexion reussie";
    return toPrint;

}
function todoPla(ev) {
    err = false;
    var toPrint = "";
    if ($('[name=name]').val().length === 0) {
        $('[name=name]').css('background', 'red');
        err = true;
        toPrint += "prenom vide\n";
    } else if ($('[name=name]').val().length < 6) {
        $('[name=name]').css('background', 'red');
        err = true;
        toPrint += "prenom trop court (<6)\n";
    } else {
        $('[name=name]').css('background', 'transparent');
    }
    if ($('[name=lastname]').val().length === 0) {
        $('[name=lastname]').css('background', 'red');
        err = true;
        toPrint += "nom vide\n";
    } else if ($('[name=lastname]').val().length < 6) {
        $('[name=lastname]').css('background', 'red');
        err = true;
        toPrint += "nom trop court (<6)\n";
    } else {
        $('[name=lastname]').css('background', 'transparent');
    }
    if (toPrint === "")
        toPrint = "insertion reussie";
    return toPrint;

}
function todoRef(ev) {
    err = false;
    var toPrint = "";
    if ($('[name=name]').val().length === 0) {
        $('[name=name]').css('background', 'red');
        err = true;
        toPrint += "prenom vide\n";
    } else if ($('[name=name]').val().length < 6) {
        $('[name=name]').css('background', 'red');
        err = true;
        toPrint += "prenom trop court (<6)\n";
    } else {
        $('[name=name]').css('background', 'transparent');
    }
    if ($('[name=lastname]').val().length === 0) {
        $('[name=lastname]').css('background', 'red');
        err = true;
        toPrint += "nom vide\n";
    } else if ($('[name=lastname]').val().length < 6) {
        $('[name=lastname]').css('background', 'red');
        err = true;
        toPrint += "nom trop court (<6)\n";
    } else {
        $('[name=lastname]').css('background', 'transparent');
    }
    if (toPrint === "")
        toPrint = "insertion reussie";
    return toPrint;

}
function todoCou(ev) {
    err = false;
    var toPrint = "";
    if ($('[name=name]').val().length === 0) {
        $('[name=name]').css('background', 'red');
        err = true;
        toPrint += "nom vide\n";
    } else if ($('[name=name]').val().length < 3) {
        $('[name=name]').css('background', 'red');
        err = true;
        toPrint += "nom trop court (<3)\n";
    } else {
        $('[name=name]').css('background', 'transparent');
    }

    if (toPrint === "")
        toPrint = "insertion reussie";
    return toPrint;

}
function todoMat(ev) {
    err = false;
    var toPrint = "";


    if (toPrint === "")
        toPrint = "insertion reussie";
    return toPrint;

}

function todoSubmitInd(ev) {

    alert(todoInd(ev));
    if (err) {
        ev.preventDefault();
    }
}

function todoSubmitPla(ev) {

    alert(todoPla(ev));
    if (err) {
        ev.preventDefault();
    }
}

function todoSubmitRef(ev) {

    alert(todoRef(ev));
    if (err) {
        ev.preventDefault();
    }
}

function todoSubmitCou(ev) {

    alert(todoCou(ev));
    if (err) {
        ev.preventDefault();
    }
}

function todoSubmitMat(ev) {

    alert(todoMat(ev));
    if (err) {
        ev.preventDefault();
    }
}
function setEventsInd() {
    $('form').submit(todoSubmitInd);
    $('[name=login]').blur(todoInd);
    $('[name=password]').blur(todoInd);
}

function setEventsPla() {
    $('form').submit(todoSubmitPla);
    $('[name=name]').blur(todoPla);
    $('[name=lastname]').blur(todoPla);
    $('[name=sexe]').blur(todoPla);
    $('[name=nationalite]').blur(todoPla);
}

function setEventsRef() {
    $('form').submit(todoSubmitRef);
    $('[name=name]').blur(todoRef);
    $('[name=lastname]').blur(todoRef);
    $('[name=level]').blur(todoRef);
    $('[name=nationalite]').blur(todoRef);
}

function setEventsCou() {
    $('form').submit(todoSubmitCou);
    $('[name=name]').blur(todoCou);
}

function setEventsMat() {
    $('form').submit(todoSubmitMat);
    $('[name=tournament]').blur(todoMat);
    $('[name=j1]').blur(todoMat);
    $('[name=j2]').blur(todoMat);
    $('[name=referee]').blur(todoMat);
    $('[name=court]').blur(todoMat);
    $('[name=matchtime]').blur(todoMat);
}
var url = window.location.href;
if (url.indexOf("index.html") > -1)
    setEventsInd();
if (url.indexOf("record_players.html") > -1)
    setEventsPla();
if (url.indexOf("record_referee.html") > -1)
    setEventsRef();
if (url.indexOf("record_courts.html") > -1)
    setEventsCou();
if (url.indexOf("planif_match.html") > -1)
    setEventsMat();