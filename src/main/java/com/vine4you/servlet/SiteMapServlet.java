package com.vine4you.servlet;

import com.vine4you.service.VideoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SiteMapServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("videos", VideoService.getAllPublishedVideoEntity());
        request.getRequestDispatcher("/WEB-INF/jsp/sitemap.jsp").forward(request, response);
    }
}
