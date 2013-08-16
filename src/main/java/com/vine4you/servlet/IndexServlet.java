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
 * User: Andrew
 * Date: 8/11/13
 * Time: 3:01 PM
 */
public class IndexServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        VideoEntity video = VideoService.getFirstVideo();
        List<VideoEntity> featuredVideos = VideoService.getFeaturedVideos(video);
        request.setAttribute("showVideoTitleInTitle", false);
        request.setAttribute("video", video);
        request.setAttribute("prevVideo", VideoService.getPreviousVideo(video));
        request.setAttribute("nextVideo", featuredVideos.get(0));
        request.setAttribute("featured", featuredVideos);

        request.getRequestDispatcher("/WEB-INF/jsp/video.jsp").forward(request, response);
    }
}
