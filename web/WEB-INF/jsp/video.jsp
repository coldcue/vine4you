<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="video" type="com.vine4you.entity.VideoEntity"--%>
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
    <meta name="keywords"
          content="7 second videos,austin miles geter,awesome,best fails,best fails this month,best june,best of vine,best of vines,best vine,best vine clips,best vine ever,best vine june,best vine videos,best vines,best vines compilation,best vines this week today this month all time most viewed sex porn boobs tits ass sexy vines porn vines,best weekly,brendon urie,brittany furlan,clever,collection,creative,daily,david avila,day,drunk,dustin poynter,elton castee,epic,epic fail,eric dunn,every,faceplant,failing,fails of the week,frank thatguy preston,fun,funniest vine,funniest vines,funny,funny videos,funny vine,funny vine videos,funny vines,funny vines compilation,gareon conley,girls vines,girls vines compilation,greg baskwell,haha,hilarious,humor,idiot,interesting,jerome jarre,joey bitch,jokes,jordan burt,josh briggs,june,kc james,landon moss,latest,latest vines,laugh,laughing,living lavish,lol,marcus johns,matthew espinosa,michael lopriore,miikeyv,month,monthly,monthly fail compilation,monthly fails,new vines,newest fails,pranks,sexy vine,sexy vine compilation,sexy vine videos,stupid,the best vines of 2013,today,top vine ideas,twerking vine,twerking vine compilation,video,vids,vincent marcus,vine,vine compilation,vine compilation august 2013,vine june,vine twerk,vine video,vine videos,vine4you,vines,vines compilation,wipeout">
    <c:if test="${showVideoTitleInTitle}">
        <meta name="author" content="${video.author}">
    </c:if>
    <meta name="msvalidate.01" content="C585C5610CF6C5DE6ECCA7E20B528C21"/>

    <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/static/icons/touch-icon-iphone.png"/>
    <link rel="apple-touch-icon" sizes="72x72"
          href="${pageContext.request.contextPath}/static/icons/touch-icon-ipad.png"/>
    <link rel="apple-touch-icon" sizes="114x114"
          href="${pageContext.request.contextPath}/static/icons/touch-icon-iphone-retina.png"/>
    <link rel="apple-touch-icon" sizes="144x144"
          href="${pageContext.request.contextPath}/static/icons/touch-icon-ipad-retina.png"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/design/style-1-0-2-002.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/design/960.css"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/video.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/application-1-0-2-002.js"></script>

    <%--Facebook OpenGraph--%>
    <meta property="fb:app_id" content="593953400627542"/>
    <meta property="og:type" content="video"/>
    <meta property="og:url" content="http://www.vine4you.com/v/${video.key.id}"/>
    <meta property="og:title" content="${video.title}"/>
    <meta property="og:description"
          content="Vine4You.com is a collection of the best Vine videos you can find on the internet."/>
    <meta property="og:site_name" content="Vine4You.com"/>
    <meta property="og:image"
          content="${video.imageURL}"/>

    <%--Microdata--%>
    <meta name="title" content="${video.title} by ${video.author}"/>
    <meta name="description"
          content="Vine4You.com is a collection of the best Vine videos you can find on the internet."/>
    <link rel="image_src" href="${video.imageURL}"/>
    <link rel="video_src" href="${video.videoURL}"/>
    <meta name="video_height" content="600"/>
    <meta name="video_width" content="600"/>

    <%-- Google Costum Search - Put the following javascript before the closing </head> tag. --%>
    <script>
        (function () {
            var cx = '000271302565310508204:WMX510812725';
            var gcse = document.createElement('script');
            gcse.type = 'text/javascript';
            gcse.async = true;
            gcse.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') +
                    '//www.google.com/cse/cse.js?cx=' + cx;
            var s = document.getElementsByTagName('script')[0];
            s.parentNode.insertBefore(gcse, s);
        })();
    </script>
