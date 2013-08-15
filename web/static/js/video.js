var Video = new VideoImpl();

$(document).ready(function () {
    Video.initt();
    Video.toggle();

    $("#video").click(function () {
        Video.toggle();
    });
});

function VideoImpl() {
    this.videoElement = undefined;
    this.started = false;

    this.initt = function () {
        Video.videoElement = $("#video").get(0);
    };

    this.toggle = function () {
        if (Video.started) {
            Video.videoElement.pause();
            Video.started = false;
        }
        else {
            Video.videoElement.play();
            Video.started = true;
        }
    };
}

