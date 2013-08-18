$(document).ready(function () {
    App.vineURL = $("#vineURL").val();
    console.log("Vine URL:" + App.vineURL)
    App.fetchVideoURL();
    App.showVideo();
});

var App = {
    vineURL: "",
    videoURL: "",

    showVideo: function () {
        $("#videoInnerContainer").append('<video id=\'video\' loop preload autoplay width=\'600\' height=\'600\'><source src=\'' + this.videoURL + '\' type=\'video/mp4\'/></video>');
        console.log("Video appended")
    },

    fetchVideoURL: function () {
        this.videoURL = "https://v.cdn.vine.co/r/videos/E4F8B8BB32980543699891433472_1f4ecd34064.3.1_rmMbGep4sP15hHcMUeI8K5zvwvE75.FVFu.9C5E4xX4PgtK.sDmyycwvOeCkRqRj.mp4";
        console.log("Video URL:" + App.videoURL)
    }
};
