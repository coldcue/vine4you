$(document).ready(function () {
    App.video = videojs("vineVideo");
    $("#videoInnerContainer").click(function () {
        App.toggle();
    });
    FacebookLikeStuff.checkLike();
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

var FacebookLikeStuff = {
    watchThreshold: 20,

    checkLike: function () {
        if (!this.hasLiked()) {
            if (this.incrementWatches() >= this.watchThreshold)
                console.log("show dialog")
        }
    },

    hasLiked: function () {
        return false;
    },

    getWatches: function () {
        var value = $.cookie("watches");
        if (value == undefined) return 0;
        return parseInt(value);
    },

    incrementWatches: function () {
        var value = this.getWatches() + 1;
        $.cookie('watches', value, { expires: 7, path: '/' });
        return value;
    }
};