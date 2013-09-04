<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/xml;charset=UTF-8" language="java" %>
<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9"
        xmlns:image="http://www.google.com/schemas/sitemap-image/1.1"
        xmlns:video="http://www.google.com/schemas/sitemap-video/1.1">
    <c:forEach items="${videos}" var="video">
        <url>
            <loc>http://www.vine4you.com/v/${video.key.id}</loc>
            <image:image>
                <image:loc>${video.imageURL}</image:loc>
            </image:image>
            <video:video>
                <video:content_loc>${video.videoURL}</video:content_loc>
                <video:thumbnail_loc>${video.imageURL}</video:thumbnail_loc>
                <video:title>${video.title}</video:title>
                <video:duration>7</video:duration>
                <video:player_loc allow_embed="yes" autoplay="ap=1">${video.videoURL}</video:player_loc>
                <video:description>Vine4You.com is a collection of the best Vine videos you can find on the internet
                </video:description>
            </video:video>
        </url>
    </c:forEach>
</urlset>