<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  ~ Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com)                   ~
  ~                                                                          ~
  ~ All rights reserved. No part of this code may be reproduced, distributed,~
  ~ or transmitted in any form or by any means, including photocopying,      ~
  ~ recording, or other electronic or mechanical methods, without the prior  ~
  ~ written permission of the owner.                                         ~
  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~--%>

<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 8/11/13
  Time: 2:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vine4You</title>
    <link rel="stylesheet" href="/static/design/style.css"/>
    <link rel="stylesheet" href="/static/design/960.css"/>
    <script type="text/javascript" src="/static/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="/static/js/video.js"></script>
</head>
<body>
<div id="fb-root"></div>
<script>(function (d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s);
    js.id = id;
    js.src = "//connect.facebook.net/en_US/all.js#xfbml=1&appId=593953400627542";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
<div id="headerContainer">
    <div class="container_16">
        <div class="grid_4">
            <div id="logoTextContainer">
                <h1 id="logoText">Vine<span>4</span>You</h1>
            </div>
        </div>
        <div class="grid_12">
            <div id="sloganTextContainer">
                <h1 id="sloganText">The best vines every <span>4</span> hours</h1>
            </div>
        </div>
    </div>
</div>
<div id="mainContainer">
    <div class="container_16">
        <div class="grid_12">
            <div id="videoOuterContainer">
                <div id="videoLeftContainer"></div>
                <div id="videoInnerContainer">
                    <video id="video" loop width="600" height="600" preload="auto"
                           poster="${video.imageURL}"
                           src="${video.videoURL}">
                    </video>
                </div>
                <div id="videoRightContainer">
                </div>
            </div>
            <div id="videoSocial">
                <div class="twitter">
                    <a href="https://twitter.com/share" class="twitter-share-button" data-size="large"
                       data-url="http://www.vine4you/v/${video.key.id}"
                       data-count="none">Tweet</a>
                    <script>!function (d, s, id) {
                        var js, fjs = d.getElementsByTagName(s)[0], p = /^http:/.test(d.location) ? 'http' : 'https';
                        if (!d.getElementById(id)) {
                            js = d.createElement(s);
                            js.id = id;
                            js.src = p + '://platform.twitter.com/widgets.js';
                            fjs.parentNode.insertBefore(js, fjs);
                        }
                    }(document, 'script', 'twitter-wjs');</script>
                </div>
                <div class="google">
                    <div class="g-plusone" data-href="http://www.vine4you/v/${video.key.id}"></div>
                </div>
                <div class="facebook">
                    <div class="fb-like" data-href="http://www.vine4you/v/${video.key.id}"
                         data-width="250" data-show-faces="false" data-send="true"></div>
                </div>

            </div>
            <div id="videoInfoContainer">
                <div id="videoInfo">
                    <div id="videoInfoTitle">${video.title}</div>
                    <div id="videoInfoAuthor">by <a href="#">${video.author}</a></div>
                </div>
            </div>
            <div id="horizontalAdContainer">
                <img src="https://storage.googleapis.com/support-kms-prod/SNP_2922339_en_v1" width="468" height="60">
            </div>
            <div id="commentsContainer">
                <div class="fb-comments" data-href="http://example.com" data-width="600"></div>
            </div>
        </div>
        <div class="grid_4">
            <div id="squareAdContainer">
                <img src="https://storage.googleapis.com/support-kms-prod/SNP_2922332_en_v0" width="200" height="200">
            </div>
            <ul id="featuredVideos">
                <c:forEach var="item" items="${featured}">
                    <li><a href="/v/${item.key.id}">
                        <div class="container"><img width="60" height="60"
                                                    src="${item.imageURL}"
                                                    alt="${item.title}"/>
                            <span class="title">${item.title}</span>
                            <span class="author">by ${item.author}</span>
                        </div>
                    </a></li>
                </c:forEach>
            </ul>
            <div id="verticalAdContainer">
                <img src="https://storage.googleapis.com/support-kms-prod/SNP_2922277_en_v0" alt="ad" width="160"
                     height="600"/>
            </div>
        </div>

    </div>
</div>
<!-- Place this tag after the last +1 button tag. -->
<script type="text/javascript">
    (function () {
        var po = document.createElement('script');
        po.type = 'text/javascript';
        po.async = true;
        po.src = 'https://apis.google.com/js/plusone.js';
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(po, s);
    })();
</script>
</body>
</html>