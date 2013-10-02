/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com)                     *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

package com.vine4you.servlet;

import com.vine4you.entity.VideoEntity;
import com.vine4you.enums.VideoServletSorting;
import com.vine4you.service.DosProtectionService;
import com.vine4you.service.VideoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * User: Andrew
 * Date: 8/11/13
 * Time: 3:01 PM
 */
public class IndexServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //DOS Protection
        if (DosProtectionService.isAttacker(request, response)) return;

        VideoServletSorting sorting;
        try {
            sorting = VideoServletSorting.valueOf(request.getParameter("sortby").toUpperCase());
        } catch (Exception ignored) {
            sorting = VideoServletSorting.DEFAULT;
        }

        switch (sorting) {
            case LIKES:
                likeSorted(request);
                break;

            case DEFAULT:
            default:
                dateSorted(request);
                break;
        }

        request.getRequestDispatcher("/WEB-INF/jsp/video.jsp").forward(request, response);
    }

    private void dateSorted(HttpServletRequest request) {
        VideoEntity video = VideoService.getFirstVideo();
        List<VideoEntity> featuredVideos = VideoService.getFeaturedVideos(video);
        request.setAttribute("showVideoTitleInTitle", false);
        request.setAttribute("video", video);
        request.setAttribute("prevVideo", VideoService.getPreviousVideo(video));
        request.setAttribute("nextVideo", featuredVideos.get(0));
        request.setAttribute("featured", featuredVideos);
    }

    private void likeSorted(HttpServletRequest request) {
        VideoEntity video = VideoService.getFirstMostLikedVideo();
        List<VideoEntity> featuredVideos = VideoService.getMostLikedVideos(video);
        request.setAttribute("showVideoTitleInTitle", false);
        request.setAttribute("video", video);
        request.setAttribute("prevVideo", VideoService.getPreviousMostLikedVideo(video));
        request.setAttribute("nextVideo", featuredVideos.get(0));
        request.setAttribute("featured", featuredVideos);
        request.setAttribute("sortby", "likes");
    }
}
