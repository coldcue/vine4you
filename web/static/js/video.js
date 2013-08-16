var videoElement = undefined;
var videoState = false;

function OnVideoLoaded(e) {
    videoElement = e;
    ToggleVideo();
}

function ToggleVideo() {
    if (videoState) videoElement.pause();
    else videoElement.play();
}

function OnVideoPlay() {
    videoState = true;
}

function OnVideoPause() {
    videoState = false;
}