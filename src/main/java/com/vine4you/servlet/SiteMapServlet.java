package com.vine4you.servlet;

import com.vine4you.service.DosProtectionService;
import com.vine4you.service.VideoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SiteMapServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //DOS Protection
        if (DosProtectionService.isAttacker(request, response)) return;

        request.setAttribute("videos", VideoService.getAllPublishedVideoEntityForSitemap());
        request.getRequestDispatcher("/WEB-INF/jsp/sitemap.jsp").forward(request, response);
    }
}
