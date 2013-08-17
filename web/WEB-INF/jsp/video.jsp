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
    <c:choose>
        <c:when test="${showVideoTitleInTitle}">
            <title>${video.title} - Vine4You</title>
        </c:when>
        <c:otherwise>
            <title>Vine4You - Best Vines</title>
        </c:otherwise>
    </c:choose>
    <meta name="description"
          content="Vine4You.com is a collection of the best Vine videos you can find on the internet.">
    <meta name="keywords" content="vine,videos,funny,vine4you,collection">
    <c:if test="${showVideoTitleInTitle}">
        <meta name="author" content="${video.author}">
    </c:if>

    <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/static/icons/touch-icon-iphone.png"/>
    <link rel="apple-touch-icon" sizes="72x72"
          href="${pageContext.request.contextPath}/static/icons/touch-icon-ipad.png"/>
    <link rel="apple-touch-icon" sizes="114x114"
          href="${pageContext.request.contextPath}/static/icons/touch-icon-iphone-retina.png"/>
    <link rel="apple-touch-icon" sizes="144x144"
          href="${pageContext.request.contextPath}/static/icons/touch-icon-ipad-retina.png"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/design/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/design/960.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/video.js"></script>

    <meta property="fb:app_id" content="593953400627542"/>
    <meta property="og:type" content="video"/>
    <meta property="og:url" content="http://www.vine4you.com/v/${video.key.id}"/>
    <meta property="og:title" content="${video.title}"/>
    <meta property="og:description"
          content="Vine4You.com is a collection of the best Vine videos you can find on the internet."/>
    <meta property="og:site_name" content="Vine4You.com"/>
    <meta property="og:image"
          content="${video.imageURL}"/>
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
                <a href="${pageContext.request.contextPath}/" id="logoText">Vine<span>4</span>You</a>
            </div>
        </div>
        <div class="grid_8">
            <div id="sloganTextContainer">
                <h1 id="sloganText">The best vines every <span>4</span> hours</h1>
            </div>

        </div>
        <div class="grid_4">
            <div id="pageLikeBox">
                <div class="fb-like" data-href="https://www.facebook.com/vine4you" data-width="450"
                     data-layout="button_count" data-show-faces="false" data-send="false"></div>
            </div>
        </div>
    </div>
</div>
<div id="mainContainer">
    <div class="container_16">
        <div itemprop="video" itemscope itemtype="http://schema.org/VideoObject" class="grid_12">
            <meta itemprop="duration" content="7S"/>
            <meta itemprop="thumbnail" content="${video.imageURL}"/>
            <div id="videoOuterContainer">
                <c:choose>
                    <c:when test="${prevVideo eq null}">
                        <div id="videoLeftSpace">
                        </div>
                    </c:when>
                    <c:otherwise>
                        <a id="videoLeftLink" href="/v/${prevVideo.key.id}">
                            <div id="videoLeftContainer">
                                <div>Prev</div>
                            </div>
                        </a>
                    </c:otherwise>
                </c:choose>

                <a id="videoRightLink" href="/v/${nextVideo.key.id}">
                    <div id="videoRightContainer">
                        <div>Next</div>
                    </div>
                </a>

                <div id="videoInnerContainer"
                     style="background-image: url(${video.imageURL});">
                    <video id="videoElement" style="display: none;" autoplay loop width="600" height="600"
                           preload="auto"
                           src="${video.videoURL}" type="video/mp4">
                    </video>
                    <script type="text/javascript">
                        Video.init();
                    </script>
                </div>


            </div>
            <div id="videoSocial">
                <div class="twitter">
                    <a href="https://twitter.com/share" class="twitter-share-button" data-size="large"
                       data-url="http://www.vine4you.com/v/${video.key.id}"
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
                    <div class="g-plusone" data-href="http://www.vine4you.com/v/${video.key.id}"></div>
                </div>
                <div class="facebook">
                    <div class="fb-like" data-href="http://www.vine4you.com/v/${video.key.id}"
                         data-width="250" data-show-faces="false" data-send="true"></div>
                </div>

            </div>
            <div id="videoInfoContainer">
                <div id="videoInfo">
                    <div id="videoInfoTitle" itemprop="name">${video.title}</div>
                    <div id="videoInfoAuthor">by <a href="#" itemprop="creator">${video.author}</a></div>
                </div>
            </div>
            <div id="horizontalAdContainer">
                <script type="text/javascript"><!--
                google_ad_client = "ca-pub-6335041482514787";
                /* HorizontalAd */
                google_ad_slot = "2078553275";
                google_ad_width = 468;
                google_ad_height = 60;
                //-->
                </script>
                <script type="text/javascript"
                        src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
                </script>
            </div>
            <div id="commentsContainer">
                <div class="fb-comments" data-href="http://www.vine4you.com/v/${video.key.id}" data-width="600"></div>
            </div>
        </div>
        <div class="grid_4">
            <div id="squareAdContainer">
                <script type="text/javascript"><!--
                google_ad_client = "ca-pub-6335041482514787";
                /* SquareAd */
                google_ad_slot = "8125086870";
                google_ad_width = 200;
                google_ad_height = 200;
                //-->
                </script>
                <script type="text/javascript"
                        src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
                </script>
            </div>
            <ul id="featuredVideos">
                <c:forEach var="item" items="${featured}">
                    <li><a href="${pageContext.request.contextPath}/v/${item.key.id}">
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
                <script type="text/javascript"><!--
                google_ad_client = "ca-pub-6335041482514787";
                /* VerticalAd */
                google_ad_slot = "9601820071";
                google_ad_width = 160;
                google_ad_height = 600;
                //-->
                </script>
                <script type="text/javascript"
                        src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
                </script>
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
<script>
    (function (i, s, o, g, r, a, m) {
        i['GoogleAnalyticsObject'] = r;
        i[r] = i[r] || function () {
            (i[r].q = i[r].q || []).push(arguments)
        }, i[r].l = 1 * new Date();
        a = s.createElement(o),
                m = s.getElementsByTagName(o)[0];
        a.async = 1;
        a.src = g;
        m.parentNode.insertBefore(a, m)
    })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

    ga('create', 'UA-43247982-1', 'vine4you.com');
    ga('send', 'pageview');

</script>
</body>
</html>