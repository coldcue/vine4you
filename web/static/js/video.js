var videoElement = undefined;
var videoState = false;
var videoLoaded = false;
var pageLoaded = false;

document.addEventListener('DOMContentLoaded', function () {
    pageLoaded = true;
    StartVideo();
});

function OnVideoLoaded(e) {
    videoElement = e;
    videoLoaded = true;
    StartVideo();
}

function StartVideo() {
    if (videoLoaded && pageLoaded) {
        videoElement.removeAttribute("style");
        ToggleVideo();
    }
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