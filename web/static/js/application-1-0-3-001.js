/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com) and                 *
 * Adrian Szell (szelladrian@gmail.com)                                       *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com)                     *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

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
    watchThreshold: 15,
    dialog: undefined,

    checkLike: function () {
        if (!this.hasLiked()) {
            if (this.incrementWatches() >= this.watchThreshold) {
                this.dialog = $.prompt("<div style='min-height: 300px; text-align: center;'><div class=\"fb-like-box\" data-href=\"https://www.facebook.com/vine4you\" data-width=\"300\" data-height=\"300\" data-show-faces=\"true\" data-header=\"false\" data-stream=\"false\" data-show-border=\"false\"></div></div>", {
                    title: "Please like us!",
                    buttons: {  "I've already liked it!": true, "Cancel": false},
                    focus: 1,
                    submit: function (e, v) {
                        if (v)
                            FacebookLikeStuff.setLiked(true);
                        else
                            FacebookLikeStuff.setWatches(FacebookLikeStuff.watchThreshold * -1);
                    }
                });
            }

        }
    },

    closeDialog: function () {
        $.prompt.close();
    },

    hasLiked: function () {
        return !!(($.cookie("liked") === "true"));
    },

    setLiked: function (val) {
        $.cookie("liked", val, { expires: 3650, path: '/' });
        if (val) $.removeCookie("watches", { path: '/' })
    },

    getWatches: function () {
        var value = $.cookie("watches");
        if (value == undefined) return 0;
        return parseInt(value);
    },

    setWatches: function (value) {
        $.cookie("watches", value, { expires: 7, path: '/' });
    },

    incrementWatches: function () {
        var value = this.getWatches() + 1;
        this.setWatches(value);
        return value;
    }
};