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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/design/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/design/960.css"/>
</head>
<body>
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
                <div id="videoInnerContainer">
                    <video id="currentVideo" autoplay loop width="600" height="600"
                           poster="https://v.cdn.vine.co/r/thumbs/F2D698FDD5977818925133959168_1e3236340f5.3.1.mp4_MRm_81WWWjc5jdVDKohg0pdn18PvawK1llPzzxSsYfEtYMkjd6ds99JE5SjPXtwL.jpg?versionId=a6_nI90sotUuWOG6EdV3o70bjxl3tg2z"
                           preload="auto"
                           src="https://mtc.cdn.vine.co/r/videos/0CECB5C22D977818923066089472_19ef4834695.3.1_NCp.f586ONi8_MomyWoU86G4YGVXLjyYXkz4wC8aL7X602eA3k0ulIBs0I3vsLa..mp4?versionId=z3eBunAiiWs9k.Lr3TuF83QuzAW3IEKZ">

                    </video>
                    <video id="nextVideo" style="display: none;" loop width="600" height="600"
                           poster="https://v.cdn.vine.co/r/thumbs/F2D698FDD5977818925133959168_1e3236340f5.3.1.mp4_MRm_81WWWjc5jdVDKohg0pdn18PvawK1llPzzxSsYfEtYMkjd6ds99JE5SjPXtwL.jpg?versionId=a6_nI90sotUuWOG6EdV3o70bjxl3tg2z"
                           preload="auto"
                           src="https://mtc.cdn.vine.co/r/videos/0CECB5C22D977818923066089472_19ef4834695.3.1_NCp.f586ONi8_MomyWoU86G4YGVXLjyYXkz4wC8aL7X602eA3k0ulIBs0I3vsLa..mp4?versionId=z3eBunAiiWs9k.Lr3TuF83QuzAW3IEKZ">

                    </video>
                </div>
            </div>
            <div id="videoInfoContainer">
                <div id="videoInfo">
                    <div class="title">Black Girls vs Black Girls</div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>