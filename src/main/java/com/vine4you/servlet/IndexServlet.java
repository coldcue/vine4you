package com.vine4you.servlet;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

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

    public static DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
    }
}
