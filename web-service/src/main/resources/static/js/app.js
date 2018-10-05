var stompClient = null;

function connect() {
    var socket = new SockJS('/messages');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        var uuid = createUUID();
        stompClient.subscribe('/topic/image/' + uuid + '/reply', function (greeting) {
            showImg(greeting);
        });
        stompClient.send("/app/image/" + uuid, {});
    });
}

function showImg(message) {
    $("#image").attr('src', message.body);
    $("#image-input").attr('value', message.body);
}

$(function () {
    $("#connect").click(function () {
        connect();
    });
});

function createUUID() {
    // http://www.ietf.org/rfc/rfc4122.txt
    var s = [];
    var hexDigits = "0123456789ABCDEF";
    for (var i = 0; i < 32; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[12] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
    s[16] = hexDigits.substr((s[16] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01

    var uuid = s.join("");
    return uuid;
}