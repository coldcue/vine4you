<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="video" type="com.vine4you.entity.VideoEntity"--%>
<%--@elvariable id="prevVideo" type="com.vine4you.entity.VideoEntity"--%>
<%--@elvariable id="nextVideo" type="com.vine4you.entity.VideoEntity"--%>
<%--@elvariable id="featured" type="java.util.Collection<com.vine4you.entity.VideoEntity>"--%>
<%--@elvariable id="showVideoTitleInTitle" type="java.lang.Boolean"--%>
<%--@elvariable id="sortby" type="java.lang.String"--%>
<html>
<head>
    <c:choose>
        <c:when test="${showVideoTitleInTitle}">
            <title>${video.title} by ${video.author} - Vine 4 You</title>
        </c:when>
        <c:otherwise>
            <title>Vine 4 You - Best and Funniest Vine videos</title>
        </c:otherwise>
    </c:choose>
    <meta name="description"
          content="Best and Funniest Vine videos">
    <meta name="keywords"
          content="7 second videos,austin miles geter,awesome,best fails,best fails this month,best june,best of vine,best of vines,best vine,best vine clips,best vine ever,best vine june,best vine videos,best vines,best vines compilation,best vines this week today this month all time most viewed sex porn boobs tits ass sexy vines porn vines,best weekly,brendon urie,brittany furlan,clever,collection,creative,daily,david avila,day,drunk,dustin poynter,elton castee,epic,epic fail,eric dunn,every,faceplant,failing,fails of the week,frank thatguy preston,fun,funniest vine,funniest vines,funny,funny videos,funny vine,funny vine videos,funny vines,funny vines compilation,gareon conley,girls vines,girls vines compilation,greg baskwell,haha,hilarious,humor,idiot,interesting,jerome jarre,joey bitch,jokes,jordan burt,josh briggs,june,kc james,landon moss,latest,latest vines,laugh,laughing,living lavish,lol,marcus johns,matthew espinosa,michael lopriore,miikeyv,month,monthly,monthly fail compilation,monthly fails,new vines,newest fails,pranks,sexy vine,sexy vine compilation,sexy vine videos,stupid,the best vines of 2013,today,top vine ideas,twerking vine,twerking vine compilation,video,vids,vincent marcus,vine,vine compilation,vine compilation august 2013,vine june,vine twerk,vine video,vine videos,vine4you,vines,vines compilation,wipeout">
    <c:if test="${showVideoTitleInTitle}">
        <meta name="author" content="${video.author}">
    </c:if>

    <%--Styles--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/design/design-1-0-6-001.min.css"/>

    <meta name="msvalidate.01" content="C585C5610CF6C5DE6ECCA7E20B528C21"/>

    <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/static/icons/touch-icon-iphone.png"/>
    <link rel="apple-touch-icon" sizes="72x72"
          href="${pageContext.request.contextPath}/static/icons/touch-icon-ipad.png"/>
    <link rel="apple-touch-icon" sizes="114x114"
          href="${pageContext.request.contextPath}/static/icons/touch-icon-iphone-retina.png"/>
    <link rel="apple-touch-icon" sizes="144x144"
          href="${pageContext.request.contextPath}/static/icons/touch-icon-ipad-retina.png"/>

    <%--Facebook OpenGraph--%>
    <meta property="fb:app_id" content="593953400627542"/>
    <meta property="og:type" content="video"/>
    <meta property="og:url" content="http://www.vine4you.com/v/${video.key.id}"/>
    <meta property="og:title" content="${video.title} - Vine4You.com"/>
    <meta property="og:description"
          content="Best and Funniest Vine videos at www.Vine4You.com"/>
    <meta property="og:site_name" content="Vine 4 You"/>
    <meta property="og:image"
          content="${video.imageURL}"/>

    <%--Microdata--%>
    <meta name="title" content="${video.title} by ${video.author}"/>
    <meta name="description"
          content="Best and Funniest Vine videos at www.Vine4You.com"/>
    <link rel="image_src" href="${video.imageURL}"/>

    <%--Google Webmaster verification--%>
    <meta name="google-site-verification" content="2XonEj136GchZCXal2J5flsNfv6AjRsZQDTmpiymDMY"/>

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
        <div class="grid_6">
            <div id="sloganTextContainer">
                <h1 id="sloganText">The best vines every <span
                        class="four">4</span> hours</h1>
            </div>

        </div>
        <div class="grid_2">
            <div id="mostLikedContainer"><a href="/?sortby=likes">
                <div id="mostLikedButton"
                     <c:if test="${sortby eq 'likes'}">class="active"</c:if> >TOP 100
                </div>
            </a></div>
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
                        <a id="videoLeftLink"
                           href="/v/${prevVideo.key.id}<c:if test="${sortby != null}">?sortby=${sortby}</c:if>">
                            <div id="videoLeftContainer">
                                <div>Prev</div>
                            </div>
                        </a>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${nextVideo eq null}">
                        <div id="videoRightSpace">
                        </div>
                    </c:when>
                    <c:otherwise>
                        <a id="videoRightLink"
                           href="/v/${nextVideo.key.id}<c:if test="${sortby != null}">?sortby=${sortby}</c:if>">
                            <div id="videoRightContainer">
                                <div>Next</div>
                            </div>
                        </a>
                    </c:otherwise>
                </c:choose>

                <div id="videoInnerContainer">
                    <%--Required properties--%>
                    <meta itemprop="description"
                          content="Best and Funniest Vine videos at www.Vine4You.com"/>
                    <meta itemprop="thumbnailUrl" content="${video.imageURL}"/>

                    <%--Recommended properties--%>
                    <meta itemprop="duration" content="7S"/>
                    <meta itemprop="embedURL" content="${video.videoURL}"/>

                    <video id="vineVideo" class="video-js" data-setup="" autoplay
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
                    <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
                    <!-- Vine4YouHorizontalAd -->
                    <ins class="adsbygoogle"
                         style="display:inline-block;width:468px;height:60px"
                         data-ad-client="ca-pub-6335041482514787"
                         data-ad-slot="2078553275"></ins>
                    <script>
                        (adsbygoogle = window.adsbygoogle || []).push({});
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
                <div id="squareAd">
                    <script type="text/javascript"><!--
                    google_ad_client = "ca-pub-6335041482514787";
                    /* Vine4YouRectangle1 */
                    google_ad_slot = "9775638872";
                    google_ad_width = 300;
                    google_ad_height = 250;
                    //-->
                    </script>
                    <script type="text/javascript"
                            src="//pagead2.googlesyndication.com/pagead/show_ads.js">
                    </script>
                </div>
            </div>
            <div id="squareAdContainer">
                <div id="squareAd">
                    <script type="text/javascript"><!--
                    google_ad_client = "ca-pub-6335041482514787";
                    /* Vine4YouRectangle2 */
                    google_ad_slot = "7718432074";
                    google_ad_width = 300;
                    google_ad_height = 250;
                    //-->
                    </script>
                    <script type="text/javascript"
                            src="//pagead2.googlesyndication.com/pagead/show_ads.js">
                    </script>
                </div>
            </div>
            <div id="searchContainer">
                <gcse:searchbox-only></gcse:searchbox-only>
            </div>
            <ul id="featuredVideos">
                <c:forEach var="item" items="${featured}">
                    <li itemscope itemtype="http://schema.org/VideoObject">
                        <a itemprop="url" title="${item.title} by ${item.author}"
                           href="${pageContext.request.contextPath}/v/${item.key.id}<c:if test="${sortby != null}">?sortby=${sortby}</c:if>">
                            <div class="container"><img width="72" height="72"
                                                        src="${item.imageURL}"
                                                        alt="${item.title}"/>
                                <span class="title">${item.title}</span>
                                <span class="author">by ${item.author}</span>
                            </div>
                        </a></li>
                </c:forEach>
            </ul>
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
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-43247982-1', 'vine4you.com');
    ga('send', 'pageview');

</script>

<%--Javascript--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/script-1-0-6-001.min.js"></script>

</body>
</html>