/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com) and                 *
 * Adrian Szell (szelladrian@gmail.com)                                       *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

package com.vine4you.servlet;

import com.vine4you.entity.VideoEntity;
import com.vine4you.enums.VideoServletSorting;
import com.vine4you.factories.VideoCacheServiceFactory;
import com.vine4you.factories.VideoServiceFactory;
import com.vine4you.service.VideoCacheService;
import com.vine4you.service.VideoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 8/16/13
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class VideoServlet extends HttpServlet {

    VideoCacheService videoCacheService = VideoCacheServiceFactory.getVideoCacheService();
    VideoService videoService = VideoServiceFactory.getVideoService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id;
        try {
            String[] split = request.getPathInfo().split("/", 2);
            id = Long.parseLong(split[1]);
        } catch (Exception ignored) {
            response.sendRedirect("/");
            return;
        }

        VideoEntity video = null;
        try {
            //Check cache
            video = videoCacheService.getVideoEntity(id);

            //If not in cache
            if (video == null)
                video = videoService.getVideoEntity(id);
        } catch (Exception ignored) {
        }

        VideoServletSorting sorting;
        try {
            sorting = VideoServletSorting.valueOf(request.getParameter("sortby").toUpperCase());
        } catch (Exception ignored) {
            sorting = VideoServletSorting.DEFAULT;
        }

        List<VideoEntity> featuredVideos;
        VideoEntity previousVideo;

        switch (sorting) {
            case LIKES:
                if (video == null)
                    video = videoService.getFirstMostLikedVideo();
                featuredVideos = videoService.getMostLikedVideos(video);
                previousVideo = videoService.getPreviousMostLikedVideo(video);
                request.setAttribute("sortby", "likes");
                break;

            case DEFAULT:
            default:
                if (video == null)
                    video = videoService.getFirstVideo();
                featuredVideos = videoService.getFeaturedVideos(video);
                previousVideo = videoService.getPreviousVideo(video);
                break;
        }

        //Set country code
        //request.setAttribute("country", request.getHeader("X-AppEngine-Country"));

        request.setAttribute("showVideoTitleInTitle", true);
        request.setAttribute("video", video);
        request.setAttribute("prevVideo", previousVideo);
        if (featuredVideos.size() > 0)
            request.setAttribute("nextVideo", featuredVideos.get(0));
        request.setAttribute("featured", featuredVideos);

        request.getRequestDispatcher("/WEB-INF/jsp/video.jsp").forward(request, response);
    }

}