</head>
<body>

<%--Facebook JS--%>
<div id="fb-root"></div>
<script>
    window.fbAsyncInit = function () {
        FB.init({
            appId: '593953400627542',
            channelUrl: 'http://www.vine4you.com/static/channel.html',
            status: true,
            xfbml: true
        });

        FB.Event.subscribe('edge.create',
                function (response) {
                    if (response == "https://www.facebook.com/vine4you" || response == "https://facebook.com/vine4you") {
                        FacebookLikeStuff.setLiked(true);
                        FacebookLikeStuff.closeDialog();
                    }
                }
        );
    };

    (function (d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) {
            return;
        }
        js = d.createElement(s);
        js.id = id;
        js.src = "//connect.facebook.net/en_US/all.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));
</script>


<div id="headerContainer">
    <div class="container_16">
        <div class="grid_4">
            <div id="logoTextContainer">
                <a href="${pageContext.request.contextPath}/" id="logoText">Vine<span>4</span>You</a>
            </div>
        </div>
        <div class="grid_8">
            <div id="sloganTextContainer">
                <h1 id="sloganText">The best vines every <span
                        class="four">4</span> hours</h1>
            </div>

        </div>
        <div class="grid_4">
            <div id="pageLikeBox">
                <div class="fb-like" data-href="https://www.facebook.com/vine4you" data-width="450"
                     data-layout="button_count" data-show-faces="true" data-send="false"></div>
            </div>
        </div>
    </div>
</div>
<div id="mainContainer">
    <div class="container_16">
        <div class="grid_12" itemprop="video" itemscope itemtype="http://schema.org/VideoObject">
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

                <div id="videoInnerContainer">
                    <%--Required properties--%>
                    <meta itemprop="description"
                          content="Vine4You.com is a collection of the best Vine videos you can find on the internet."/>
                    <meta itemprop="thumbnailUrl" content="${video.imageURL}"/>

                    <%--Recommended properties--%>
                    <meta itemprop="duration" content="7S"/>
                    <meta itemprop="embedURL" content="${video.videoURL}"/>

                    <video id="vineVideo" class="video-js" data-setup="{'controls':false}" autoplay
                           loop preload="auto"
                           width="600"
                           height="600"
                           poster="${video.imageURL}">
                        <source itemprop="contentURL" src="${video.videoURL}" type="video/mp4"/>
                    </video>
                </div>
            </div>
            <div id="videoBottom">
                <div id="videoInfoContainer">
                    <div id="videoInfo" itemprop="name">
                        <div id="videoInfoTitle">${video.title}</div>
                        <div id="videoInfoAuthor">by <a href="#">${video.author}</a></div>
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
                    <div class="fb-comments" data-href="http://www.vine4you.com/v/${video.key.id}"
                         data-width="600"></div>
                </div>
            </div>
        </div>
        <div class="grid_4">
            <div id="squareAdContainer">
                <script type="text/javascript"><!--
                google_ad_client = "ca-pub-6335041482514787";
                /* SquareAdBig */
                google_ad_slot = "8014008870";
                google_ad_width = 250;
                google_ad_height = 250;
                //-->
                </script>
                <script type="text/javascript"
                        src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
                </script>
            </div>
            <div id="search">
                <gcse:searchbox-only></gcse:searchbox-only>
            </div>
            <ul id="featuredVideos">
                <c:forEach var="item" items="${featured}">
                    <li itemscope itemtype="http://schema.org/VideoObject">
                        <a itemprop="url" title="${item.title} by ${item.author}"
                           href="${pageContext.request.contextPath}/v/${item.key.id}">
                            <div class="container"><img width="72" height="72"
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

<%--Place this tag after the last +1 button tag.--%>
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
<%--Google Analytics--%>
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

<%--Async loading stuff--%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/design/video-js.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/design/jquery-impromptu.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-impromptu.js"></script>
</body>
</html>