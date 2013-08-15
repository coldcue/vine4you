<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="<c:url value="/static/design/style.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/static/design/960.css"/>"/>
    <script type="text/javascript" src="<c:url value="/static/js/jquery-1.10.2.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/video.js"/>"></script>
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
    </div>
</div>
<div id="mainContainer">
    <div class="container_16">
        <div class="grid_12">
            <div id="videoOuterContainer">
                <div id="videoLeftContainer"></div>
                <div id="videoInnerContainer">
                    <video id="video" loop width="600" height="600" preload="auto"
                           poster="https://v.cdn.vine.co/r/thumbs/F2D698FDD5977818925133959168_1e3236340f5.3.1.mp4_MRm_81WWWjc5jdVDKohg0pdn18PvawK1llPzzxSsYfEtYMkjd6ds99JE5SjPXtwL.jpg?versionId=a6_nI90sotUuWOG6EdV3o70bjxl3tg2z"
                           src="https://mtc.cdn.vine.co/r/videos/0CECB5C22D977818923066089472_19ef4834695.3.1_NCp.f586ONi8_MomyWoU86G4YGVXLjyYXkz4wC8aL7X602eA3k0ulIBs0I3vsLa..mp4?versionId=z3eBunAiiWs9k.Lr3TuF83QuzAW3IEKZ">
                    </video>
                </div>
                <div id="videoRightContainer">
                </div>
            </div>
            <div id="videoSocial">
                <div class="twitter">
                    <a href="https://twitter.com/share" class="twitter-share-button" data-size="large"
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
                    <div class="g-plusone"></div>
                </div>
                <div class="facebook">
                    <div class="fb-like" data-href="http://developers.facebook.com/docs/reference/plugins/like"
                         data-width="250" data-show-faces="false" data-send="true"></div>
                </div>

            </div>
            <div id="videoInfoContainer">
                <div id="videoInfo">
                    <div id="videoInfoTitle">White girls vs Black girls</div>
                    <div id="videoInfoAuthor">by <a href="#">Jess le Forgia</a></div>
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
                <li><a href="#">
                    <div class="container"><img width="60" height="60"
                                                src="https://v.cdn.vine.co/r/thumbs/F2D698FDD5977818925133959168_1e3236340f5.3.1.mp4_MRm_81WWWjc5jdVDKohg0pdn18PvawK1llPzzxSsYfEtYMkjd6ds99JE5SjPXtwL.jpg?versionId=a6_nI90sotUuWOG6EdV3o70bjxl3tg2z"
                                                alt="White girls vs Black girls"/>
                        <span class="title">White girls vs Black girls</span>
                        <span class="author">by Jerome Jarre</span>
                    </div>
                </a></li>
                <li><a href="#">
                    <div class="container"><img width="60" height="60"
                                                src="https://v.cdn.vine.co/r/thumbs/F2D698FDD5977818925133959168_1e3236340f5.3.1.mp4_MRm_81WWWjc5jdVDKohg0pdn18PvawK1llPzzxSsYfEtYMkjd6ds99JE5SjPXtwL.jpg?versionId=a6_nI90sotUuWOG6EdV3o70bjxl3tg2z"
                                                alt="White girls vs Black girls"/>
                        <span class="title">White girls vs Black girls</span>
                        <span class="author">by Jerome Jarre</span>
                    </div>
                </a></li>
                <li><a href="#">
                    <div class="container"><img width="60" height="60"
                                                src="https://v.cdn.vine.co/r/thumbs/F2D698FDD5977818925133959168_1e3236340f5.3.1.mp4_MRm_81WWWjc5jdVDKohg0pdn18PvawK1llPzzxSsYfEtYMkjd6ds99JE5SjPXtwL.jpg?versionId=a6_nI90sotUuWOG6EdV3o70bjxl3tg2z"
                                                alt="White girls vs Black girls"/>
                        <span class="title">White girls vs Black girls</span>
                        <span class="author">by Jerome Jarre</span>
                    </div>
                </a></li>
                <li><a href="#">
                    <div class="container"><img width="60" height="60"
                                                src="https://v.cdn.vine.co/r/thumbs/F2D698FDD5977818925133959168_1e3236340f5.3.1.mp4_MRm_81WWWjc5jdVDKohg0pdn18PvawK1llPzzxSsYfEtYMkjd6ds99JE5SjPXtwL.jpg?versionId=a6_nI90sotUuWOG6EdV3o70bjxl3tg2z"
                                                alt="White girls vs Black girls"/>
                        <span class="title">White girls vs Black girls</span>
                        <span class="author">by Jerome Jarre</span>
                    </div>
                </a></li>
                <li><a href="#">
                    <div class="container"><img width="60" height="60"
                                                src="https://v.cdn.vine.co/r/thumbs/F2D698FDD5977818925133959168_1e3236340f5.3.1.mp4_MRm_81WWWjc5jdVDKohg0pdn18PvawK1llPzzxSsYfEtYMkjd6ds99JE5SjPXtwL.jpg?versionId=a6_nI90sotUuWOG6EdV3o70bjxl3tg2z"
                                                alt="White girls vs Black girls"/>
                        <span class="title">White girls vs Black girls</span>
                        <span class="author">by Jerome Jarre</span>
                    </div>
                </a></li>
                <li><a href="#">
                    <div class="container"><img width="60" height="60"
                                                src="https://v.cdn.vine.co/r/thumbs/F2D698FDD5977818925133959168_1e3236340f5.3.1.mp4_MRm_81WWWjc5jdVDKohg0pdn18PvawK1llPzzxSsYfEtYMkjd6ds99JE5SjPXtwL.jpg?versionId=a6_nI90sotUuWOG6EdV3o70bjxl3tg2z"
                                                alt="White girls vs Black girls"/>
                        <span class="title">White girls vs Black girls</span>
                        <span class="author">by Jerome Jarre</span>
                    </div>
                </a></li>
                <li><a href="#">
                    <div class="container"><img width="60" height="60"
                                                src="https://v.cdn.vine.co/r/thumbs/F2D698FDD5977818925133959168_1e3236340f5.3.1.mp4_MRm_81WWWjc5jdVDKohg0pdn18PvawK1llPzzxSsYfEtYMkjd6ds99JE5SjPXtwL.jpg?versionId=a6_nI90sotUuWOG6EdV3o70bjxl3tg2z"
                                                alt="White girls vs Black girls"/>
                        <span class="title">White girls vs Black girls</span>
                        <span class="author">by Jerome Jarre</span>
                    </div>
                </a></li>
                <li><a href="#">
                    <div class="container"><img width="60" height="60"
                                                src="https://v.cdn.vine.co/r/thumbs/F2D698FDD5977818925133959168_1e3236340f5.3.1.mp4_MRm_81WWWjc5jdVDKohg0pdn18PvawK1llPzzxSsYfEtYMkjd6ds99JE5SjPXtwL.jpg?versionId=a6_nI90sotUuWOG6EdV3o70bjxl3tg2z"
                                                alt="White girls vs Black girls"/>
                        <span class="title">White girls vs Black girls</span>
                        <span class="author">by Jerome Jarre</span>
                    </div>
                </a></li>
                <li><a href="#">
                    <div class="container"><img width="60" height="60"
                                                src="https://v.cdn.vine.co/r/thumbs/F2D698FDD5977818925133959168_1e3236340f5.3.1.mp4_MRm_81WWWjc5jdVDKohg0pdn18PvawK1llPzzxSsYfEtYMkjd6ds99JE5SjPXtwL.jpg?versionId=a6_nI90sotUuWOG6EdV3o70bjxl3tg2z"
                                                alt="White girls vs Black girls"/>
                        <span class="title">White girls vs Black girls</span>
                        <span class="author">by Jerome Jarre</span>
                    </div>
                </a></li>
                <li><a href="#">
                    <div class="container"><img width="60" height="60"
                                                src="https://v.cdn.vine.co/r/thumbs/F2D698FDD5977818925133959168_1e3236340f5.3.1.mp4_MRm_81WWWjc5jdVDKohg0pdn18PvawK1llPzzxSsYfEtYMkjd6ds99JE5SjPXtwL.jpg?versionId=a6_nI90sotUuWOG6EdV3o70bjxl3tg2z"
                                                alt="White girls vs Black girls"/>
                        <span class="title">White girls vs Black girls</span>
                        <span class="author">by Jerome Jarre</span>
                    </div>
                </a></li>
                <li><a href="#">
                    <div class="container"><img width="60" height="60"
                                                src="https://v.cdn.vine.co/r/thumbs/F2D698FDD5977818925133959168_1e3236340f5.3.1.mp4_MRm_81WWWjc5jdVDKohg0pdn18PvawK1llPzzxSsYfEtYMkjd6ds99JE5SjPXtwL.jpg?versionId=a6_nI90sotUuWOG6EdV3o70bjxl3tg2z"
                                                alt="White girls vs Black girls"/>
                        <span class="title">White girls vs Black girls</span>
                        <span class="author">by Jerome Jarre</span>
                    </div>
                </a></li>
                <li><a href="#">
                    <div class="container"><img width="60" height="60"
                                                src="https://v.cdn.vine.co/r/thumbs/F2D698FDD5977818925133959168_1e3236340f5.3.1.mp4_MRm_81WWWjc5jdVDKohg0pdn18PvawK1llPzzxSsYfEtYMkjd6ds99JE5SjPXtwL.jpg?versionId=a6_nI90sotUuWOG6EdV3o70bjxl3tg2z"
                                                alt="White girls vs Black girls"/>
                        <span class="title">White girls vs Black girls</span>
                        <span class="author">by Jerome Jarre</span>
                    </div>
                </a></li>
                <li><a href="#">
                    <div class="container"><img width="60" height="60"
                                                src="https://v.cdn.vine.co/r/thumbs/F2D698FDD5977818925133959168_1e3236340f5.3.1.mp4_MRm_81WWWjc5jdVDKohg0pdn18PvawK1llPzzxSsYfEtYMkjd6ds99JE5SjPXtwL.jpg?versionId=a6_nI90sotUuWOG6EdV3o70bjxl3tg2z"
                                                alt="White girls vs Black girls"/>
                        <span class="title">White girls vs Black girls</span>
                        <span class="author">by Jerome Jarre</span>
                    </div>
                </a></li>
                <li><a href="#">
                    <div class="container"><img width="60" height="60"
                                                src="https://v.cdn.vine.co/r/thumbs/F2D698FDD5977818925133959168_1e3236340f5.3.1.mp4_MRm_81WWWjc5jdVDKohg0pdn18PvawK1llPzzxSsYfEtYMkjd6ds99JE5SjPXtwL.jpg?versionId=a6_nI90sotUuWOG6EdV3o70bjxl3tg2z"
                                                alt="White girls vs Black girls"/>
                        <span class="title">White girls vs Black girls</span>
                        <span class="author">by Jerome Jarre</span>
                    </div>
                </a></li>
                <li><a href="#">
                    <div class="container"><img width="60" height="60"
                                                src="https://v.cdn.vine.co/r/thumbs/F2D698FDD5977818925133959168_1e3236340f5.3.1.mp4_MRm_81WWWjc5jdVDKohg0pdn18PvawK1llPzzxSsYfEtYMkjd6ds99JE5SjPXtwL.jpg?versionId=a6_nI90sotUuWOG6EdV3o70bjxl3tg2z"
                                                alt="White girls vs Black girls"/>
                        <span class="title">White girls vs Black girls</span>
                        <span class="author">by Jerome Jarre</span>
                    </div>
                </a></li>
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