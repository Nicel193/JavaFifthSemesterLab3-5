const logoutTimeSec = 600;
var idleTime = 0;

$(document).ready(function () {
    var idleInterval = setInterval(timerIncrement, 1000);

    $(this).mousemove(function (e) {
        idleTime = 0;
    });
    $(this).keypress(function (e) {
        idleTime = 0;
    });
});

function timerIncrement() {
    idleTime = idleTime + 1;
    console.log(idleTime);
    if (idleTime >= logoutTimeSec) {
        window.location.href = '/login';
    }
}
