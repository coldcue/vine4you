$(document).ready(function () {
    App.video = videojs("vineVideo");
    $("#videoInnerContainer").click(function () {
        App.toggle();
    });
});


var App = {
    video: null,

    toggle: function () {
        if (this.video.paused())
            this.video.play();
        else
            this.video.pause();
    }
};