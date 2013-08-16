package com.vine4you.servlet;

import com.google.appengine.api.datastore.KeyFactory;
import com.vine4you.entity.VideoEntity;
import com.vine4you.service.VideoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: Andrew
 * Date: 8/11/13
 * Time: 3:01 PM
 */
public class IndexServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        VideoEntity firstVideo = VideoService.getFirstVideo();
        request.setAttribute("video", firstVideo);
        request.setAttribute("featured", VideoService.getFeaturedVideos(firstVideo));

        request.getRequestDispatcher("/WEB-INF/jsp/video.jsp").forward(request, response);
    }
}
