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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] split = request.getPathInfo().split("/", 2);

        VideoEntity video;
        try {
            video = VideoService.getVideoEntity(Long.parseLong(split[1]));
        } catch (Exception e) {
            video = VideoService.getFirstVideo();
        }
        List<VideoEntity> featuredVideos = VideoService.getFeaturedVideos(video);
        request.setAttribute("showVideoTitleInTitle", true);
        request.setAttribute("video", video);
        request.setAttribute("prevVideo", VideoService.getPreviousVideo(video));
        request.setAttribute("nextVideo", featuredVideos.get(0));
        request.setAttribute("featured", featuredVideos);

        request.getRequestDispatcher("/WEB-INF/jsp/video.jsp").forward(request, response);
    }
}
